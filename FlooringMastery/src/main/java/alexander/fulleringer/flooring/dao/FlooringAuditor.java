/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.model.Order;

/**
 *
 * @author Alex
 */
public interface FlooringAuditor {
    
    void writeEntry(String s)throws AuditorFileAccessException;

    public void writeAddEntry(Order order) throws AuditorFileAccessException;

    public void writeRemoveEntry(Order order)throws AuditorFileAccessException;

    public void writeEditEntry(Order order)throws AuditorFileAccessException;

}
