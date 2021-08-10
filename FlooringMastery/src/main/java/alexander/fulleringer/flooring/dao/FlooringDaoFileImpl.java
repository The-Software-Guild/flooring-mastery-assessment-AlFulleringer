/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class FlooringDaoFileImpl implements FlooringDao {
    
    private String EXPORT_DATA_PATH ="InfoStorage/Backup/DataExport.txt";
    private String ORDERS_FOLDER =  "InfoStorage/Orders";
    private String STATES_PATH = "InfoStorage/Data/Taxes.txt";
    private String PRODUCTS_PATH = "InfoStorage/Data/Products.txt";
    private final String DELIMITER = ",";
    
    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, TaxState> states = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    
    
    @Override
    public void importStates() throws DaoFileAccessException{
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(STATES_PATH)));
        }
        catch(FileNotFoundException e){
            //Turn the generic exception into our sapecific type so we can keep io in viewer.
            throw new DaoFileAccessException("File " + STATES_PATH + "Could not be found :(");
        }
        
        String currentLine = scanner.nextLine(); //Skip over the first line.
        TaxState currentState;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentState = unmarshallState(currentLine);
            states.put(currentState.getStateName(),currentState);
        }
        scanner.close();
    }
    
    @Override
    public void importOrders() throws DaoFileAccessException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(EXPORT_DATA_PATH)));
        }
        catch(FileNotFoundException e){
            //Turn the generic exception into our sapecific type so we can keep io in viewer.
            throw new DaoFileAccessException("File " + EXPORT_DATA_PATH + "Could not be found :(");
        }
        
        String currentLine = scanner.nextLine(); //Skip over the first line.
        Order nextOrder;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            nextOrder = unmarshallOrder(currentLine);
            orders.put(nextOrder.getOrderNumber(), nextOrder);
        }
        scanner.close();
    }
    
    @Override
    public void importProducts() throws DaoFileAccessException{
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(STATES_PATH)));
        }
        catch(FileNotFoundException e){
            //Turn the generic exception into our sapecific type so we can keep io in viewer.
            throw new DaoFileAccessException("File " + PRODUCTS_PATH + "Could not be found :(");
        }
        
        String currentLine = scanner.nextLine(); //Skip over the first line.
        Product product;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            product = unmarshallProduct(currentLine);
            products.put(product.getProductType(),product);
        }
        scanner.close();
    }
    
    @Override
    public void exportData() throws DaoFileAccessException{
        PrintWriter out;
        String toWrite;
        try {
            out = new PrintWriter(new FileWriter(EXPORT_DATA_PATH));
        }
        catch (IOException ex) {
            throw new DaoFileAccessException("Data could not be saved :(");
        }
        
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate");
        for(Order order: this.getAllOrders()){
            toWrite = order.getFileString();
            out.println(toWrite);
        }
        out.flush();
        out.close();
    }
    
    @Override
    public Order addOrder(Order order) {
        return orders.put(order.getOrderNumber(), order);
    }
    
    @Override
    public Order editOrderCustomer(String customer, Order order) {
        order.setCustomerName(customer);
        return order;
    }
    
    @Override
    public Order editProduct(Product product, Order order) {
        order.setCostPerSqFoot(product.getCostPerSqFoot());
        order.setLaborCost(product.getLaborPerSqFoot());
        order.setProductType(product.getProductType());
        return order;
    }
    
    @Override
    public Order editArea(BigDecimal area, Order order) {
        order.setArea(area);
        return order;
    }
    
    @Override
    public Order removeOrder(Integer orderNum) {
        return orders.remove(orderNum);
    }
    
    @Override
    public Order getOrder(Integer orderNum) {
        return orders.get(orderNum);
    }
    
    @Override
    public Order editState(TaxState state, Order order) {
        order.setStateAbbr(state.getStateAbbr());
        order.setTaxRate(state.getTaxRate());
        return order;
    }
    
    private TaxState unmarshallState(String currentLine) {
        TaxState newState = new TaxState();
        String[] tokens = currentLine.split(DELIMITER, 3);
        newState.setStateAbbr(tokens[0]);
        newState.setStateName(tokens[1]);
        newState.setTaxRate(new BigDecimal(tokens[2]));
        return newState;
    }
    private Product unmarshallProduct(String currentLine) {
        Product newProduct = new Product();
        String[] tokens = currentLine.split(DELIMITER, 3);
        newProduct.setProductType(tokens[0]);
        newProduct.setCostPerSqFoot(new BigDecimal (tokens[1]));
        newProduct.setLaborPerSqFoot(new BigDecimal(tokens[2]));
        return newProduct;
    }
    
    private Order unmarshallOrder(String currentLine) {
        Order order = new Order();
        String[] tokens = currentLine.split(DELIMITER,12);
        order.setOrderNumber(Integer.parseInt(tokens[0]));
        order.setCustomerName(tokens[1]);
        order.setStateAbbr(tokens[2]);
        order.setTaxRate(new BigDecimal(tokens[3]));
        order.setProductType(tokens[4]);
        order.setArea(new BigDecimal(tokens[5]));
        order.setCostPerSqFoot(new BigDecimal(tokens[6]));
        order.setLaborPerSqFoot(new BigDecimal(tokens[7]));
        order.setMaterialCost(new BigDecimal(tokens[8]));
        order.setLaborCost(new BigDecimal(tokens[9]));
        order.setTax(new BigDecimal(tokens[10]));
        order.setTotal(new BigDecimal(tokens[11]));
        return order;
    }
    
    private List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
