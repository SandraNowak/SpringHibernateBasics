package Player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerRestController {


    private final PlayersService playersService;

    public PlayerRestController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @RequestMapping("/player")
    public Iterable<Player> getPlayers(){return playersService.getPlayers();}

    @RequestMapping("/player/{id}")
    public ResponseEntity<Player>  getPlayer(@PathVariable Integer id){
        Player player = playersService.getPlayer(id);
        if(player == null){ return new ResponseEntity<Player>( (Player)null, HttpStatus.NOT_FOUND);}
        else return new ResponseEntity<Player>( player,HttpStatus.OK);

    }

   @RequestMapping("/player/withSurnameStartingWithP/{team}")
   public Iterable<Player> getPsurmanePlayers(@PathVariable String team){
       return playersService.getPsurmanePlayers(team);
   }

    @PostMapping("/player")
    public Player postPlayer(@RequestBody Player newPlayer){
        return playersService.postPlayer(newPlayer);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Player> deletePlayer (@PathVariable Integer id){
        Player player1 = playersService.getPlayer(id);
        if (player1==null)
        {
            return new ResponseEntity<Player>( (Player)null, HttpStatus.NOT_FOUND);
        }
        else playersService.deletePlayer(player1);
        {
            return new ResponseEntity<Player>(player1, HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/player/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable Integer id, @RequestBody Player player){
        Player player1 = playersService.getPlayer(id);
        if (player1==null){
            return new ResponseEntity<Player>( (Player)null, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<Player>( playersService.editPlayer(id,player),HttpStatus.OK);

    }

}
