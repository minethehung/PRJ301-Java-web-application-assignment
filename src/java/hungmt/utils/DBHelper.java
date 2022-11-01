/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author PC
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection()
    throws /*ClassNotFoundException*/NamingException, SQLException{
//        //1. load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. make connnection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=MVCDB;";
//        //3. open connection
//        Connection con = DriverManager.getConnection(url, "sa", "sa");
//        return con;
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        DataSource datasource = (DataSource)tomcatContext.lookup("DS007");
        Connection con = datasource.getConnection();
        return con;
    }
    public static void getSiteMaps (ServletContext context) throws IOException {
        String siteMaps  = context.getInitParameter("SITEMAP_FILE");
        InputStream is = null;
        Properties siteMap = null;
        try {
           is = context.getResourceAsStream(siteMaps);
           siteMap = new Properties();
           siteMap.load(is);
           
           context.setAttribute("SITEMAPS", siteMap);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public static void getAuthenticationList (ServletContext context) throws IOException {
        String siteMaps  = context.getInitParameter("AUTHENTICATION_FILE");
        InputStream is = null;
        Properties authentication = null;
        try {
           is = context.getResourceAsStream(siteMaps);
           authentication = new Properties();
           authentication.load(is);
           
           context.setAttribute("AUTHENTICATION", authentication);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
