package in.sp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send plain OTP email
     */
    public boolean sendOtpEmail(String to, String otp) {
        try {
            String subject = "Your OTP Code";
            String message = "Hello,\n\nYour OTP is: " + otp + "\n\nDo not share it with anyone.";

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setText(message, false); // false = plain text
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(mailSender.getUsername());

            mailSender.send(mimeMessage);
            System.out.println("OTP email sent to " + to);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Send HTML email
     */
    public boolean sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML
            helper.setFrom(mailSender.getUsername());

            mailSender.send(mimeMessage);
            System.out.println("HTML email sent to " + to);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


