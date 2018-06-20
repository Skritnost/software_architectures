/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.ArrayMemberRepository;
import data.DBMemberRepository;
import data.MemberRepository;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Bocman
 */
public abstract class Company {
    protected int id;
    protected String name;
    protected String login;
    protected String pass;
    protected CompanyType type;
    protected int cash = 0;
    //protected MemberRepository rep = new ArrayMemberRepository();
    protected MemberRepository rep = new DBMemberRepository();
    public abstract ArrayList<Order> getOrders();
    
    public Boolean autentification(String aLogin, String aPass) {
        return aLogin.equals(getLogin()) && aPass.equals(getPass());
    }
     public ArrayList<Order> getOrders(OrderType status) {
        ArrayList<Order> orders = getOrders();
        ArrayList<Order> answer = new ArrayList<>();
        for(Order order : orders) {
            if (order.getType() == status)
                answer.add(order);
        }
        return answer;
    }
            
    public Boolean addCash (int num){
        cash += num;
        //update();
        return true;
    }
    
    public Boolean writeOffCash (int num) {
        if (cash < num)
            return false;
        
        cash -= num;
        //update();
        return true;
    } 
    
    //Возвращает оплаты, связанные с компанией
    public ArrayList<Payment> getPays() {
        return rep.getPayments(this);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    
    
}
