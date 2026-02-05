package in.sp.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class facebookandgoogle {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            
            // 1️⃣ URL authorization
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // sab pages permitAll
            )
            
            // 2️⃣ OAuth2 login (Google)
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/index.html", true) // login ke baad redirect index.html
            )
            
            // 3️⃣ Logout configuration
            .logout(logout -> logout
                .logoutUrl("/logout")       // custom logout URL
                .logoutSuccessUrl("/indexhtml")    // redirect after logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}
