/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Alex
 */
public interface FlooringDao {
    
    void importStates() throws DaoFileAccessException;
    void importOrders() throws DaoFileAccessException;
    void importProducts() throws DaoFileAccessException;
    
    void exportData() throws DaoFileAccessException;
    
    Order addOrder(Order order) throws DaoFileAccessException;
    Order editOrderCustomer(String customer, Order order);
    Order editState(String stateAbbr, Order order);
    Order editProduct(String productType, Order order);
    Order editArea(BigDecimal area, Order order);
    
    Order removeOrder(Integer orderNum);
    Order getOrder(Integer orderNum);
    
    boolean isValidState(String s);
    boolean isValidProduct(String s);
}
