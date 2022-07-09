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
public class Admin extends User {

    public Admin(int id, String first_name, String user_name, String password, String email, String type) {
        super(id, first_name, user_name, password, email, type);
    }

    
    public String addItem(){
        String sql="SELECT *  FROM  WHERE ";
        return sql;
    }
     public String deleteItem(){
        String sql="Delete *  FROM  WHERE ";
        return sql;
    }
      public String updateItem(){
        String sql="Update *  FROM  WHERE ";
        return sql;
    }
       public String searchItem(){
        String sql="Select *  FROM  WHERE ";
        return sql;
    }
    
    
    @Override
    public LinkedList<String> showAvailableContent() {
        
        LinkedList<String> content= new LinkedList<>();
        content.add("bcenters");
        content.add("library");
        content.add("industries");
        content.add("uni");
        content.add("bus");
        content.add("hotels");
        content.add("jobs");
        content.add("attractions");
        
        return content;
    }
    
}
