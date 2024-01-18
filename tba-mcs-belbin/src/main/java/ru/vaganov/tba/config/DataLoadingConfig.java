package ru.vaganov.tba.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.repositories.BelbinRoleRepository;

import java.util.Arrays;
import java.util.List;

@Configuration @Slf4j
public class DataLoadingConfig {

    @ConditionalOnProperty(
            prefix = "command-line-runner.data-loading.role-catalog",
            value = "enabled",
            havingValue = "true",
            matchIfMissing = true)
    @Bean
    public CommandLineRunner catalogDataLoader(BelbinRoleRepository repository) {
        return args -> {
            log.info("Role Catalog Loading ...");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                BelbinRole[] roles = objectMapper.readValue(getClass().getResource("/RoleCatalog.json"),BelbinRole[].class);
                Arrays.asList(roles).forEach(role ->{
                    repository.save(role);
                    log.info("Saving role of \""+ role.getEngName() +"\" ");
                });

                log.info("Role Catalog Loaded Successfully ...");
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        };
    }

}
