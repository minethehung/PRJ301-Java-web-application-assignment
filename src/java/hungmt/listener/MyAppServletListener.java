/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.listener;

import hungmt.utils.DBHelper;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author PC
 */
public class MyAppServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying......");
        ServletContext context = sce.getServletContext();
        try {
            DBHelper.getSiteMaps(context);
            DBHelper.getAuthenticationList(context);
        }catch (IOException e) {
            context.log("MyAppServletListener  IO " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeploy.......");
    }
}
