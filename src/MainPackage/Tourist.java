/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import java.util.LinkedList;

/**
 *
 * @author mohaned
 */
public class Tourist extends User {

    public Tourist(int id, String first_name, String user_name, String password, String email, String type) {
        super(id, first_name, user_name, password, email, type);
    }

    @Override
    public LinkedList<String> showAvailableContent() {
        
        LinkedList<String> content= new LinkedList<>();
        content.add("hotels");
        content.add("bus");
        content.add("attractions");
        
        return content;
    }

   
    
}
