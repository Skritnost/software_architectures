/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.ArrayMemberRepository;
import data.MemberRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Bocman
 */
public class Disk {
    private int id;
    private Store store;
    private String Type;
    private String Name;
    private String Info;
    private int cost;
    private int num;
    
    protected MemberRepository rep = new ArrayMemberRepository();
    
     public Disk (Store store, String Name, String Type, String Info, int cost, int num ) {
        this.Info = Info;
        this.store = store;
        this.Name = Name;
        this.Type = Type;
        this.cost = cost;
        this.num = num;      
    }
     
    public Boolean addNum(int n){
        num += n;
        //update();
        return true;
    } 
    
    public Boolean writeNum(int n){
        if(num<n)
            return false;
        num -= n;
        //update();
        return true;
    } 
    
    public void update() {
        rep.update(this);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
     
}
