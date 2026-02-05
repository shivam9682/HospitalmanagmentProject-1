package in.sp.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import in.sp.spring.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUsername(String username);
    
    @Query("SELECT u FROM Doctor u WHERE u.username = :username")
    Doctor getDoctorByUsername(@Param("username") String username);
  
}
