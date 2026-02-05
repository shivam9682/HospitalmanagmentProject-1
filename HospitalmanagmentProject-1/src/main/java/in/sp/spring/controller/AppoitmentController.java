package in.sp.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.sp.spring.entity.Appoitment;
import in.sp.spring.entity.User;
import in.sp.spring.service.AppoitmentMailService;
import in.sp.spring.service.AppoitmentService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/appoitment")
@CrossOrigin("*")
public class AppoitmentController {

    @Autowired
    private AppoitmentService service;

    @Autowired
    private AppoitmentMailService appoitmentMailService;

    // ✅ USER – Book appointment (ONLY ONE POST)
   /* @PostMapping
    public Appoitment bookAppointment(@RequestBody Appoitment appointment) {

        // 1️⃣ Save appointment
        Appoitment saved = service.book(appointment);

        // 2️⃣ Send confirmation email
        appoitmentMailService.sendAppointmentConfirmation(
                saved.getEmail(),
                saved.getFullname(),
                saved.getDate().toString(),
                saved.getTime().toString()
        );

        return saved;
    }*/
    
    
    @PostMapping
    public Appoitment bookAppointment(
            @RequestBody Appoitment appointment,
            HttpSession session) {

        // 🔐 Get logged-in user
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        // 🔗 Link appointment to user
        appointment.setUserId(user.getId());

        Appoitment saved = service.book(appointment);

        appoitmentMailService.sendAppointmentConfirmation(
                saved.getEmail(),
                saved.getFullname(),
                saved.getDate(),
                saved.getTime()
        );

        return saved;
    }


    // ADMIN – View all
    @GetMapping("/admin")
    public List<Appoitment> adminAll() {
        return service.getAll();
    }

    // DOCTOR – View own appointments
    @GetMapping("/doctor/{doctorId}")
    public List<Appoitment> doctorAppointments(@PathVariable String doctorId) {
        return service.getByDoctor(doctorId);
    }

    // ADMIN / DOCTOR – Update status
    @PatchMapping("/{id}/status")
    public Appoitment updateStatus(
            @PathVariable int id,
            @RequestParam String status) {

        return service.updateStatus(id, status);
    }

    // ADMIN / DOCTOR – Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Appointment deleted successfully";
    }
    
    @GetMapping("/my")
    public List<Appoitment> myAppointments(HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        return service.getByUser(user.getId());
    }

    
}
