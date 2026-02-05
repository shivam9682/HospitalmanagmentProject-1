package in.sp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.spring.service.AdminDoctorService;



@Controller
public class AdminLoginController {

    @Autowired
    private AdminDoctorService loginService;

    @GetMapping("/adminlog")
    public String adminLoginPage() {
        return "adminlogin";
    }

    @PostMapping("/admlogin")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {

        if (loginService.adminLogin(username, password)) {
        	return "redirect:/showappoitmentadmin.html";

        }
        model.addAttribute("error", "Invalid Admin Credentials");
        return "adminlogin";
        
    }

    @GetMapping("/doctorlog")
    public String doctorLoginPage() {
        return "doctorlogin";
    }

    @PostMapping("/doctorlogi")
    public String doctorLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model ) {

        if (loginService.doctorLogin(username, password)) {
        	return "redirect:/showappoitmentdoctor.html";

        }
        model.addAttribute("error", "Invalid Doctor Credentials");
        return "doctorlogin";
    }
}
