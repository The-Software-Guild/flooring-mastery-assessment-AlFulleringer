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
import alexander.fulleringer.flooring.model.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Alex
 */
public class ServiceLayer {
    
    FlooringDao dao;
    FlooringAuditor auditor;
    
    ServiceLayer(){
        dao = new FlooringDaoFileImpl();
        auditor = new FlooringAuditorFileImpl();
    }
    
    public Order getOrder(Integer orderNumber){
        return dao.getOrder(orderNumber);
    }
    
    public Order addOrder(Order order){
        orderCalcs(order);
        return dao.addOrder(order);
    }
    
    public Order removeOrder(Integer orderNumber){
        return dao.removeOrder(orderNumber);
    }
    
    public Order editOrderState(String s, Integer orderNum){
        return dao.editState(s, dao.getOrder(orderNum));
    }
    
    public Order editOrderCustomer(String s, Integer orderNum){
        return dao.editOrderCustomer(s, dao.getOrder(orderNum));
    }
    
    public Order editOrderProduct(String s, Integer orderNum){
        return dao.editProduct(s, dao.getOrder(orderNum));
    }
    
    public Order editOrderArea(BigDecimal area, Integer orderNum){
        return dao.editArea(area, dao.getOrder(orderNum));
    }
    
    
    public void orderCalcs(Order order){
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
    boolean isValidState(String s){
        return dao.isValidState(s);
    }
    boolean isValidProduct(String s){
        return dao.isValidProduct(s);
    }
}
