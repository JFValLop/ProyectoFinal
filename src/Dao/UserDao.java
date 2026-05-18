/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conexion.CreateConnection;
import Modelo.User;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import email.servicioemail;

/**
 *
 * @author juanf
 */
public class UserDao {
    
    String sql = "";
    CreateConnection cn;

    public UserDao() throws IOException {
        this.cn = new CreateConnection();
    }
    
    //insertar ususario nuevo
    
    public boolean insertarUsuario(User u){
        sql = "insert into public.usuario (username, password, rol)"
                + " Values (?,?,?)";
        try{ Connection conn = cn.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql);
              ps.setString(1, u.getUsername());
              ps.setString(2, u.getPassword());
              ps.setString(3, u.getRoll());
              ps.executeUpdate();
              ps.close();
              conn.close();
              return true;
        }catch (SQLException e){
            System.out.println("error insertar: " + e.getMessage());
            return false;
        }
    }
    
    //login de usuario, si usuario y contra = true entonces se devuelve true sino false
    
    public boolean loginUsuario(String username, String password) {
    sql = "SELECT username FROM public.usuario WHERE username = ? AND password = ?";
    try {
        Connection conn = cn.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        boolean encontrado = rs.next(); 

        rs.close();
        ps.close();
        conn.close();
        return encontrado;

    } catch (SQLException e) {
        System.out.println("error login: " + e.getMessage());
        return false;
    }
}
    
    // verificar que el correo existe
public boolean existeCorreo(String correo) {
    sql = "SELECT email FROM public.usuario WHERE email = ?";
    try {
        Connection conn = cn.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        conn.close();
        return existe;
    } catch (SQLException e) {
        System.out.println("error existe correo: " + e.getMessage());
        return false;
    }
}

// cambiar contraseña por correo
public boolean cambiarPassword(String correo, String nuevaPassword) {
    sql = "UPDATE public.usuario SET password = ? WHERE email = ?";
    try {
        Connection conn = cn.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nuevaPassword);
        ps.setString(2, correo);
        ps.executeUpdate();
        ps.close();
        conn.close();
        return true;
    } catch (SQLException e) {
        System.out.println("error cambiar password: " + e.getMessage());
        return false;
    }
}

    
    
}
