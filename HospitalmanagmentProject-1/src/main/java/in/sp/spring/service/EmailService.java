package in.sp.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    public boolean sendEmail(String subject, String message, String to) {

        try {
            String url = "https://api.brevo.com/v3/smtp/email";

            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> body = new HashMap<>();

            Map<String, String> sender = new HashMap<>();
            sender.put("email", "ssivamyadav0123@gmail.com"); // verified email
            sender.put("name", "Hospital");

            List<Map<String, String>> toList = new ArrayList<>();
            Map<String, String> toMap = new HashMap<>();
            toMap.put("email", to);
            toList.add(toMap);

            body.put("sender", sender);
            body.put("to", toList);
            body.put("subject", subject);
            body.put("htmlContent", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            System.out.println(response.getBody());

            return response.getStatusCodeValue() == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
