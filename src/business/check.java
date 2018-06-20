/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.ArrayMemberRepository;
import data.DBMemberRepository;
import data.MemberRepository;

/**
 *
 * @author Bocman
 */
public class check {
    private int id;
    private Payment payment;
    private Integer num = 0;
    
    //private MemberRepository rep = new ArrayMemberRepository();
    private MemberRepository rep = new DBMemberRepository();
    
    public check(Payment payment) {
        this.payment = payment;
    }
    
    public int Verifycheck(int num,Order oldorder) {
        if(this.num != num)
            return 2;
        if(this.payment.getOrder().getClient() != oldorder.getClient())
            return 3;
        return 0;
    }
    
    public check(Payment payment, int num) {
        this.payment = payment;
        this.num = num;
    }
    
    public int getNum(Payment pay){
        int numc = 0;
        rep.getCheck();
        return numc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public void update() {
        rep.update(this);
    }
}
