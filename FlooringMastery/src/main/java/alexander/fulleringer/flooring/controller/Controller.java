/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package alexander.fulleringer.flooring.controller;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.exceptions.NoOrdersFoundException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.service.ServiceLayer;
import alexander.fulleringer.flooring.view.View;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alex
 */
public class Controller {
    
    ServiceLayer service;
    View view;
    
    Controller() {
        try {
            service = new ServiceLayer();
            view = new View();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean loop = true;
        int choice;
        while (loop) {
            choice = view.printMenuGetSelection();
            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportData();
                    break;
                case 6:
                    loop = false;
                    break;
                default:
                    view.displayUnkownCommandBanner();
            }
        }
        try {
            service.exportData();
        } catch (DaoFileAccessException e) {
            view.displayError(e);
        }
    }
    
    private void displayOrders() {
        LocalDate date = view.getDate();
        try {
            List<Order> toDisplay = service.getDatedOrders(date);
            view.displayOrders(toDisplay);
        } catch (NoOrdersFoundException e) {
            view.displayError(e);
        }
        
    }
    
    private void addOrder() {
        Order toAdd = new Order();
        
        setValidOrderDate(toAdd);
        setValidCustomerName(toAdd);
        setValidTaxState(toAdd);
        setValidProductType(toAdd);
        setValidArea(toAdd);
        service.orderCalcs(toAdd);
        try {
            service.fillInAndAddOrder(toAdd);
            view.displayOrder(toAdd);
            view.displaySuccess("Your order has been added!\n");
            
        } catch (DaoFileAccessException | AuditorFileAccessException e) {
            view.displayError(e);
        }
    }
    
    private void setValidArea(Order order) {
//      BigDecimal area = getValidBigDecimal();
        view.displayRequestAreaPrompt();
        boolean validArea = false;
        BigDecimal area;
        while (!validArea) {
            area = view.parseBigDecimal();
            validArea = (area.compareTo(BigDecimal.ZERO) != -1);
            if (validArea) {
                order.setArea(area);
            }
        }
        
    }
    
    private void setValidProductType(Order toAdd) {
        boolean isValidProduct = false;
        while (!isValidProduct) {
            view.displayProductSelectionBanner();
            view.displayAllProducts(service.getAllProducts());
            
            String productType = view.parseString("Please enter the product you'd like now");
            isValidProduct = service.isValidProduct(productType);
            
            if (isValidProduct) {
                toAdd.setProductType(productType);
                view.displaySuccess("That is a valid product");
            } else {
                view.displayBadProductBanner();
            }            
            
        }
    }
    
    private void setValidOrderDate(Order order) {
        LocalDate orderDate = view.getNewOrderDate();
        while (!service.isValidDate(orderDate)) {
            orderDate = view.getNewOrderDate();
        }
        order.setOrderDate(orderDate);
    }

    //Stretch goal: Reject names with "::"
    private void setValidCustomerName(Order toAdd) {
        String customerName = "";
        while (customerName.length() == 0) {
            customerName = view.parseString("Please enter the customer's name, it must not be left blank");
        }
        toAdd.setCustomerName(customerName);
        view.displaySuccess("Customer name successfully chosen");
    }
    
    private void setValidTaxState(Order toAdd) {
        boolean isValidState = false;
        while (!isValidState) {
            view.displayStateSelectionBanner();
            view.displayStates(service.getAllStates());
            
            String stateAbbr = view.parseString("");
            isValidState = service.isValidState(stateAbbr);
            
            if (isValidState) {
                toAdd.setStateAbbr(stateAbbr);
                view.displaySuccess("That is a good state!");
            } else {
                view.displayBadStateBanner();
            }            
            
        }
    }
    
    private void editOrder() {
        boolean loop = true;
        int choice;
        int orderNum = view.parseOrderNumber();
        LocalDate orderDate = view.getDate();
        Order theOrder = service.getOrder(orderNum);
        if (theOrder == null) {
            view.parseString("No such order exists\nPress enter to continue");
            return;
        } else {
            String oldCustomerName = theOrder.getCustomerName();
            String oldState = theOrder.getStateAbbr();
            String oldProduct = theOrder.getProductType();
            BigDecimal oldArea = theOrder.getArea();
            editCustomerName(theOrder);
            editState(theOrder);
            editProductType(theOrder);
            editArea(theOrder);
            
            if (view.verifyOrderEdit(theOrder)) {
                try {
                    service.exportData();
                    service.redoDatedFile(theOrder.getOrderDate());
                } catch (DaoFileAccessException e) {
                    view.displayError(e);
                }
            } else {
                theOrder.setCustomerName(oldCustomerName);
                theOrder.setStateAbbr(oldState);
                theOrder.setProductType(oldProduct);
                theOrder.setArea(oldArea);
            }
            
        }
        
    }
    
    private void removeOrder() {
        int orderNum = view.parseOrderNumber();
        LocalDate orderDate = view.getDate();
        Order theOrder = service.getOrder(orderNum);
        
        boolean toDrop = view.confirmDrop(theOrder);
        
        if (toDrop) {
            try {
                service.removeOrder(orderNum);
            } catch (DaoFileAccessException|AuditorFileAccessException e) {
                view.displayError(e);
            }
        } else {
            view.parseString("No changes were made.\nPress enter to continue");
        }
    }
    
    private void exportData() {
        try {
            service.exportData();
        } catch (DaoFileAccessException e) {
            view.displayError(e);
        }
        
    }
    
    private void editCustomerName(Order theOrder) {
        String s = view.getEditCustomerName(theOrder);
        if (s.length() == 0) {
            return;
        } else {
            theOrder.setCustomerName(s);
        }
    }
    
    private void editState(Order theOrder) {
        String newState;
        newState = view.getEditState(theOrder, service.getAllStates());
        
        if (newState.length() == 0) {
            return;
        } else {
            if (service.isValidState(newState)) {
                theOrder.setStateAbbr(newState);
                view.displaySuccess("Edit successful");
            } else {
                view.displayInvalidInputBanner();
                editState(theOrder);
            }
        }
    }

    private void editProductType(Order theOrder) {
        String newProduct;
        newProduct = view.getEditProduct(theOrder, service.getAllProducts());
        
        if (newProduct.length() == 0) {
            return;
        } else {
            if (service.isValidProduct(newProduct)) {
                theOrder.setProductType(newProduct);
                view.displaySuccess("Edit successful");
            } else {
                view.displayInvalidInputBanner();
                editProductType(theOrder);
            }
        }
    }
    
    private void editArea(Order theOrder) {
        String s;
        s = view.getEditArea(theOrder);
        BigDecimal newArea;
        boolean validArea;
        if (s.length() == 0) {
            return;
        } else {
            try {
                newArea = new BigDecimal(s);
                validArea = (newArea.compareTo(BigDecimal.ZERO) != -1);
                if (validArea) {
                    theOrder.setArea(newArea);
                } else {
                    view.displayInvalidInputBanner();
                    editArea(theOrder);
                }
            } catch (NumberFormatException e) {
                view.displayInvalidInputBanner();
                editArea(theOrder);
            }
        }
    }
    
}
