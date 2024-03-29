/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.dao;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Alex
 */
public class FlooringAuditorFileImpl implements FlooringAuditor{

    
    public static String AUDIT_FILE = "audit.txt";
   
    public FlooringAuditorFileImpl(){
        
    }
    public FlooringAuditorFileImpl(String s){
        AUDIT_FILE = s;
    }
    public void writeEntry(String entry) throws AuditorFileAccessException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new AuditorFileAccessException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        out.close(); //TODO VERIFY THIS
    }

    @Override
    public void writeAddEntry(Order order) throws AuditorFileAccessException{
        String s = LocalDateTime.now().toString() + ": the following Order has been added " + order.toString();
        writeEntry(s);
    }

    @Override
    public void writeRemoveEntry(Order order) throws AuditorFileAccessException {
        String s = LocalDateTime.now().toString() + ": the following Order has been removed" + order.toString();
        writeEntry(s);
    }

    @Override
    public void writeEditEntry(Order order) throws AuditorFileAccessException {
        String s = LocalDateTime.now().toString() + ": the following Order has been edited" + order.toString();
        writeEntry(s);
    }
    
    
}
