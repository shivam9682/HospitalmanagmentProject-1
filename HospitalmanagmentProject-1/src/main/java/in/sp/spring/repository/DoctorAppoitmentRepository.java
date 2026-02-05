package in.sp.spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.spring.entity.DoctorEntity;

import java.util.List;

@Repository
public interface DoctorAppoitmentRepository extends JpaRepository<DoctorEntity, Integer> {
    List<DoctorEntity> findByDepartment(String department);
    List<DoctorEntity> findByStatus(String status);
    DoctorEntity findByEmail(String email);
    List<DoctorEntity> findBySpecialization(String specialization);
}