package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data @AllArgsConstructor
public class AnswerBlank {
    private Map<String, Integer> blank;
}
