/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import tools.Database;
import tools.MysqlConnect;
import tools.Report;

/**
 *
 * @author Wahyu
 */
public class Pembayaran {
    
// Inisialisasi Database
MysqlConnect mysqlConnect = new MysqlConnect();
ResultSet rs,rb;
Statement st;
String bulan_tahun,nis;
    public Pembayaran(String nis, String bulan_tahun) {
        mysqlConnect.createDatabase(); // Buka Database
        try{
        st = mysqlConnect.conn.createStatement();
        }
        catch(SQLException ex){
            
        }
        this.nis=nis;
        this.bulan_tahun=bulan_tahun;
        TampilkanReport();
    }
    
    
    public void TampilkanReport(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = (Connection) Database.getConnection();
            statement = connection.createStatement();
            HashMap parameterMap = new HashMap();
            parameterMap.put("nis", nis);//sending the report title as a parameter.
            parameterMap.put("bulan", bulan_tahun);
            Report rpt = new Report(parameterMap, connection);
            rpt.setReportName("Pembayaran"); //productlist is the name of my jasper file.
            rpt.callReport();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       
    }
    
}


