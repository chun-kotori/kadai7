/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import model.Items;

/**
 *
 * @author HikariHasegawa
 */
public class HistoryDao {
    private final String DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver";
    private final String JDBC_URL = "jdbc:derby://localhost:1527/db0818";
    private final String DB_USER = "db";
    private final String DB_PASS = "db";
    

    public boolean create(/*userList userlist*/String userId,int sum){
        Connection conn=null;
        try{
            conn=DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);

            String sql="INSERT INTO HISTORY(USERID,PRICE) VALUES(?,?)";
            PreparedStatement pStmt=conn.prepareStatement(sql);

            //pStmt.setString(1,userlist.getUserId());
            pStmt.setString(1,userId);
            pStmt.setInt(2,sum);
            
            int result=pStmt.executeUpdate();
            if(result!=1){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
