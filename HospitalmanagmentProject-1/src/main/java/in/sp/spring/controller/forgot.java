 package in.sp.spring.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.spring.entity.User;
import in.sp.spring.repository.UserRepository;
import in.sp.spring.service.EmailService;
import jakarta.servlet.http.HttpSession;

@Controller
public class forgot {
    
    @Autowired
    private  EmailService emailService;
    @Autowired 
    private BCryptPasswordEncoder bcrypt;
      @Autowired
    private UserRepository repository;

      // ---------------- OTP EMAIL TEMPLATE ----------------
      private String generateOtpEmailTemplate(int otp) {

          return "<!DOCTYPE html>" +
              "<html>" +
              "<head>" +
              "<style>" +
              "body{font-family:Arial;background:#f4f4f4;padding:20px;}" +
              ".card{max-width:600px;margin:auto;background:#fff;" +
              "border-radius:10px;box-shadow:0 4px 15px rgba(0,0,0,.1)}" +
              ".header{background:linear-gradient(135deg,#667eea,#764ba2);" +
              "color:#fff;padding:25px;text-align:center}" +
              ".otp{font-size:42px;font-weight:bold;letter-spacing:8px;" +
              "margin:30px 0;color:#f5576c}" +
              ".content{text-align:center;padding:30px;color:#555}" +
              "</style>" +
              "</head>" +
              "<body>" +
              "<div class='card'>" +
              "<div class='header'><h2>🏥 Hospital OTP</h2></div>" +
              "<div class='content'>" +
              "<p>Use this OTP to reset your password</p>" +
              "<div class='otp'>" + otp + "</div>" +
              "<p>⏱ Valid for 10 minutes</p>" +
              "<p><b>Do not share this OTP</b></p>" +
              "</div>" +
              "</div>" +
              "</body>" +
              "</html>";
      }
      // ----
    // Email ID form open handler
    @RequestMapping("/forgot")
    public String openEmailForm() {
        return "forgot-email-form";
    }
    
    @PostMapping("/send-otp")
    public String Sendotp(@RequestParam("email") String email, HttpSession session) {
        System.out.println("Email: " + email);
        
        // Generate 6 digits random number
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        
        System.out.println("OTP: " + otp);
        
        // Store OTP in session for verification
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);
        
        String subject = "Your OTP Code - Hospital Management System";
        
        // Stylish HTML email template with only OTP data
        String message = "<!DOCTYPE html>" +
            "<html lang='en'>" +
            "<head>" +
            "    <meta charset='UTF-8'>" +
            "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
            "    <title>OTP Verification</title>" +
            "    <style>" +
            "        body {" +
            "            font-family: 'Arial', sans-serif;" +
            "            background-color: #f4f4f4;" +
            "            margin: 0;" +
            "            padding: 0;" +
            "        }" +
            "        .container {" +
            "            max-width: 600px;" +
            "            margin: 20px auto;" +
            "            background: white;" +
            "            border-radius: 10px;" +
            "            overflow: hidden;" +
            "            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);" +
            "        }" +
            "        .header {" +
            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
            "            color: white;" +
            "            padding: 30px 20px;" +
            "            text-align: center;" +
            "        }" +
            "        .header h1 {" +
            "            margin: 0;" +
            "            font-size: 28px;" +
            "            font-weight: 600;" +
            "        }" +
            "        .content {" +
            "            padding: 40px 30px;" +
            "            text-align: center;" +
            "        }" +
            "        .otp-container {" +
            "            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);" +
            "            color: white;" +
            "            padding: 25px;" +
            "            border-radius: 10px;" +
            "            margin: 30px 0;" +
            "            display: inline-block;" +
            "        }" +
            "        .otp-code {" +
            "            font-size: 42px;" +
            "            font-weight: bold;" +
            "            letter-spacing: 10px;" +
            "            font-family: 'Courier New', monospace;" +
            "            text-shadow: 2px 2px 4px rgba(0,0,0,0.2);" +
            "        }" +
            "        .message {" +
            "            color: #666;" +
            "            font-size: 16px;" +
            "            line-height: 1.6;" +
            "            margin-bottom: 25px;" +
            "        }" +
            "        .note {" +
            "            background: #fff9e6;" +
            "            border-left: 4px solid #ffc107;" +
            "            padding: 15px;" +
            "            margin: 20px 0;" +
            "            border-radius: 4px;" +
            "            text-align: left;" +
            "            font-size: 14px;" +
            "            color: #856404;" +
            "        }" +
            "        .footer {" +
            "            background: #f8f9fa;" +
            "            padding: 20px;" +
            "            text-align: center;" +
            "            color: #6c757d;" +
            "            font-size: 14px;" +
            "            border-top: 1px solid #dee2e6;" +
            "        }" +
            "        .validity {" +
            "            background: #e7f5ff;" +
            "            padding: 10px 15px;" +
            "            border-radius: 20px;" +
            "            display: inline-block;" +
            "            margin: 15px 0;" +
            "            font-weight: 600;" +
            "            color: #0056b3;" +
            "        }" +
            "        .logo {" +
            "            font-size: 24px;" +
            "            font-weight: bold;" +
            "            color: white;" +
            "            margin-bottom: 10px;" +
            "        }" +
            "    </style>" +
            "</head>" +
            "<body>" +
            "    <div class='container'>" +
            "        <div class='header'>" +
            "            <div class='logo'>🏥 HOSPITAL</div>" +
            "            <h1>OTP Verification by ✨Shivam✨</h1>" +
            "        </div>" +
            "        <div class='content'>" +
            "            <p class='message'>" +
            "                Please use the following One-Time Password (OTP) and change your password.<br>" +
            "                This OTP is valid for a single use and will expire shortly." +
            "            </p>" +
            "            " +
            "            <div class='otp-container'>" +
            "                <div class='otp-code'>" + otp + "</div>" +
            "            </div>" +
            "            " +
            "            <div class='validity'>⏱️ Valid for 10 minutes</div>" +
            "            " +
            "            <div class='note'>" +
            "                <strong>Important:</strong><br>" +
            "                • Do not share this OTP with anyone<br>" +
            "                • The OTP is for one-time use only<br>" +
            "                • If you didn't request this, please ignore this email" +
            "            </div>" +
            "            " +
            "            <p class='message'>" +
            "                Enter this OTP in the verification page to Change your Password.<br>" +
            "                <strong>Do not reply to this email.</strong>" +
            "            </p>" +
            "        </div>" +
            "        <div class='footer'>" +
            "            <p>© 2024 Hospital Management System. All rights reserved.</p>" +
            "            <p>This is an automated message, please do not reply.</p>" +
            "        </div>" +
            "    </div>" +
            "</body>" +
            "</html>";
        
