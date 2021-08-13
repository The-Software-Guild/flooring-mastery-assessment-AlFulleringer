/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.service;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alex
 */
public class ServiceLayerTest {
    ServiceLayer service;
    Order testOrder;
    public ServiceLayerTest() {
    }
    
    @BeforeEach
    public void setUp() {
        try {
            service = new ServiceLayer();
            setupTestOrder();
        } catch (DaoFileAccessException ex) {
            System.out.println("Service init error");
        }
    }
    private void setupTestOrder(){
        testOrder = new Order();
        testOrder.setOrderNum(-1);
        testOrder.setArea(BigDecimal.ONE);
        testOrder.setCustomerName("Testy McTest");
        testOrder.setOrderDate(LocalDate.now());
        testOrder.setStateAbbr("TX");
        testOrder.setTaxRate(new BigDecimal("5.0"));
        testOrder.setCostPerSqFoot(BigDecimal.ONE);
        testOrder.setLaborPerSqFoot(BigDecimal.ONE);
        testOrder.setLaborCost(BigDecimal.ZERO);
        testOrder.setMaterialCost(BigDecimal.ZERO);
        testOrder.setTax(BigDecimal.ZERO);
        testOrder.setTotal(BigDecimal.ONE);
        testOrder.setProductType("Wood");
        
        try{
            service.fillInAndAddOrder(testOrder);
        }
        catch(DaoFileAccessException|AuditorFileAccessException e){
            System.out.println("COULD NOT ADD ORDER TO DAO");
        }
    }
    @AfterEach
    public void tearDown() {
        try {
            service.removeOrder(-1);
        } catch (DaoFileAccessException ex) {
            Logger.getLogger(ServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuditorFileAccessException ex) {
            Logger.getLogger(ServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    /**
     * Test of removeOrder method, of class ServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        try {
            service.removeOrder(-1);
            assertFalse(service.getAllOrders().contains(service.getOrder(-1)));
            service.fillInAndAddOrder(testOrder);
        } catch (DaoFileAccessException ex) {
            Logger.getLogger(ServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuditorFileAccessException ex) {
            Logger.getLogger(ServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of editOrderState method, of class ServiceLayer.
     */
    @Test
    public void testEditOrderState() {
        try {
            BigDecimal oldTotal = testOrder.getTotal();
            service.editOrderState("CA", testOrder.getOrderNumber());
            assertTrue(0 != oldTotal.compareTo(testOrder.getTotal()));
        } catch (AuditorFileAccessException ex) {
            Logger.getLogger(ServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of orderCalcs method, of class ServiceLayer.
     */
    @Test
    public void testOrderCalcs() {
        Order knownOrder = service.getOrder(1);
        Order order = new Order();
        order.setOrderNum(-1);
        order.setArea(knownOrder.getArea());
        order.setCustomerName("Testy McTest");
        order.setOrderDate(LocalDate.now());
        order.setStateAbbr(knownOrder.getStateAbbr());
        order.setProductType(knownOrder.getProductType());
        service.orderCalcs(order);
        
        assertEquals(0, order.getTotal().compareTo(knownOrder.getTotal()));
    }

    /**
     * Test of isValidState method, of class ServiceLayer.
     */
    @Test
    public void testIsValidState() {
        assertTrue(service.isValidState("CA"));
        assertFalse(service.isValidState("Wood"));
    }

    /**
     * Test of isValidProduct method, of class ServiceLayer.
     */
    @Test
    public void testIsValidProduct() {
        assertTrue(service.isValidProduct("Wood"));
        assertFalse(service.isValidProduct("CA"));
    }
    
}
