package in.sp.spring.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import in.sp.spring.entity.User;
import in.sp.spring.repository.UserRepository;  

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oauthUser = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration()
                                     .getRegistrationId(); // google / facebook

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String providerId = oauthUser.getAttribute("sub"); // Google

        if (provider.equals("facebook")) {
            providerId = oauthUser.getAttribute("id");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setFirstName(name);
            user.setEmail(email);
            user.setProvider(provider);
            user.setProviderId(providerId);
            userRepository.save(user);
        }

        return oauthUser;
    }
}
