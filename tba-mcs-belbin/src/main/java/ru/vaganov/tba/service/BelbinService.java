package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.BelbinRoleMapper;
import ru.vaganov.tba.mapper.BlockQuestionMapper;
import ru.vaganov.tba.mapper.QuestionMapper;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.BlockQuestion;
import ru.vaganov.tba.models.Question;
import ru.vaganov.tba.models.dto.AnswerBlank;
import ru.vaganov.tba.models.dto.BelbinRoleDTO;
import ru.vaganov.tba.repositories.BelbinRoleRepository;
import ru.vaganov.tba.repositories.BlockQuestionRepository;
import ru.vaganov.tba.repositories.QuestionRepository;
import ru.vaganov.tba.service.responses.BlockQuestionResponseDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BelbinService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private BlockQuestionRepository blockQuestionRepository;
    @Autowired
    private BelbinRoleRepository belbinRoleRepository;
    @Autowired
    private BlockQuestionMapper blockQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private BelbinRoleMapper roleMapper;

    public BlockQuestionResponseDTO getBlockByNumber(Long number){
        BlockQuestion block = blockQuestionRepository.findByNumber(number).orElseThrow(()-> new EntityNotFoundException("cannot find block with number: "+number));
        List<Question> questions = questionRepository.findByAttachedBlock_NumberOrderByNumber(number);
        if(questions == null || questions.size() == 0 ){
            throw new EntityNotFoundException("cannot find questions of block: "+number);
        };

        BlockQuestionResponseDTO response = BlockQuestionResponseDTO.builder()
                .blockQuestion(blockQuestionMapper.toDto(block))
                .questions(questionMapper.toDto(questions))
                .build();
        return response;
    }

    public BelbinRoleDTO getBelbinRoleByName(String roleName){
        Optional<BelbinRole> role = belbinRoleRepository.findByEngName(roleName);
        if(role.isPresent())
            return roleMapper.toDto(role.get());

        role = belbinRoleRepository.findByRusName(roleName);
        if(role.isPresent())
            return roleMapper.toDto(role.get());

        throw new EntityNotFoundException("cannot find role with name: "+roleName);
    }

}
