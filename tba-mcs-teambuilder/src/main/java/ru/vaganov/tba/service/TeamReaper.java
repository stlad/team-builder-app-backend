package ru.vaganov.tba.service;

import lombok.Data;
import ru.vaganov.tba.model.UserFullResult;

import java.util.List;
import java.util.Map;

@Data
public class TeamReaper {

    private List<UserFullResult> currentTeam;
    private Long projectId;


    public Map<Long, List<UserFullResult>> reapMembers(Map<Long, List<UserFullResult>> potentialMembers){
        for(Long profId: potentialMembers.keySet()){ //По каждой роли
            List<UserFullResult> currentRoleUsers = potentialMembers.get(profId);
            // Выбрать чела, посмотреть его командную роль
            //если роль хороша - сохраняем себе, удаляем из потенциальных мемберов
            //улетаем сохранять команду

        }
        return potentialMembers;
    }

    private int getRandomLong(Long max){
        return (int)(Math.random() * ++max);
    }

}
