package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor
public class ResultBlank {
    List<RoleScore> roleScores;

    public void add(BelbinRoleDTO role, Integer score){
        roleScores.add(new RoleScore(role, score));
    }

    public ResultBlank(){
        roleScores = new ArrayList<>();
    }
}

@Data @AllArgsConstructor
class RoleScore {
    BelbinRoleDTO role;
    Integer score;
}
