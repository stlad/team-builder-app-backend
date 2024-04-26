package ru.vaganov.tba.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vaganov.tba.models.HardRole;
import ru.vaganov.tba.repositories.HardRoleRepository;

@Configuration
@Slf4j
public class RoleCatalogLoader {

    @Bean
    public CommandLineRunner loadRoleCatalog(HardRoleRepository repository){
        return args -> {
            log.info("Loading Hard Role Catalog...");
            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("Бэкенд-разработчик").engName("Backend-developer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("Фронтенд-разработчик").engName("Frontend-developer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("Дизайнер").engName("Designer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("Аналитик").engName("Analyst")
                    .build(), repository);

            log.info("Hard Role Catalog loaded...");
        };
    }

    private void saveHardRole(HardRole role, HardRoleRepository repository){
        if(!repository.findByRusNameOrEngName(role.getRusName(), role.getEngName()).isPresent()){
            role = repository.save(role);
            log.info("Saved role: {} with id: {}", role.getEngName(), role.getId());
        }
    }
}
