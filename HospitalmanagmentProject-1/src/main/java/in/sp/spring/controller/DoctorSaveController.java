// DoctorController.java
package in.sp.spring.controller;

import in.sp.spring.entity.DoctorEntity;
import in.sp.spring.service.DoctorSaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorSaveController {

    @Autowired
    private DoctorSaveService doctorService;

    // 1. Add Doctor (POST)
    @PostMapping("/add")
    public ResponseEntity<Object> addDoctor(@RequestBody DoctorEntity doctor) {
        try {
            DoctorEntity savedDoctor = doctorService.addDoctor(doctor);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor added successfully");
            response.put("data", savedDoctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 2. Get All Doctors (GET)
    @GetMapping("/all")
    public ResponseEntity<Object> getAllDoctors() {
        List<DoctorEntity> doctors = doctorService.getAllDoctors();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", doctors.size());
        response.put("data", doctors);
        return ResponseEntity.ok(response);
    }

    // 3. Get Doctor by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable int id) {
        try {
            DoctorEntity doctor = doctorService.getDoctorById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", doctor);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // 4. Update Doctor (PUT)
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable int id, @RequestBody DoctorEntity doctor) {
        try {
            DoctorEntity updatedDoctor = doctorService.updateDoctor(id, doctor);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor updated successfully");
            response.put("data", updatedDoctor);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 5. Delete Doctor (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable int id) {
        try {
            doctorService.deleteDoctor(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // 6. Change Doctor Status (PATCH)
    @PatchMapping("/status/{id}")
    public ResponseEntity<Object> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            DoctorEntity doctor = doctorService.changeStatus(id, status);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor status updated to " + status);
            response.put("data", doctor);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // 7. Get Doctors by Specialization (GET)
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<Object> getDoctorsBySpecialization(@PathVariable String specialization) {
        List<DoctorEntity> doctors = doctorService.getDoctorsBySpecialization(specialization);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", doctors.size());
        response.put("data", doctors);
        return ResponseEntity.ok(response);
    }
        
    // 8. Get Doctors by Department (GET)
    @GetMapping("/department/{department}")
    public ResponseEntity<Object> getDoctorsByDepartment(@PathVariable String department) {
        List<DoctorEntity> doctors = doctorService.getDoctorsByDepartment(department);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", doctors.size());
        response.put("data", doctors);
        return ResponseEntity.ok(response);
    }
}