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
public class Product {
    String productType;
    BigDecimal costPerSqFoot;
    BigDecimal laborPerSqFoot;

    public Product(String ProductType, BigDecimal costPerSqFoot, BigDecimal laborPerSqFoot) {
        this.productType = ProductType;
        this.costPerSqFoot = costPerSqFoot;
        this.laborPerSqFoot = laborPerSqFoot;
    }

    public Product() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String ProductType) {
        this.productType = ProductType;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public BigDecimal getLaborPerSqFoot() {
        return laborPerSqFoot;
    }

    public void setLaborPerSqFoot(BigDecimal laborPerSqFoot) {
        this.laborPerSqFoot = laborPerSqFoot;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductType=" + productType + ", costPerSqFoot=" + costPerSqFoot + ", laborPerSqFoot=" + laborPerSqFoot + '}';
    }
    
}
