/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package alexander.fulleringer.flooring.service;

import alexander.fulleringer.flooring.dao.FlooringAuditor;
import alexander.fulleringer.flooring.dao.FlooringAuditorFileImpl;
import alexander.fulleringer.flooring.dao.FlooringDao;
import alexander.fulleringer.flooring.dao.FlooringDaoFileImpl;
import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.exceptions.NoOrdersFoundException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import alexander.fulleringer.flooring.view.View;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ServiceLayer {

    FlooringDao dao;
    FlooringAuditor auditor;

    public ServiceLayer() throws DaoFileAccessException {
        dao = new FlooringDaoFileImpl();
        auditor = new FlooringAuditorFileImpl();
    }

    public ServiceLayer(FlooringDao dao, FlooringAuditor auditor) {
        this.dao = dao;
        this.auditor = auditor;
    }

    public void exportData() throws DaoFileAccessException {
        dao.exportData();
    }

    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }

    public List<Order> getDatedOrders(LocalDate date) throws NoOrdersFoundException {
        List<Order> datedOrders = dao.getDatedOrders(date);
        if (datedOrders.size() == 0) {
            throw new NoOrdersFoundException("No orders for this date were found");
        } else {
            return datedOrders;
        }
    }

    public Order getOrder(Integer orderNumber) {
        return dao.getOrder(orderNumber);
    }

    public Order fillInAndAddOrder(Order order) throws DaoFileAccessException, AuditorFileAccessException {
        orderCalcs(order);
        Order added = dao.addOrder(order);
        auditor.writeAddEntry(order);
        return added;
    }

    public Order removeOrder(Integer orderNumber) throws DaoFileAccessException, AuditorFileAccessException {
        auditor.writeRemoveEntry(dao.getOrder(orderNumber));
        return dao.removeOrder(orderNumber);
    }

    public Order editOrderState(String s, Integer orderNum) throws AuditorFileAccessException {
        auditor.writeEditEntry(dao.getOrder(orderNum));
        return dao.editState(s, dao.getOrder(orderNum));
    }

    public Order editOrderCustomer(String s, Integer orderNum) throws AuditorFileAccessException {
        auditor.writeEditEntry(dao.getOrder(orderNum));
        return dao.editOrderCustomer(s, dao.getOrder(orderNum));
    }

    public Order editOrderProduct(String s, Integer orderNum) throws AuditorFileAccessException {
        auditor.writeEditEntry(dao.getOrder(orderNum));
        return dao.editProduct(s, dao.getOrder(orderNum));
    }

    public Order editOrderArea(BigDecimal area, Integer orderNum) throws AuditorFileAccessException {
        auditor.writeEditEntry(dao.getOrder(orderNum));
        return dao.editArea(area, dao.getOrder(orderNum));
    }

    public void orderCalcs(Order order) {
        TaxState state = dao.getState(order.getStateAbbr());
        Product product = dao.getProduct(order.getProductType());

        order.setTaxRate(state.getTaxRate());

        order.setCostPerSqFoot(product.getCostPerSqFoot());
        order.setLaborPerSqFoot(product.getLaborPerSqFoot());

        BigDecimal laborCost = order.getArea().multiply(order.getLaborPerSqFoot());
        laborCost.setScale(2, RoundingMode.HALF_UP);
        order.setLaborCost(laborCost);

        BigDecimal materialCost = order.getArea().multiply(order.getCostPerSqFoot());
        materialCost.setScale(2, RoundingMode.HALF_UP);
        order.setMaterialCost(materialCost);

        BigDecimal taxRate = order.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal taxes = laborCost.add(materialCost);
        taxes = taxes.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        order.setTax(taxes);

        order.setTotal(taxes.add(materialCost).add(laborCost));
    }

    public boolean isValidState(String s) {
        return dao.isValidState(s);
    }

    public boolean isValidProduct(String s) {
        return dao.isValidProduct(s);
    }

    public void redoDatedFile(LocalDate date) throws DaoFileAccessException {
        dao.redoDatedFile(date);
    }

    public List<TaxState> getAllStates() {
        return dao.getAllStates();
    }

    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    public boolean isValidDate(LocalDate date) {
        LocalDate now = LocalDate.now();
        return date.isAfter(now);
    }
}
