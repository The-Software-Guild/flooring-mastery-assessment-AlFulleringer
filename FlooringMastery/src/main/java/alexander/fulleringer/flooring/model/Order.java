/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.model;

import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public class Order {
    static int nextOrderNum;
    Integer orderNumber;
    String customerName;
    String state;
    String productType;
    BigDecimal area;
    BigDecimal costSqFoot;
    BigDecimal laborCostSqFoot; 
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal tax;
    BigDecimal total;

    public Order() {
    }

    public static void setNextOrderNum(int nextOrderNum) {
        Order.nextOrderNum = nextOrderNum;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setCostSqFoot(BigDecimal costSqFoot) {
        this.costSqFoot = costSqFoot;
    }

    public void setLaborCostSqFoot(BigDecimal laborCostSqFoot) {
        this.laborCostSqFoot = laborCostSqFoot;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public static int getNextOrderNum() {
        return nextOrderNum;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getState() {
        return state;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getCostSqFoot() {
        return costSqFoot;
    }

    public BigDecimal getLaborCostSqFoot() {
        return laborCostSqFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
    
    
}