        String to = email;
        boolean flag = this.emailService.sendEmail(subject, message, to);
        
        if (flag) {
            // Set a timestamp for OTP expiry (10 minutes)
            session.setAttribute("mtotp", otp);
            session.setAttribute("email",email);
            return "varify-otp";
        } else {
            session.setAttribute("message", "Failed to send email. Please check your email address.");
            return "forgot-email-form";
        }
       
    }
     @PostMapping("/varify")
      public String varifyotp(@RequestParam("otp") int otp, HttpSession session) {
    	  int myotp = (int)session.getAttribute("otp");
    	   String email  =(String)session.getAttribute("email"); 
    	   if (myotp==otp) {
    		     
    		 User user=this.repository.getUserByUsername(email);
    		   
    		 if (user== null) {
				//sende error massage 
    			  session.setAttribute("massage","User does'nt exits !" );
    			   return "forgot-email-form";
    			    
			}
    		 else {
    			   
    		 }
			return "password_change_form";
		}
    	   else {
    		    session.setAttribute("message","You hava intered wronge otp !!");
    		   return "varify-otp";
    		    
    	   }
     }
     @PostMapping("/change-password")
      public String changePassword(@RequestParam("newPassword")  String newPassword , 	HttpSession session) {
    	 String email  =(String)session.getAttribute("email");
    	   User user =     this.repository.getUserByUsername(email);
    	       user.setPassword(this.bcrypt.encode(newPassword));
    	       this.repository.save(user);
    	       session.setAttribute("masage","Password changed successfully" );
    	    return "redirect:/log";
    	 
     } 
      
     @PostMapping("/resend-otp")
     public String resendOtp(HttpSession session) {

         String email = (String) session.getAttribute("email");

         if (email == null) {
             session.setAttribute("message", "Session expired. Please try again.");
             return "forgot-email-form";
         }

         // Generate NEW OTP
         Random random = new Random();
         int otp = 100000 + random.nextInt(900000);

         // Update session OTP
         session.setAttribute("otp", otp);

         String subject = "Resent OTP - Hospital Management System";

         // Reuse SAME stylish HTML email
         String message = generateOtpEmailTemplate(otp);

         boolean flag = this.emailService.sendEmail(subject, message, email);

         if (flag) {
             session.setAttribute("message", "New OTP has been sent to your email");
         } else {
             session.setAttribute("message", "Failed to resend OTP. Try again.");
         }

         return "varify-otp";
     }

     
}