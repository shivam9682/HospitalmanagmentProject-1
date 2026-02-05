package in.sp.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.sp.spring.entity.Admin;



public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
    @Query("SELECT u FROM Admin u WHERE u.username = :username")
    Admin getAdminByUsername(@Param("username") String username);

}
