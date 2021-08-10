/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.exceptions;

/**
 *
 * @author Alex
 */
public class DaoFileAccessException extends Exception {
    public DaoFileAccessException(String message){
        super(message);
    }
    public DaoFileAccessException(String message, Throwable cause){
        super(message,cause);
    }
}
