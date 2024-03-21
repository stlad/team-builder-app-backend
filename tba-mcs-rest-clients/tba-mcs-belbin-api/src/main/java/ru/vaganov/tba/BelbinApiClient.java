package ru.vaganov.tba;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class BelbinApiClient {

    private final RestTemplate restTemplate;

    public BelbinApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public BelbinRoleExternalDTO getRoleByName(String role){
        try {
            var resp = restTemplate.getForEntity("http://localhost:8090/api/belbin/roles/"+role, BelbinRoleExternalDTO.class);
            return resp.getBody();
        }catch (ResourceAccessException ex){
            return  null;
        }
    }
}
