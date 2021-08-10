/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import java.lang.Thread.State;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Alex
 */
public interface FlooringDao {
    
    void importStates();
    void importOrders();
    void importProducts();
    
    void exportData();
    
    Order addOrder(Order order);
    Order editOrderCustomer(String Customer, Order order);
    Order editState(State state, Order order);
    Order editProduct(Product product, Order order);
    Order editArea(BigDecimal area, Order order);
    
    Order removeOrder(Integer orderNum);
    Order getOrder(Integer orderNum);
    
    
    
    
}
