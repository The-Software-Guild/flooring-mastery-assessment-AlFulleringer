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
    String ProductType;
    BigDecimal costPerSqFoot;
    BigDecimal laborPerSqFoot;

    public Product(String ProductType, BigDecimal costPerSqFoot, BigDecimal laborPerSqFoot) {
        this.ProductType = ProductType;
        this.costPerSqFoot = costPerSqFoot;
        this.laborPerSqFoot = laborPerSqFoot;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
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
        return "Product{" + "ProductType=" + ProductType + ", costPerSqFoot=" + costPerSqFoot + ", laborPerSqFoot=" + laborPerSqFoot + '}';
    }
    
}
