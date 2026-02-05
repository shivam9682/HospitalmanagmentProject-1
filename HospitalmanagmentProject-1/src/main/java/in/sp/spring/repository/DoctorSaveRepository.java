// DoctorRepository.java
package in.sp.spring.repository;

import in.sp.spring.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorSaveRepository extends JpaRepository<DoctorEntity, Integer> {
    
    // Find doctor by email
    DoctorEntity findByEmail(String email);
    
    // Find active doctors
    List<DoctorEntity> findByStatus(String status);
    
    // Find doctors by specialization
    List<DoctorEntity> findBySpecialization(String specialization);
    
    // Find doctors by department
    List<DoctorEntity> findByDepartment(String department);
    
    // Check if email exists
    boolean existsByEmail(String email);
}