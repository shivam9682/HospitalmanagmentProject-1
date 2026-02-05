// DoctorServiceImpl.java
package in.sp.spring.service;

import in.sp.spring.entity.DoctorEntity;

import in.sp.spring.repository.DoctorSaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorSaveServiceimpl implements DoctorSaveService {

    @Autowired
    private DoctorSaveRepository doctorRepository;

    @Override
    public DoctorEntity addDoctor(DoctorEntity doctor) {
        // Check if email already exists
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Doctor with email " + doctor.getEmail() + " already exists");
        }
        
        // Set default status if not provided
        if (doctor.getStatus() == null || doctor.getStatus().isEmpty()) {
            doctor.setStatus("ACTIVE");
        }
        
        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity getDoctorById(int id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorEntity getDoctorByEmail(String email) {
        DoctorEntity doctor = doctorRepository.findByEmail(email);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found with email: " + email);
        }
        return doctor;
    }

    @Override
    public DoctorEntity updateDoctor(int id, DoctorEntity doctorDetails) {
        DoctorEntity existingDoctor = getDoctorById(id);
        
        // Check if email is being changed and if new email already exists for another doctor
        if (!existingDoctor.getEmail().equals(doctorDetails.getEmail()) && 
            doctorRepository.existsByEmail(doctorDetails.getEmail())) {
            throw new RuntimeException("Email " + doctorDetails.getEmail() + " is already taken");
        }
        
        // Update fields
        existingDoctor.setName(doctorDetails.getName());
        existingDoctor.setEmail(doctorDetails.getEmail());
        existingDoctor.setPhone(doctorDetails.getPhone());
        existingDoctor.setSpecialization(doctorDetails.getSpecialization());
        existingDoctor.setDepartment(doctorDetails.getDepartment());
        existingDoctor.setQualification(doctorDetails.getQualification());
        existingDoctor.setExperience(doctorDetails.getExperience());
        existingDoctor.setConsultingHours(doctorDetails.getConsultingHours());
        existingDoctor.setRoomNumber(doctorDetails.getRoomNumber());
        
        // Status can be updated using changeStatus method
        // existingDoctor.setStatus(doctorDetails.getStatus());
        
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(int id) {
        DoctorEntity doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }

    @Override
    public DoctorEntity changeStatus(int id, String status) {
        DoctorEntity doctor = getDoctorById(id);
        doctor.setStatus(status);
        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorEntity> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public List<DoctorEntity> getDoctorsByDepartment(String department) {
        return doctorRepository.findByDepartment(department);
    }
}