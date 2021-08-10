/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;

/**
 *
 * @author Alex
 */
public interface FlooringAuditor {
    
    void writeEntry(String s)throws AuditorFileAccessException;

}
