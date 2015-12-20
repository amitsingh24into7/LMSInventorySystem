package com.lms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	private static Connection connection = null;
	private static DataSource dataSource;
/*
    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("jdbc/STGDB");
        }
        catch (NamingException e) { 
            throw new ExceptionInInitializerError("'jndifordbconc' not found in JNDI");
        }
    }

    public static Connection getDBConnection()  {
        try {
        	connection= dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection=null;
		}
        return connection;
    }*/

  /* public static Connection getDBConnection() {
    	
        if (connection != null)
            return connection;
        else {
            try {
            	Properties prop = new Properties();
                InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
   }*/
	
	public static Connection getDBConnection() {	
	try {
    	Context initContext = new InitialContext();
    	Context envContext = (Context) initContext.lookup("java:comp/env");
    	DataSource ds = (DataSource) envContext.lookup("jdbc/STGDB");
    	
    	connection= ds.getConnection();
    	System.out.println(connection);
    	
    	 }
    	 catch (NamingException ex) {
             System.err.println(ex);
         } catch (Exception ex) {
             System.err.println(ex);
         }
    	 return connection;
	}
    
}
