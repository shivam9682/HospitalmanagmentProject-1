package in.sp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.spring.entity.User;
import in.sp.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userserivce;

    // Open Registration Page
    @GetMapping("/reg")
    public String openReg(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle Registration Form
    @PostMapping("/regForm")
    public String regData(@ModelAttribute("user") User user, Model model) {
 
        boolean status = userserivce.registerUser(user);
        if (status) {
            model.addAttribute("success", "Successfully registered!");
        } else {
            model.addAttribute("errMsg", "Email already exists or error occurred!");
        }
        return "register";
    }
       
    // Open Login Page
    @GetMapping("/log")
    public String openLog(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // Handle Login Form
    @PostMapping("/logForm")
    public String submit(@ModelAttribute("user") User user,
                         Model model,
                         HttpSession session) {

        User validUser = userserivce.loginUser(
                user.getEmail(),
                user.getPassword()
        );

        if (validUser != null) {

            // 🔐 Store user in session
            session.setAttribute("loggedUser", validUser);

            return "index2";
        } else {
            model.addAttribute("error", "Email & Password didn't match!");
            return "login";
        }
    }

    @GetMapping("/logout") 
     public String logout(HttpServletRequest request) {
    	  HttpSession session =  request.getSession(false);
    	   if (session != null) {
    		    session.invalidate();
			
		}
    	   return "redirect:/index.html";
    	   
     } 

    @GetMapping("/ind")  // or /home or whatever page
    public String dashboard() {
        return "index2"; // this resolves to dashboard.html in templates folder
    }
 
}
