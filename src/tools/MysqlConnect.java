/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Wahyu
 */
public class MysqlConnect {
    public Connection conn = null;
    public String dbUrl = "jdbc:mysql://localhost:3306/";
    public String dbName = "tatausahajava";
    public String driver = "com.mysql.jdbc.Driver";
    public String dbUser = "root"; 
    public String dbPass = "vertrigo";


    public void createDatabase(){
    try {
        conn = DriverManager.getConnection(dbUrl+dbName,dbUser,dbPass);
    } 
   
    catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Tidak Terhubung ke SQL");
    }
    }
    

}

