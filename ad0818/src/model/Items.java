/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author HikariHasegawa
 */
public class Items implements Serializable{
    private String itemId;
    private String itemName;
    private int price;
    private int num;
    

    public Items(String itemId,String itemName,int price,int num){
        this.itemId=itemId;
        this.itemName=itemName;
        this.price=price;
        this.num=num;
    }
    public String getItemId(){
        return itemId;
    }
    public String getItemName(){
        return itemName;
    }
    public int getPrice(){
        return price;
    }
    public int getNum(){
        return num;
    }
}
