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
public abstract class User {
    
    private int id;
    private String first_name;
    private String user_name;
    private String email;
    private String type;
    private int password;
    
    public User(int id,String first_name,String user_name,String password,String email,String type){
        this.id=id;
        this.user_name=user_name;
        this.first_name=first_name;
        this.type=type;
        this.email=email;
        
        this.setPassword(password);
    }
    
    public abstract LinkedList<String> showAvailableContent();

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        
        this.password = password.hashCode();  //encrypts password for security
    }

    
    
 
    
    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getPassword() {
        return password;
    }
    
    
    
}
