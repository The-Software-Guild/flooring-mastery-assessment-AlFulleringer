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
public class State {
    private String stateAbbr;
    private String stateName;
    private BigDecimal taxRate;

    public State(String stateAbbr, String stateName, BigDecimal taxRate) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "StateTax{" + "stateAbbr=" + stateAbbr + ", stateName=" + stateName + ", taxRate=" + taxRate + '}';
    }
    
    
    
    
}
