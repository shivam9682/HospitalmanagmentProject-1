package in.sp.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.spring.entity.User;
import in.sp.spring.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;
      @Autowired
         private sendemailservice sendemailservice;
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    // ================= REGISTER =================
    @Override
    public boolean registerUser(User user) {
        try {
            // Email already exists check
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return false;
                 
            }

            // ✅ Encode password before saving
            user.setPassword(bcrypt.encode(user.getPassword()));

            userRepository.save(user);
            sendemailservice.sendRegistrationSuccessEmail(
                    user.getEmail(),
                    user.getFirstName()
            );
            return true;
 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
    }

    // ================= LOGIN =================
    @Override
    public User loginUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // ✅ BCrypt password match
            if (bcrypt.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null; // Email not found or password mismatch
    }
}
