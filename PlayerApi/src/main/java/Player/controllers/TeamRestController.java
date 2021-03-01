package player.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import player.Team;
import player.domain.TeamsService;

@RestController
public class TeamRestController {

    private final TeamsService teamsService;

    public TeamRestController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping("/team")
    public Iterable<Team> getTeam(){return teamsService.getTeams();}

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Integer id){
        Team team = teamsService.getTeam(id);
        if(team == null){ return new ResponseEntity<Team>( (Team) null, HttpStatus.NOT_FOUND);}
        else return new ResponseEntity<Team>( team,HttpStatus.OK);
    }

    @PostMapping("/team")
    public Team postTeam(@RequestBody Team newTeam){
        return teamsService.postTeam(newTeam);
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<Team> deleteTeam (@PathVariable Integer id){
        Team Team1 = teamsService.getTeam(id);
        if (Team1==null)
        {
            return new ResponseEntity<Team>( (Team)null, HttpStatus.NOT_FOUND);
        }
        else teamsService.deleteTeam(Team1);
        {
            return new ResponseEntity<Team>(Team1, HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/team/{id}")
    public ResponseEntity<Team> editTeam(@PathVariable Integer id, @RequestBody Team Team){
        Team Team1 = teamsService.getTeam(id);
        if (Team1==null){
            return new ResponseEntity<Team>( (Team)null, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<Team>( teamsService.editTeam(id,Team),HttpStatus.OK);
    }


}
