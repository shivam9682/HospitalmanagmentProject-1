package in.sp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import in.sp.spring.entity.User;

import java.util.Optional;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

    // Use Optional for safety
    Optional<User> findByEmail(String email); 

   
     
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByUsername(@Param("email") String email);

}