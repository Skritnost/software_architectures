/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;

/**
 *
 * @author Bocman
 */
public class Client extends Company{
    private int numofcheck;
    private String comment;
    private ServiceCenter chooseSC;//Выбранное оборудование для заказа в магазине
    private Disk chooseEq;//Выбранное оборудование для заказа в магазине
    private Order oldorder;//Старый заказ для сервисцентра, если техника поломалась
    public Client() {
        type = CompanyType.Client;
    }
    
    public  Client(String name, String login, String pass) {
        this.name = name;
        this.login = login;
        this.pass = pass;
        type = CompanyType.Client;
    } 

    @Override
    public ArrayList<Order> getOrders() {
        ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {
            if ( (order.getType().equals(OrderType.ServeciCenter)||order.getType().equals(OrderType.Client))&&
                    order.getClient().equals(this) )
                arr.add(order);
        }
        return arr;
    }
    
    public ArrayList<Payment> getPaymentstoStore() {//получение списка 
         ArrayList<Payment> arr = new ArrayList<>();
        for(Payment payment : rep.getPayments())
        {
            if (payment.getOrder().getClient().equals(this) 
                    && payment.getOrder().getType().equals(OrderType.Client)
                    && payment.getStatus().equals(PaymentStatus.PaymentVerified))
                arr.add(payment);
        }
        return arr;
    }
    public ArrayList<Order> getServiceOrders() {//получение списка 
         ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {
            if ( order.getType().equals(OrderType.ServeciCenter)
                    && order.getClient().equals(this))
                arr.add(order);
        }
        return arr;
    }
    public ArrayList<Order> getCLOrders() {//получение списка 
         ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {
            if ( order.getType().equals(OrderType.Client)
                    && order.getClient().equals(this))
                arr.add(order);
        }
        return arr;
    }
    public ArrayList<Order> getEndServiceOrders() {//получение списка 
         ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {
            if ( order.getType().equals(OrderType.ServeciCenter)&&
                    order.getClient().equals(this) 
                    
                    && order.getStatus().equals(OrderStatus.End))
                arr.add(order);
        }
        return arr;
    }
    public ArrayList<Order> getOrders(OrderStatus status) {//запрос новых заказов от магазина в склад
        ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {   
            if(order.getClient() == null)
                continue;
            if (order.getClient().equals(this) && order.getStatus().equals(status))
                arr.add(order);
        }
        return arr;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ArrayList<Order> getOrdersCl(OrderStatus status) {//запрос новых заказов от магазина в склад
        ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {   
            if(order.getClient() == null)
                continue;
            if (order.getClient().equals(this) && order.getStatus().equals(status)&& order.getType().equals(OrderType.Client))
                arr.add(order);
        }
        return arr;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ArrayList<Order> getOrdersSC(OrderStatus status) {//запрос новых заказов от магазина в склад
        ArrayList<Order> arr = new ArrayList<>();
        for(Order order : rep.getOrders())
        {   
            if(order.getClient() == null)
                continue;
            if (order.getClient().equals(this) && order.getStatus().equals(status)&& order.getType().equals(OrderType.ServeciCenter))
                arr.add(order);
        }
        return arr;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //11 - оплата не для склада
    //12 - оплата не для этого магазина
    //0 - успешно
    public int PaytoStore(Payment pay){//оплата магазину
        if (pay.getOrder().getType() != OrderType.Client)
            return 11;
        if (pay.getOrder().getClient() != this)
            return 12;
        
        return pay.pay();
    }
    
    public Boolean newOrderStore(){//создаем заказ на склад
        if (1 > chooseEq.getNum())
            return false;
        Order order = new Order (OrderType.Client, this, chooseEq.getStore(),
            chooseEq,  chooseEq.getCost() );
        rep.addOrder(order);
        return true;
    }
    
    //1 - выбранный заказ ещё не завершен
    //2 - чек не найден
    //3 - номер чека не совпал
    //4 - чек оформлен не на этого клиента
    //0 - заказ на ремонт успешно сформировн
    public int newOrdertoServiceCenter(){//создаем заказ на склад
        if (oldorder.getStatus()!=OrderStatus.End)//Проверяем закончен ли заказ
            return 1;
        check check = rep.getCheckByOrder(oldorder);
        if(check == null)
            return 2;
        int verifycheck = check.Verifycheck(numofcheck, oldorder);
        if(verifycheck == 2)
            return 3;
        if(verifycheck == 3)
            return 4;
        
        Order order = new Order (OrderType.ServeciCenter, this, chooseSC,
            oldorder,  comment );
        rep.addOrder(order);
        return 0;
        
    }

    public Disk getChooseEq() {
        return chooseEq;
    }

    
    public void setChooseEq(Disk chooseEq) {
        this.chooseEq = chooseEq;
    }

    public Order getOldorder() {
        return oldorder;
    }

    public void setOldorder(Order oldorder) {
        this.oldorder = oldorder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ServiceCenter getChooseSC() {
        return chooseSC;
    }

    public void setChooseSC(ServiceCenter chooseSC) {
        this.chooseSC = chooseSC;
    }

    public int getNumofcheck() {
        return numofcheck;
    }

    public void setNumofcheck(int numofcheck) {
        this.numofcheck = numofcheck;
    }
    
    
    
    
}
