/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring;

import alexander.fulleringer.flooring.controller.Controller;
import alexander.fulleringer.flooring.dao.FlooringAuditor;
import alexander.fulleringer.flooring.dao.FlooringAuditorFileImpl;
import alexander.fulleringer.flooring.dao.FlooringDao;
import alexander.fulleringer.flooring.dao.FlooringDaoFileImpl;
import alexander.fulleringer.flooring.exceptions.DaoFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.service.ServiceLayer;
import alexander.fulleringer.flooring.view.View;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class App {
    public static void main(String[] args){
//        try{
//        FlooringDao dao = new FlooringDaoFileImpl();
//        FlooringAuditor auditor = new FlooringAuditorFileImpl();
//        View view = new View();
//        ServiceLayer service = new ServiceLayer(dao,auditor);
//        
//        Controller controller = new Controller(service, view);
//        
//        controller.run();
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Controller controller = appContext.getBean("controller", Controller.class);
        controller.run();
        
    }
}
