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
                    .industry(HardRole.Industry.IT).rusName("бэкенд-разработчик").engName("backend-developer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("фронтенд-разработчик").engName("frontend-developer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("дизайнер интерфейсов").engName("ui designer")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("системный аналитик").engName("system analyst")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("бизнес аналитик").engName("business analyst")
                    .build(), repository);

            saveHardRole(HardRole.builder()
                    .industry(HardRole.Industry.IT).rusName("тестировщик").engName("qa-engineer")
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
