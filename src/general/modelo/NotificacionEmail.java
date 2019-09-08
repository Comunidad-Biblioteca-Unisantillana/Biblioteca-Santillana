package general.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Miguel Fernàndez
 * @creado: 6/08/2019
 * @author Miguel Fernàndez
 * @modificado: 6/08/2019
 */
public class NotificacionEmail {

    public NotificacionEmail() {

    }

    /**
     *
     * @param asunto
     * @param contenido
     * @param emailReceptor
     */
    private void enviarEmail(String asunto, String contenido, String emailReceptor) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.user", "bibliotecaunisantillana@gmail.com");
        properties.put("mail.password", "PostgreSql1234");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        (String) properties.get("mail.smtp.user"),
                        (String) properties.get("mail.password"));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.user")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));
            message.setSubject(asunto);
            message.setText(contenido, "ISO-8859-1", "html");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (AddressException ex) {
            System.out.println("Error al con la dirección del correo del emisor o receptor.\n");
        } catch (MessagingException ex) {
            System.out.println("Error al enviar el correo al usuario.\n");
        }
    }

    /**
     *
     * @param datos
     * @param nombreMensaje
     */
    public void gestionarNotificacion(String datos, String nombreMensaje) {
        String asunto, contenido = "", textoTemp;
        String xDatos[] = datos.split(";");
        File archivo = new File("src/general/recursos/txt/" + nombreMensaje + ".txt");

        if (archivo.exists()) {
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                asunto = lector.readLine();
                textoTemp = lector.readLine();

                while (textoTemp != null) {
                    contenido += textoTemp;
                    textoTemp = lector.readLine();
                }

                contenido = contenido.replaceAll("texto1", xDatos[0]);
                contenido = contenido.replaceAll("texto2", xDatos[1]);
                contenido = contenido.replaceAll("texto3", xDatos[2]);
                contenido = contenido.replaceAll("texto4", xDatos[3]);
                contenido = contenido.replaceAll("texto5", xDatos[4]);
                contenido = contenido.replaceAll("texto6", xDatos[5]);

                if (nombreMensaje.equalsIgnoreCase("mensajePrestamo")
                        || nombreMensaje.equalsIgnoreCase("mensajeRetencion")) {
                    contenido = contenido.replaceAll("texto7", xDatos[6]);
                }

                if (nombreMensaje.equalsIgnoreCase("mensajePrestamo")) {
                    if (xDatos[7].equalsIgnoreCase("general")) {
                        contenido = contenido.replaceAll("texto8", "Por favor recuerde que debe devolverlo o renovarlo "
                                + "dentro de las fechas correspondientes para evitar multas. Si lo desea puede renovar "
                                + "el material desde su cuenta (ingresando a préstamos actuales), digitando su password "
                                + "el cual es su código de usuario como estudiante o cédula como otro tipo de usuario de "
                                + "la misma (sin puntos ni espacios) y hacer la renovación máximo el mismo día de vencimiento; "
                                + "tenga en cuenta que si actualmente tiene alguna multa cargada en su cuenta o el libro fué "
                                + "reservado, o habrá llegado al límite de renovaciones, no podrá realizar la renovación.");
                    } else {
                        contenido = contenido.replaceAll("texto8", "Por favor recuerde que debe devolverlo dentro de las fechas "
                                + "correspondientes para evitar multas.");
                    }
                } else if (nombreMensaje.equalsIgnoreCase("mensajeRetencion")) {
                    contenido = contenido.replaceAll("texto8", xDatos[7]);
                }

                enviarEmail(asunto, contenido, xDatos[8]);
            } catch (FileNotFoundException ex) {
                System.out.println("Error archivo.");
            } catch (IOException ex) {
                System.out.println("Error I/O.");
            }
        } else {
            System.out.println("Error el archivo no existe");
        }
    }

}
