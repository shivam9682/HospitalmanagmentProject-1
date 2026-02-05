package in.sp.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.spring.entity.Appoitment;

public interface AppoitmentRepository extends JpaRepository<Appoitment, Integer> {

    List<Appoitment> findByDoctorId(String doctorId);

    List<Appoitment> findByUserId(int userId);
     
}
