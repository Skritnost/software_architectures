/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.ArrayMemberRepository;
import data.DBMemberRepository;
import data.MemberRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Bocman
 */
public class Order {
    private int id;
    private OrderType type;
    private int price = -1;
    private int num = 0;
    private Payment payment;
    private String commentService; 
    
    private OrderStatus status;
    private Disk disk;
    private StockDisk stockdisk;
    private Client client;
    private Order oldorder;
    private Stock stock;
    private Store store;
    private ServiceCenter servicecenter = null;
    
    private String storeName = "lol" ;
    //private MemberRepository rep = new ArrayMemberRepository();
    protected MemberRepository rep = new DBMemberRepository();
    private ArrayList <Order> orders;
    
    public Order (){};
    
    //Заявка покупка клиент-магазин
    public Order (OrderType type, Client client, Store store,
            Disk disk, Integer price) {
        this.num = 1;
        this.type = type;
        this.client = client;
        this.store = store;
        this.disk = disk;
        this.storeName = store.getName();
        this.price = price;
        this.status = OrderStatus.New;
    }
    //Заявка покупка клиент-магазин c ордером
    public Order (OrderType type, Client client, Store store,
            Disk disk, Integer price, OrderStatus st) {
        this.num = 1;
        this.type = type;
        this.client = client;
        this.store = store;
        this.disk = disk;
        this.storeName = store.getName();
        this.price = price;
        this.status = st;
    }
    //Заявка покупка магазин склад
    public Order (OrderType type, Store store, Stock stock,
            StockDisk stockdisk,Integer num, Integer price) {
        
        this.type = type;
        this.stock = stock;
        this.store = store;
        this.stockdisk = stockdisk;
        this.num = num;
        this.price = price;
        this.status = OrderStatus.New;
        //this.storeName = this.store.getName();
    }
    //Заявка покупка магазин склад and status
    public Order (OrderType type, Store store, Stock stock,
            StockDisk stockdisk,Integer num, Integer price, OrderStatus st) {
        
        this.type = type;
        this.stock = stock;
        this.store = store;
        this.stockdisk = stockdisk;
        this.num = num;
        this.price = price;
        this.status = st;
        //this.storeName = this.store.getName();
    }
    //Заявка ремонт техники клиент - сервис центр 
    public Order (OrderType type, Client client, ServiceCenter servicecenter
            ,Order oldorder, String com) {
        
        this.type = type;
        this.client = client;
        this.store = oldorder.getStore();
        this.storeName = store.getName();
        this.servicecenter = servicecenter;
        this.oldorder = oldorder;
        this.price = 0;
        this.commentService = com;
        this.status = OrderStatus.New;
    }
    //Заявка ремонт техники клиент - сервис центр  and order stat
    public Order (OrderType type, Client client, ServiceCenter servicecenter
            ,Order oldorder, String com, OrderStatus st) {
        
        this.type = type;
        this.client = client;
        this.store = oldorder.getStore();
        this.storeName = store.getName();
        this.servicecenter = servicecenter;
        this.oldorder = oldorder;
        this.price = 0;
        this.commentService = com;
        this.status = st;
    }
    
    
    //Вызвать, когда тендер утвержден
    public Boolean createPayment() {
        setPayment(new Payment(this));
        Boolean res = rep.addPayment(getPayment());
        update();
        return res;
        
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

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store Store) {
        this.store = Store;
    }

    public ServiceCenter getServicecenter() {
        return servicecenter;
    }

    public void setServicecenter(ServiceCenter servicecenter) {
        this.servicecenter = servicecenter;
    }

    public MemberRepository getRep() {
        return rep;
    }

    public void setRep(MemberRepository rep) {
        this.rep = rep;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public StockDisk getStockdisk() {
        return stockdisk;
    }

    public void setStockdisk(StockDisk stockdisk) {
        this.stockdisk = stockdisk;
    }

    public Order getOldorder() {
        return oldorder;
    }

    public void setOldorder(Order oldorder) {
        this.oldorder = oldorder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCommentService() {
        return commentService;
    }

    public void setCommentService(String commentService) {
        this.commentService = commentService;
    }
     
    
    
}
