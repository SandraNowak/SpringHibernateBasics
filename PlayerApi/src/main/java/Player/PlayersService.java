package Player;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
public class PlayersService {

    private final PlayersRepository playersRepository;

    public PlayersService( PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }




    public Iterable<Player> getPlayers(){return playersRepository.findAll();}

    public Player getPlayer(Integer id){

        Player getPlayer = StreamSupport.stream(playersRepository.findAll().spliterator(), false).filter(A ->A.getId()==id).findAny().orElse(null);
        return getPlayer;
    }

    public Iterable<Player> getPsurmanePlayers(String team ){
        Iterable<Player> getPsurmanePlayers = StreamSupport.stream(playersRepository.findAll().spliterator(), false).filter(A -> A.getTeam().equals(team)).filter(A -> A.getSurname().toLowerCase().startsWith("p")).collect(Collectors.toList());
        return getPsurmanePlayers;
    }

    public Player postPlayer(Player newPlayer){
        playersRepository.save(newPlayer);
        return newPlayer;
    }

    public void deletePlayer (Player player){
        playersRepository.delete(player);

    }

    public Player editPlayer(Integer id, Player player){
        Player editPlayer = StreamSupport.stream(playersRepository.findAll().spliterator(), false).filter(A ->A.getId()==id).findAny().orElse(null);
        editPlayer.setName(player.getName());
        editPlayer.setSurname(player.getSurname());
        editPlayer.setNubmer(player.getNubmer());
        editPlayer.setPosition(player.getPosition());
        editPlayer.setTeam(player.getTeam());
        editPlayer.setNubmer(player.getNubmer());
        return playersRepository.save(editPlayer);

    }

}
