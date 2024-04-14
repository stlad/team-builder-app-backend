package ru.vaganov.tba;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vaganov.tba.dto.HardRoleExternalDTO;
import ru.vaganov.tba.dto.HardskillsResultDTO;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class HardskillsApiClient {

    @Value("${hardskills.host}")
    private String host;

    @Value("${hardskills.context-path}")

    private String contextPath;

    public HardskillsResultDTO[] getAllUserResults(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> vars = new HashMap<>();

        try {
            var resp = restTemplate.exchange(host +contextPath+"/results/",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    HardskillsResultDTO[].class,vars);

            return resp.getBody();
        }catch (Exception ex){
            return  null;
        }
    }

    public HardRoleExternalDTO getRoleById(Long roleId){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> vars = new HashMap<>();
        vars.put("id", roleId.toString());

        try {
            var resp = restTemplate.exchange(host +contextPath+"/roles/id/{id}",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    HardRoleExternalDTO.class,vars);

            return resp.getBody();
        }catch (Exception ex){
            return  null;
        }
    }
}
