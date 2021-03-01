package player.domain;


import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import player.Team;
import player.dataAccess.TeamsRepository;

@Service
public class TeamsService {
    private final TeamsRepository teamsRepository;

    public TeamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }
    
    public Iterable<Team> getTeams(){return teamsRepository.findAll();}

    public Team getTeam(Integer id){

        Team getTeam = StreamSupport.stream(teamsRepository.findAll().spliterator(), false).filter(A ->A.getId()==id).findAny().orElse(null);
        return getTeam;
    }
    
    public Team postTeam(Team newTeam){
        teamsRepository.save(newTeam);
        return newTeam;
    }

    public void deleteTeam (Team team){
        teamsRepository.delete(team);

    }

    public Team editTeam(Integer id, Team team){
        Team editTeam = StreamSupport.stream(teamsRepository.findAll().spliterator(), false).filter(A ->A.getId()==id).findAny().orElse(null);
        editTeam.setName(team.getName());
        return teamsRepository.save(editTeam);

    }
    
}
