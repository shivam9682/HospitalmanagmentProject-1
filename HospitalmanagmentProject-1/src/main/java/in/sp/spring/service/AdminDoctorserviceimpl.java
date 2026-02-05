package in.sp.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.spring.entity.Admin;
import in.sp.spring.entity.Doctor;
import in.sp.spring.repository.AdminRepository;
import in.sp.spring.repository.DoctorRepository;

@Service
public class AdminDoctorserviceimpl implements AdminDoctorService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean adminLogin(String username, String password) {
        Optional<Admin> admin = adminRepo.findByUsername(username);

        return admin.isPresent() &&
               passwordEncoder.matches(password, admin.get().getPassword());
    }

    @Override
    public boolean doctorLogin(String username, String password) {
        Optional<Doctor> doctor = doctorRepo.findByUsername(username);

        return doctor.isPresent() &&
               passwordEncoder.matches(password, doctor.get().getPassword());
    }
}
