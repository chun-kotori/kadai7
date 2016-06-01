/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import dao.ListDao;
import java.util.List;

/**
 *
 * @author HikariHasegawa
 */
public class getItemListLogic {
        public List<Items> execute(){
        ListDao dao=new ListDao();
        List<Items> Items=dao.findAll();
        return Items;
    }
}
