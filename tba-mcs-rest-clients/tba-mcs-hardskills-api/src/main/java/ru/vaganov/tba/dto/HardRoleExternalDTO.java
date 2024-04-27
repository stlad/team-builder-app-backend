package ru.vaganov.tba.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HardRoleExternalDTO {

    private Long id;
    private String engName;
    private String rusName;
    private String description;
    private HardRoleExternalDTO.Industry industry;
    public enum Industry {
        IT,
        RADIO_ENGINEERING
    }
}
