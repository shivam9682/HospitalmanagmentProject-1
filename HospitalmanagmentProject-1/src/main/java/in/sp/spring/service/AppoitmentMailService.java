package in.sp.spring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class AppoitmentMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAppointmentConfirmation(
            String toEmail,
            String fullName,
            LocalDate localDate,
            LocalTime localTime) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("📅 Appointment Confirmed | Hospital Management");

            String html = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <style>
                    body {
                      margin: 0;
                      padding: 0;
                      background-color: #f4f6f8;
                      font-family: Arial, Helvetica, sans-serif;
                    }
                    .wrapper {
                      width: 100%%;
                      padding: 20px 0;
                    }
                    .container {
                      max-width: 600px;
                      margin: auto;
                      background: #ffffff;
                      border-radius: 12px;
                      overflow: hidden;
                      box-shadow: 0 8px 24px rgba(0,0,0,0.08);
                    }
                    .header {
                      background: linear-gradient(135deg, #0d6efd, #198754);
                      color: #ffffff;
                      padding: 25px;
                      text-align: center;
                    }
                    .header h1 {
                      margin: 0;
                      font-size: 24px;
                    }
                    .content {
                      padding: 30px;
                      color: #333333;
                    }
                    .content h2 {
                      margin-top: 0;
                      color: #0d6efd;
                    }
                    .details {
                      background: #f8f9fa;
                      border-radius: 8px;
                      padding: 15px 20px;
                      margin: 20px 0;
                    }
                    .details p {
                      margin: 8px 0;
                      font-size: 15px;
                    }
                    .btn {
                      display: inline-block;
                      margin-top: 20px;
                      background: #198754;
                      color: #ffffff !important;
                      padding: 12px 28px;
                      text-decoration: none;
                      border-radius: 6px;
                      font-size: 15px;
                    }
                    .footer {
                      background: #f1f3f5;
                      text-align: center;
                      padding: 15px;
                      font-size: 12px;
                      color: #6c757d;
                    }

                    /* Mobile */
                    @media only screen and (max-width: 600px) {
                      .content {
                        padding: 20px;
                      }
                      .header h1 {
                        font-size: 20px;
                      }
                    }
                  </style>
                </head>

                <body>
                  <div class="wrapper">
                    <div class="container">

                      <div class="header">
                        <h1>Appointment Confirmed</h1>
                      </div>

                      <div class="content">
                        <h2>Hello %s 👋</h2>

                        <p>
                          Your appointment has been <b>successfully booked</b>.
                          Please find the details below:
                        </p>

                        <div class="details">
                          <p><b>📅 Date:</b> %s</p>
                          <p><b>⏰ Time:</b> %s</p>
                        </div>

                        <p>
                          We look forward to serving you. Please arrive
                          <b>10 minutes early</b> for smooth processing.
                        </p>

                        <a href="http://localhost:8080/log" class="btn">
                          Login to Your Account
                        </a>
                      </div>

                      <div class="footer">
                        © 2026 Hospital Management System<br>
                        All rights reserved
                      </div>

                    </div>
                  </div>
                </body>
                </html>
                """.formatted(fullName, localDate, localTime);

            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
