package in.sp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String message, String to) {

        try {
            // create message
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            // true = HTML allowed
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("ssivamyadav0123@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);

            mailSender.send(mimeMessage);

            System.out.println("EMAIL SENT SUCCESS");
            return true;

        } catch (Exception e) {
            System.out.println("EMAIL ERROR");
            e.printStackTrace();
            return false;
        }
    }
}
