package in.sp.spring.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class AppoitmentMailService {

    public void sendAppointmentConfirmation(
            String toEmail,
            String fullName,
            LocalDate localDate,
            LocalTime localTime) {

        String from = "ssivamayadav0123@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ssivamayadav0123@gmail.com", "zyla qwwz rlrt nbri");
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("📅 Appointment Confirmed | Hospital Management");

            String html = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <style>
                    body { margin:0; padding:0; background-color:#f4f6f8; font-family:Arial, Helvetica, sans-serif; }
                    .wrapper { width:100%%; padding:20px 0; }
                    .container { max-width:600px; margin:auto; background:#ffffff; border-radius:12px; overflow:hidden; box-shadow:0 8px 24px rgba(0,0,0,0.08); }
                    .header { background:linear-gradient(135deg, #0d6efd, #198754); color:#fff; padding:25px; text-align:center; }
                    .header h1 { margin:0; font-size:24px; }
                    .content { padding:30px; color:#333; }
                    .content h2 { margin-top:0; color:#0d6efd; }
                    .details { background:#f8f9fa; border-radius:8px; padding:15px 20px; margin:20px 0; }
                    .details p { margin:8px 0; font-size:15px; }
                    .btn { display:inline-block; margin-top:20px; background:#198754; color:#fff !important; padding:12px 28px; text-decoration:none; border-radius:6px; font-size:15px; }
                    .footer { background:#f1f3f5; text-align:center; padding:15px; font-size:12px; color:#6c757d; }
                    @media only screen and (max-width:600px) { .content { padding:20px; } .header h1 { font-size:20px; } }
                  </style>
                </head>
                <body>
                  <div class="wrapper">
                    <div class="container">
                      <div class="header"><h1>Appointment Confirmed</h1></div>
                      <div class="content">
                        <h2>Hello %s 👋</h2>
                        <p>Your appointment has been <b>successfully booked</b>. Please find the details below:</p>
                        <div class="details">
                          <p><b>📅 Date:</b> %s</p>
                          <p><b>⏰ Time:</b> %s</p>
                        </div>
                        <p>We look forward to serving you. Please arrive <b>10 minutes early</b> for smooth processing.</p>
                        <a href="http://localhost:8080/log" class="btn">Login to Your Account</a>
                      </div>
                      <div class="footer">© 2026 Hospital Management System<br>All rights reserved</div>
                    </div>
                  </div>
                </body>
                </html>
                """.formatted(fullName, localDate, localTime);

            message.setContent(html, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Appointment email sent successfully to " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
