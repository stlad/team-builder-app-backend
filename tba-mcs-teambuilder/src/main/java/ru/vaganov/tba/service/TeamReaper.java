package ru.vaganov.tba.service;

import lombok.Data;
import ru.vaganov.tba.model.UserFullResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TeamReaper {
    public TeamReaper(){
        currentTeam = new ArrayList<>();
    }

    private List<UserFullResult> currentTeam;
    private Long projectId;


    public Map<Long, List<UserFullResult>> reapMembers(Map<Long, List<UserFullResult>> potentialMembers){
        for(Long profId: potentialMembers.keySet()){ //По каждой роли
            List<UserFullResult> currentRoleUsers = potentialMembers.get(profId);
            for(int i = 0; i< currentRoleUsers.size(); ++i)
            //for(UserFullResult potentialMember: currentRoleUsers){

                if(!getCurrentTeamRoles().contains(currentRoleUsers.get(i).getTeamRoleId())
                                                    || i == currentRoleUsers.size()-1){
                    catchUserToCommand(currentRoleUsers, currentRoleUsers.get(i));
                    break;
                }

            }

        return potentialMembers;
    }


    private Set<Long> getCurrentProfRoles(){
        return currentTeam.stream().map(UserFullResult::getProfRoleId).collect(Collectors.toSet());
    }

    private Set<Long> getCurrentTeamRoles(){
        return currentTeam.stream().map(UserFullResult::getTeamRoleId).collect(Collectors.toSet());
    }

    private void catchUserToCommand(List<UserFullResult> usersOfRole, UserFullResult target){
        currentTeam.add(target);
        usersOfRole.remove(target);
    }

}
