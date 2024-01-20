package ru.vaganov.tba.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.BlockQuestion;
import ru.vaganov.tba.models.Question;
import ru.vaganov.tba.repositories.BelbinRoleRepository;
import ru.vaganov.tba.repositories.BlockQuestionRepository;
import ru.vaganov.tba.repositories.QuestionRepository;

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
    public CommandLineRunner catalogDataLoader(BelbinRoleRepository roleRepository, BlockQuestionRepository blockQuestionRepository, QuestionRepository questionRepository) {
        return args -> {
            loadRoleCatalog(roleRepository);
            loadBlocks(blockQuestionRepository);
            loadQuestions(roleRepository, blockQuestionRepository, questionRepository);
        };
    }


    private void loadRoleCatalog(BelbinRoleRepository repository){
        log.info("Role Catalog Loading ...");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BelbinRole[] roles = objectMapper.readValue(getClass().getResource("/RoleCatalog.json"),BelbinRole[].class);
            Arrays.asList(roles).forEach(role ->{
                if(repository.findByNumber(role.getNumber()).isPresent()){
                    log.info("Saving role of \""+ role.getEngName() +"\" already exists, skipping");
                }
                else {
                    repository.save(role);
                    log.info("Saving role of \""+ role.getEngName() +"\" ");
                }
            });
            log.info("Role Catalog Loaded Successfully ...");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void loadBlocks(BlockQuestionRepository repository){
        log.info("Block Catalog Loading ...");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BlockQuestion[] blocks = objectMapper.readValue(getClass().getResource("/BlockQuestions.json"),BlockQuestion[].class);
            Arrays.asList(blocks).forEach(block ->{
                if(repository.findByNumber(block.getNumber()).isPresent()){
                    log.info("Block with id: "+block.getNumber()+" already exists, skipping...");
                }
                else{
                    repository.save(block);
                    log.info("Saving block №"+ block.getNumber());
                }
            });
            log.info("Block Catalog Loaded Successfully ...");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void loadQuestions(BelbinRoleRepository belbinRoleRepository,
                               BlockQuestionRepository blockQuestionRepository,
                               QuestionRepository questionRepository){

        log.info("Question Catalog Loading ...");
        FileParser<Question> parser = new FileParser<>("/QuestionCatalog.txt");
        parser.exec(str -> {
            String[] arr = str.split(";");
            Long number = Long.parseLong(arr[0]) * 10 + Long.parseLong(arr[1]);
            BlockQuestion blockQuestion = blockQuestionRepository.findByNumber(Long.parseLong(arr[0]))
                    .orElseThrow(()-> new EntityNotFoundException("Cannot find block with block number: "+arr[0]));

            BelbinRole role = belbinRoleRepository.findByEngName(arr[3])
                    .orElseThrow(()-> new EntityNotFoundException("Cannot find role of: "+arr[3]));

            Question question = Question.builder()
                    .number(number)
                    .attachedBlock(blockQuestion)
                    .attachedRole(role)
                    .content(arr[2])
                    .build();
            return question;

        }, (question)->{
            if(questionRepository.findByNumber(question.getNumber()).isPresent()) {
                log.info("Question with number: " + question.getNumber()+ " already exists, skipping...");
            }
            else {
                questionRepository.save(question);
            }
        });
        log.info("Questions Catalog Loaded Successfully ...");
    };
}
