/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alex
 */
public class FlooringDaoFileImplTest {
//    
//    public FlooringDaoFileImplTest() {
//    }
//
//    /**
//     * Test of importStates method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testImportStates() throws Exception {
//        System.out.println("importStates");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        instance.importStates();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of importOrders method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testImportOrders() throws Exception {
//        System.out.println("importOrders");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        instance.importOrders();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of importDatedOrders method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testImportDatedOrders() throws Exception {
//        System.out.println("importDatedOrders");
//        LocalDate date = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        List<Order> expResult = null;
//        List<Order> result = instance.importDatedOrders(date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of importProducts method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testImportProducts() throws Exception {
//        System.out.println("importProducts");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        instance.importProducts();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of exportData method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testExportData() throws Exception {
//        System.out.println("exportData");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        instance.exportData();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addOrder method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testAddOrder() throws Exception {
//        System.out.println("addOrder");
//        Order order = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.addOrder(order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editOrderCustomer method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testEditOrderCustomer() {
//        System.out.println("editOrderCustomer");
//        String customer = "";
//        Order order = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.editOrderCustomer(customer, order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editProduct method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testEditProduct() {
//        System.out.println("editProduct");
//        String productType = "";
//        Order order = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.editProduct(productType, order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of doesFileExist method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testDoesFileExist() {
//        System.out.println("doesFileExist");
//        String filePath = "";
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        boolean expResult = false;
//        boolean result = instance.doesFileExist(filePath);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editArea method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testEditArea() {
//        System.out.println("editArea");
//        BigDecimal area = null;
//        Order order = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.editArea(area, order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeOrder method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testRemoveOrder() {
//        System.out.println("removeOrder");
//        Integer orderNum = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.removeOrder(orderNum);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrder method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testGetOrder() {
//        System.out.println("getOrder");
//        Integer orderNum = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        Order expResult = null;
//        Order result = instance.getOrder(orderNum);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
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
//
//    /**
//     * Test of getAllOrders method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testGetAllOrders() {
//        System.out.println("getAllOrders");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        List<Order> expResult = null;
//        List<Order> result = instance.getAllOrders();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllStates method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testGetAllStates() {
//        System.out.println("getAllStates");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        List<TaxState> expResult = null;
//        List<TaxState> result = instance.getAllStates();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllProducts method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testGetAllProducts() {
//        System.out.println("getAllProducts");
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        List<Product> expResult = null;
//        List<Product> result = instance.getAllProducts();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isValidState method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testIsValidState() {
//        System.out.println("isValidState");
//        String s = "";
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        boolean expResult = false;
//        boolean result = instance.isValidState(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isValidProduct method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testIsValidProduct() {
//        System.out.println("isValidProduct");
//        String s = "";
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        boolean expResult = false;
//        boolean result = instance.isValidProduct(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDatedOrders method, of class FlooringDaoFileImpl.
//     */
//    @Test
//    public void testGetDatedOrders() {
//        System.out.println("getDatedOrders");
//        LocalDate date = null;
//        FlooringDaoFileImpl instance = new FlooringDaoFileImpl();
//        List<Order> expResult = null;
//        List<Order> result = instance.getDatedOrders(date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
