// DoctorService.java
package in.sp.spring.service;

import in.sp.spring.entity.DoctorEntity;
import java.util.List;

public interface DoctorSaveService {
    
    // Create a new doctor
    DoctorEntity addDoctor(DoctorEntity doctor);
    
    // Get all doctors
    List<DoctorEntity> getAllDoctors();
    
    // Get doctor by ID
    DoctorEntity getDoctorById(int id);
    
    // Get doctor by email
    DoctorEntity getDoctorByEmail(String email);
    
    // Update doctor
    DoctorEntity updateDoctor(int id, DoctorEntity doctor);
    
    // Delete doctor
    void deleteDoctor(int id);
    
    // Change doctor status
    DoctorEntity changeStatus(int id, String status);
    
    // Get doctors by specialization
    List<DoctorEntity> getDoctorsBySpecialization(String specialization);
    
    // Get doctors by department
    List<DoctorEntity> getDoctorsByDepartment(String department);
}