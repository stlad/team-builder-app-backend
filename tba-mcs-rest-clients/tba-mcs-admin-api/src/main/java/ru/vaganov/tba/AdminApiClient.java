package ru.vaganov.tba;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vaganov.tba.dto.UserExternalDTO;

import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class AdminApiClient {

    @Value("${admin.host}")
    private String host;

    @Value("${admin.context-path}")
    private String contextPath;

    public UserExternalDTO getUserById(Long id){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> vars = new HashMap<>();
        vars.put("id", id.toString());

        try {
            var resp = restTemplate.exchange(host +contextPath+"/users/{id}",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    UserExternalDTO.class,vars);

            return resp.getBody();
        }catch (Exception ex){
            return  null;
        }
    }

    public Long countUser(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> vars = new HashMap<>();

        try {
            var resp = restTemplate.exchange(host +contextPath+"/users/count",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Long.class,vars);

            return resp.getBody();
        }catch (Exception ex){
            return  null;
        }
    }
}
