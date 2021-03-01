package player.dataAccess;

import org.springframework.data.repository.CrudRepository;
import player.Team;

public interface TeamsRepository extends CrudRepository<Team, Integer> {


}
