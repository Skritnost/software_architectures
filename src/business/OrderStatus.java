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
public enum OrderStatus {
    New,Accepted, End;
    
    public static OrderStatus strToType(String str) {
        switch(str) {
            case "New":
                return New;
            case "Accepted":
                return Accepted;
            case "End":
                return End;
            default:
                return null;                
        }
    }
    
    public static String typeToString(OrderStatus status) {
        switch(status) {
            case New:
                return "New";
            case Accepted:
                return "Accepted";
            case End:
                return "End";
            default:
                return "";
        }
    }
}
