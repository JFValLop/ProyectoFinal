/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juanf
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private String roll;
    private boolean estado;
    private String email;

    public User() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.roll = "";
        this.estado = true;
        this.email = "";
    }

    public User(String username, String password, String roll, boolean estado, String email) {
        this.username = username;
        this.password = password;
        this.roll = roll;
        this.estado = estado;
        this.email = email;
    }

    public User(int id, String username, String password, String roll, boolean estado, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roll = roll;
        this.estado = estado;
        this.email = email;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roll
     */
    public String getRoll() {
        return roll;
    }

    /**
     * @param roll the roll to set
     */
    public void setRoll(String roll) {
        this.roll = roll;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
}
