/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Alex
 */
public class FlooringDaoFileImplTest {
    
    FlooringDao dao;
    Order testOrder;
    public FlooringDaoFileImplTest() {
    }
    
    
    @BeforeEach
    public void setUp() {
        try{
            dao = new FlooringDaoFileImpl();
            
            //System.out.println("Fields should be initialized by now");
            setupTestOrder();
            
        }
        catch(Exception e){
            System.out.println("Issue initializing tests");
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
            dao.addOrder(testOrder);
        }
        catch(DaoFileAccessException e){
            System.out.println("COULD NOT ADD ORDER TO DAO");
        }
    }
    @AfterEach
    public void tearDown() {
        try{
            dao.removeOrder(testOrder.getOrderNumber());
        }
        catch (Exception e){
            System.out.println("Error getting rid of test order.");
        }
    }
    
    @Test
    public void testEditProduct() {
        System.out.println("editProduct");
        String productType = "Laminate";
        System.out.println(this.testOrder);
        Order order = dao.getOrder(testOrder.getOrderNumber());
        dao.editProduct(productType, order);
        Product product = dao.getProduct(productType);
        assertEquals(order.getProductType(), product.getProductType());
        assertEquals(0, product.getCostPerSqFoot().compareTo(order.getCostPerSqFoot()));
        
    }
    @Test
    public void testEditState() {
        
        System.out.println("editState");
        String state = "CA";
        Order order = dao.getOrder(testOrder.getOrderNumber());
        dao.editState(state, order);
        TaxState targetState = dao.getState(state);
        assertEquals(order.getStateAbbr(), targetState.getStateAbbr());
        assertEquals(0,targetState.getTaxRate().compareTo(order.getTaxRate()));
        
    }
    /**
     * Test of doesFileExist method, of class FlooringDaoFileImpl.
     */
    @Test
    public void testDoesFileExist() {
        System.out.println("doesFileExist");
        String filePath = "thisdoesnotexist";
        
        assertFalse(dao.doesFileExist(filePath));
        assertTrue(dao.doesFileExist("InfoStorage\\Backup\\DataExport.txt"));
        
    }
    
    public void testGetOrder() {
        System.out.println("getOrder");
        Integer orderNum = -1;
        Order expResult = testOrder;
        Order result = dao.getOrder(orderNum);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of editState method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testEditState() {
//        System.out.println("editState");
//        String stateAbbr = "";
//        Order order = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.editState(stateAbbr, order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of isValidState method, of class FlooringDaoFileImpl.
     */
    @Test
    public void testIsValidState() {
        System.out.println("isValidState");
        assertTrue(dao.isValidState("TX"));
        assertFalse(dao.isValidState("Quebec"));
    }
    
}
