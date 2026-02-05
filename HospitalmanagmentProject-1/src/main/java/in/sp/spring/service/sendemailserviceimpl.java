package in.sp.spring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class sendemailserviceimpl implements sendemailservice {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendRegistrationSuccessEmail(String toEmail, String userName) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🎉 Registration Successful");

            String htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        margin: 0;
                        padding: 0;
                        background-color: #f4f6f8;
                        font-family: Arial, sans-serif;
                    }

                    .container {
                        max-width: 600px;
                        margin: auto;
                        background: #ffffff;
                        border-radius: 10px;
                        padding: 25px;
                    }

                    h2 {
                        color: #2c3e50;
                        text-align: center;
                        font-size: 24px;
                    }

                    p {
                        font-size: 16px;
                        color: #555;
                        line-height: 1.6;
                    }

                    .btn {
                        display: inline-block;
                        background: #28a745;
                        color: #ffffff !important;
                        padding: 14px 30px;
                        text-decoration: none;
                        border-radius: 6px;
                        font-size: 16px;
                        margin: 20px auto;
                    }

                    .footer {
                        font-size: 13px;
                        color: #999;
                        text-align: center;
                        margin-top: 20px;
                    }
                </style>
                </head>

                <body>
                    <div style="padding: 20px;">
                        <div class="container">

                            <h2>Welcome, {{name}} 👋</h2>

                            <p>
                                <b>Your registration was successful.</b><br>
                                We’re excited to have you with us!
                            </p>

                            <div style="text-align: center;">
                                <a href="http://localhost:8080/log" class="btn">
                                    Login to Your Account
                                </a>
                            </div>

                            <p style="font-size: 14px; color: #777;">
                                If you didn’t create this account, please ignore this email.
                            </p>

                            <hr>

                            <div class="footer">
                                © 2026 Hospital Management System<br>
                                All rights reserved.
                            </div>

                        </div>
                    </div>
                </body>
                </html>
                """;

            // ✅ SAFE replacement (no formatter, no crash)
            htmlContent = htmlContent.replace("{{name}}", userName);

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
