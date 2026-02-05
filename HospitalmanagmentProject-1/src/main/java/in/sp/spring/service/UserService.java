package in.sp.spring.service;

import in.sp.spring.entity.User;

public interface UserService {

    boolean registerUser(User user);

    User loginUser(String email, String password);
}
