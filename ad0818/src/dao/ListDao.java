/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Items;

/**
 *
 * @author HikariHasegawa
 */
public class ListDao {

    private final String DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver";
    private final String JDBC_URL = "jdbc:derby://localhost:1527/db0818";
    private final String DB_USER = "db";
    private final String DB_PASS = "db";

    public List<Items> findAll() {
        Connection conn = null;
        List<Items> Items = new ArrayList<Items>();

        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            String sql = "SELECT ITEMID,ITEMNAME,PRICE,NUM FROM ITEM ORDER BY ITEMID";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                String itemId = rs.getString("ITEMID");
                String itemName = rs.getString("ITEMNAME");
                int price = rs.getInt("PRICE");
                int num=rs.getInt("NUM");
                Items items = new Items(itemId, itemName, price,num);                
                Items.add(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return Items;
    }
}