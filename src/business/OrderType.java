/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Bocman
 */
public enum OrderType {
    Client,Store, ServeciCenter;
    
    public static OrderType strToType(String str) {
        switch(str) {
            case "Client":
                return Client;
            case "Store":
                return Store;
            case "ServeciCenter":
                return ServeciCenter;
            default:
                return null;                
        }
    }
    
    public static String typeToString(OrderType status) {
        switch(status) {
            case Client:
                return "Client";
            case Store:
                return "Store";
            case ServeciCenter:
                return "ServeciCenter";
            default:
                return "";
        }
    }
}
