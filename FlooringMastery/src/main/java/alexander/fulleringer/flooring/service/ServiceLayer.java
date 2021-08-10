/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.service;

import alexander.fulleringer.flooring.dao.FlooringDao;
import alexander.fulleringer.flooring.dao.FlooringDaoFileImpl;
import alexander.fulleringer.flooring.model.Order;

/**
 *
 * @author Alex
 */
public class ServiceLayer {
    
    FlooringDao dao;
    
    ServiceLayer(){
        dao = new FlooringDaoFileImpl();
    }
    
    public Order getOrder(Integer orderNumber){
        return dao.getOrder(orderNumber);
    }
    
    public void addOrder(Order order){
        
    }
    
    
}
