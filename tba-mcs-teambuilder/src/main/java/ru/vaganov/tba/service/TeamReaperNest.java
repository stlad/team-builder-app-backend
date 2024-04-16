package ru.vaganov.tba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vaganov.tba.model.Team;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.repositories.TeamRepository;
import ru.vaganov.tba.repositories.UserResultsRepository;

import java.util.List;
import java.util.Map;

@Component
public class TeamReaperNest {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserResultsRepository userResultsRepository;

    public void Reap(){
        Map<Long, List<UserFullResult>> users = userResultsRepository.findAllGroupedByProfRole();
        long profRolesCnt = users.keySet().size(); // Столько человек в команде на основе результатов
        long allUsersCnt = userResultsRepository.count(); //столько всего человек

        long teamCount = (allUsersCnt + profRolesCnt - 1) / profRolesCnt; // деление с округлением в большую сторону при целочисленном делении

        for(int i =0; i< teamCount; i++){
            TeamReaper teamReaper = new TeamReaper();
            users = teamReaper.reapMembers(users);  //teamRaper забирает себе членов команды
            saveReapedTeam(teamReaper); //состав комадны, собранной teamReaper сохраняется
        }
    }

    //Вообще можно сделать в разных потоках??
    private Team saveReapedTeam(TeamReaper reaper){
        Team teamToSave = new Team();
        teamToSave = teamRepository.save(teamToSave);
        for(UserFullResult member: reaper.getCurrentTeam()){
            member.setTeam(teamToSave);
            userResultsRepository.save(member);
        }
        return teamToSave;
    }
}
