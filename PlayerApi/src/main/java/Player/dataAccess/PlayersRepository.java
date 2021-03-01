package player.dataAccess;

import player.Player;
import org.springframework.data.repository.CrudRepository;
public interface PlayersRepository extends CrudRepository<Player, Integer> {

}
