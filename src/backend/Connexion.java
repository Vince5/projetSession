/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.*;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class Connexion {
 
    public static Connection connecter() {
 
        Connection conn = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://localhost;databaseName=GestionPlus;integratedSecurity=true;";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                
                return conn;
                
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void fermer(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}