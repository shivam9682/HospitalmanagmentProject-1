package in.sp.spring.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.spring.entity.Appoitment;
import in.sp.spring.repository.AppoitmentRepository;

@Service
public class AppoitmentService {

    @Autowired
    private AppoitmentRepository repo;

    // USER
    public Appoitment book(Appoitment app) {
        app.setStatus("PENDING");
        return repo.save(app);
    }

    // ADMIN
    public List<Appoitment> getAll() {
        return repo.findAll();
    }

    // DOCTOR
    public List<Appoitment> getByDoctor(String doctorId) {
        return repo.findByDoctorId(doctorId);
    }

    public Appoitment updateStatus(int id, String status) {
        Appoitment app = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        app.setStatus(status);
        return repo.save(app);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
    public List<Appoitment> getByUser(int userId) {
        return repo.findByUserId(userId);
    }

}
