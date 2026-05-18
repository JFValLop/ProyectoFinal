/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author juanf
 */
public class servicioemail {
    
    private String remitente;
    private String clave;
    private String host;
    private String port;

    public servicioemail() throws IOException {
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader()
                            .getResourceAsStream("email.properties");
        props.load(input);

        this.remitente = props.getProperty("mail.remitente");
        this.clave     = props.getProperty("mail.clave");
        this.host      = props.getProperty("mail.host");
        this.port      = props.getProperty("mail.port");
    }

    public boolean enviarCodigo(String destinatario, String codigo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(remitente));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject("Código de recuperación");
            msg.setText("Tu código de recuperación es: " + codigo);
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            System.out.println("Error email: " + e.getMessage());
            return false;
        }
    }
    
}
