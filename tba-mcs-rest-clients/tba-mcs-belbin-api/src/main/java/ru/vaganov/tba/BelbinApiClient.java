package ru.vaganov.tba;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.vaganov.tba.dto.BelbinRoleExternalDTO;

import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class BelbinApiClient {

    @Value("${belbin.host}")
    private String host;

    @Value("${belbin.context-path}")
    private String contextPath;

    public BelbinRoleExternalDTO getRoleByName(String role){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> vars = new HashMap<>();
        vars.put("roleName", role);

        try {
            var resp = restTemplate.exchange(host +contextPath+"/roles/{roleName}",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    BelbinRoleExternalDTO.class,vars);

            return resp.getBody();
        }catch (Exception ex){
            return  null;
        }
    }
}
