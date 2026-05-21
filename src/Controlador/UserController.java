/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ezayr
 */
import Dao.UserDao;
import Modelo.User;
import email.servicioemail;
import java.io.IOException;
import java.util.Random;

public class UserController {

    private final UserDao dao; // Corregido: era "User dao" en lugar de "UserDao dao"

    public UserController() throws IOException {
        dao = new UserDao();
    }

    // Login por username o email
    public boolean login(String usuarioOEmail, String password) {
        if (usuarioOEmail == null || usuarioOEmail.isEmpty()
                || password == null || password.isEmpty()) {
            return false;
        }
        return dao.loginUsuario(usuarioOEmail, password)
                || dao.loginEmail(usuarioOEmail, password);
    }

    // Registrar usuario nuevo
    public boolean registrarUsuario(String username, String password, String roll) {
        if (username == null || username.isEmpty()
                || password == null || password.isEmpty()
                || roll == null || roll.isEmpty()) {
            return false;
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setRoll(roll);
        return dao.insertarUsuario(u);
    }

    // Generar código de 4 dígitos para recuperación
    private int generarCodigo() {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

    // Enviar código de recuperación al correo si existe
    public int recuperacion(String email) {
        if (email == null || email.isEmpty()) {
            return 0;
        }

        if (dao.existeCorreo(email)) {
            int codigo = generarCodigo();
            try {
                servicioemail s = new servicioemail();
                s.enviarCodigo(email, String.valueOf(codigo));
            } catch (IOException e) {
                System.out.println("Error al enviar email: " + e.getMessage());
                return 0; // Si falla el envío, no devolver el código
            }
            return codigo;
        } else {
            return 0;
        }
    }

    // Cambiar contraseña dado un correo verificado
    public boolean cambiarPassword(String email, String nuevaPassword) {
        if (email == null || email.isEmpty()
                || nuevaPassword == null || nuevaPassword.isEmpty()) {
            return false;
        }
        return dao.cambiarPassword(email, nuevaPassword);
    }
}
