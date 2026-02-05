package in.sp.spring.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import in.sp.spring.entity.Doctor;

import in.sp.spring.repository.DoctorRepository;

import in.sp.spring.service.doctorEmail;
import jakarta.servlet.http.HttpSession;

@Controller
public class Doctorforgot {
	 @Autowired
       private doctorEmail  doctorEmail;
        @Autowired
      private DoctorRepository doctorRepository;
      
	@Autowired
	 private BCryptPasswordEncoder crypt;
	 
	private String generateOtpEmailTemplate(int otp) {

	    return "<!DOCTYPE html>" +
	        "<html><head><style>" +
	        "body{font-family:Arial;background:#f4f4f4;}" +
	        ".box{max-width:600px;margin:20px auto;background:#fff;" +
	        "border-radius:10px;box-shadow:0 4px 15px rgba(0,0,0,0.1)}" +
	        ".head{background:linear-gradient(135deg,#667eea,#764ba2);" +
	        "color:#fff;padding:25px;text-align:center}" +
	        ".otp{font-size:40px;letter-spacing:8px;font-weight:bold;" +
	        "margin:30px 0;color:#f5576c}" +
	        ".content{text-align:center;padding:30px;color:#555}" +
	        "</style></head>" +
	        "<body>" +
	        "<div class='box'>" +
	        "<div class='head'><h2>🏥 Hospital OTP</h2></div>" +
	        "<div class='content'>" +
	        "<p>Use this OTP to reset your password:</p>" +
	        "<div class='otp'>" + otp + "</div>" +
	        "<p>Valid for 10 minutes</p>" +
	        "<p><b>Do not share this OTP</b></p>" +
	        "</div></div></body></html>";
	}

	 @RequestMapping("/doctorforgot")
	  public String openEmailFor() {
		 return "doctor-forgot-email-form";
		 
	 }
	

    @PostMapping("/doctor-send-otp")
    public String Sendotp(@RequestParam("username") String username, HttpSession session) {
        System.out.println("Username: " + username);
        
        // Generate 6 digits random number
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        
        System.out.println("OTP: " + otp);
        
        // Store OTP in session for verification
        session.setAttribute("otp", otp);
        session.setAttribute("username", username);
        
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
            "            <h1>OTP Verification for Doctor</h1>" +
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
            "            <div class='validity'>Valid for 10 minutes</div>" +
            "            " +
            "            <div class='note'>" +
            "                <strong>Important:</strong><br>" +
            "                • Do not share this OTP with anyone<br>" +
            "                • The OTP is for one-time use only<br>" +
            "                • If you didn't request this, please ignore this email" +
            "            </div>" +
            "            " +
            "            <p class='message'>" +
            "                Enter this OTP in the verification page to Change  your Password.<br>" +
            "                <strong>Do not reply to this email.</strong>" +
            "            </p>" +
            "        </div>" +
            "        <div class='footer'>" +
            "            <p>© 2026 Hospital Management System. All rights reserved.</p>" +
            "            <p>This is an automated message, please do not reply.</p>" +
            "        </div>" +
            "    </div>" +
            "</body>" +
            "</html>";
        
        String to = username;
        boolean flag = this.doctorEmail.sendEmail(subject, message, to);
        
        if (flag) {
            // Set a timestamp for OTP expiry (10 minutes)
            session.setAttribute("mtotp", otp);
            session.setAttribute("username",username);
            return "doctor-varify-otp";
        } else {
            session.setAttribute("message", "Failed to send email. Please check your email address.");
            return "doctor-forgot-email-form";
        }
       
    }
     @PostMapping("/doctor-varify")
      public String varifyotp(@RequestParam("otp") int otp, HttpSession session) {
    	  int myotp = (int)session.getAttribute("otp");
    	   String username  =(String)session.getAttribute("username"); 
    	   if (myotp==otp) {
    		     
    		 Doctor user=this.doctorRepository.getDoctorByUsername(username);
    		   
    		 if (user== null) {
				//sende error massage 
    			  session.setAttribute("mssage","User does't exits this email !" );
    			   return "doctor-forgot-email-form";
    			    
			}
    		 else {
    			   
    		 }
			return "doctor_password_change_password";
		}
    	   else {
    		    session.setAttribute("message","You hava intered wronge otp !!");
    		   return "doctor-varify-otp";
    		    
    	   }
     }
     @PostMapping("/doctor-change-password")
      public String changePassword(@RequestParam("newPassword")  String newPassword , 	HttpSession session) {
    	 String username  =(String)session.getAttribute("username");
    	  // Admin use =     this.adminRepository.getAdminByUsername(username);
    	  Doctor use = this.doctorRepository.getDoctorByUsername(username);
    	     //use.setPassword(this.crypt.encode(newPassword));
    	   use.setPassword(this.crypt.encode(newPassword));
    	       this.doctorRepository.save(use);
    	       session.setAttribute("masage","Password changed successfully" );
    	    return "redirect:/doctorlog";
    	 
     }
     
     
     @PostMapping("/rrresend-otp")
     public String resendOtp(HttpSession session) {

         String username = (String) session.getAttribute("username");

         if (username == null) {
             session.setAttribute("message", "Session expired. Please try again.");
             return "docto-forgot-email-form";
         }

         // Generate NEW OTP
         Random random = new Random();
         int otp = 100000 + random.nextInt(900000);

         // Update session OTP
         session.setAttribute("otp", otp);

         String subject = "Resent OTP - Hospital Management System";

         // Reuse SAME stylish HTML email
         String message = generateOtpEmailTemplate(otp);

         boolean flag = this.doctorEmail.sendEmail(subject, message, username);

         if (flag) {
             session.setAttribute("message", "New OTP has been sent to your email");
         } else {
             session.setAttribute("message", "Failed to resend OTP. Try again.");
         }

         return "doctor-varify-otp";
     }

     
     
}
