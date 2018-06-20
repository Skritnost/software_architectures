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
public enum CompanyType {
    Client,ServiceCenter,Store,Stock;

    public static CompanyType strToType(String str) {
        switch (str) {
        case "Client":
            return Client;
        case "ServiceCenter":
            return ServiceCenter;
        case "Store":
            return Store;
         case "Stock":
            return Stock;
        default:
            return null;
        }
    }
    
    public static String typeToStr (CompanyType type) {        
        switch (type) {
        case Client:
            return "Client";
        case ServiceCenter:
            return "ServiceCenter";
        case Store:
            return "Store";
        case Stock:
            return "Stock";
        default:
            return null;
        }
    }
}
