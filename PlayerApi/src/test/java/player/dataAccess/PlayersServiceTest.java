package player.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import player.Player;
import player.Position;
import player.dataAccess.PlayersRepository;
import player.domain.PlayersService;

@RunWith(MockitoJUnitRunner.Silent.class)

public class PlayersServiceTest {

    @Mock
    PlayersRepository playersRepository;

    @Test
     public void getPlayer_WhenPlayerIsFound_PlayerShouldByReturn() {
        //Assign
        ArrayList<Player>  players = new ArrayList<Player>();
        players.add(new Player("szkrabek", "szkrabowki", 11, Position.DEFENDER, "Kanapowice", 11));
        players.add(new Player("mania", "mańkowska", 13, Position.WINGER, "Domownik", 13));
        when(playersRepository.findAll()).thenReturn(players);

        PlayersService target = new PlayersService(playersRepository);

        //Act
        Player response = target.getPlayer(11);

        //Assert
        assertEquals("szkrabek", response.getName());
        assertEquals((Integer) 11, response.getId());
    }
    @Test
    public void getPlayers_WhenTwoPlayersAreAvailable_TwoPlayersShouldBeAvailable()
    {
        //Assign
        ArrayList<Player>  players = new ArrayList<Player>();
        players.add(new Player("szkrabek", "szkrabowki", 11, Position.DEFENDER, "Kanapowice", 11));
        players.add(new Player("mania", "mańkowska", 13, Position.WINGER, "Domownik", 13));
        when(playersRepository.findAll()).thenReturn(players);

        PlayersService target = new PlayersService(playersRepository);

        //Act
        Iterable<Player> response = target.getPlayers();

        //Assert
        assertNotNull(response);
        assertEquals(players, response);
    }

    @Test
    public void editPlayer_WhenPlayerIsFound_PlayerShouldBeEdit()
    {
        //Assign
        ArrayList<Player>  players = new ArrayList<Player>();
        players.add(new Player("szkrabek", "szkrabowki", 11, Position.DEFENDER, "Kanapowice", 11));
        players.add(new Player("mania", "mańkowska", 13, Position.WINGER, "Domownik", 13));
        when(playersRepository.findAll()).thenReturn(players);
        when(playersRepository.save(any(Player.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        PlayersService target = new PlayersService(playersRepository);

        //Act
        Player newPlayer = new Player("maja","pszczółkowska",7,Position.GOELIE,"pszczółkowo", 13);
        Player response = target.editPlayer(13, newPlayer);

        //Assert
        assertEquals("maja", response.getName());
        assertEquals("pszczółkowska", response.getSurname());
        assertEquals(7, response.getNubmer());
        assertEquals(Position.GOELIE, response.getPosition());
        assertEquals("pszczółkowo", response.getTeam());
    }

    @Test
    public void deletePlayers_WhenPlayerIsFound_PlayerShouldBeDeleded()
    {
        //Assign
        Player player1 = new Player("szkrabek", "szkrabowki", 11, Position.DEFENDER, "Kanapowice", 11);
        ArrayList<Player>  players = new ArrayList<Player>();
        players.add(player1);
        players.add(new Player("mania", "mańkowska", 13, Position.WINGER, "Domownik", 13));
        when(playersRepository.findAll()).thenReturn(players);

        PlayersService target = new PlayersService(playersRepository);

        //Act
        target.deletePlayer(player1);

        //Assert
        verify(playersRepository,times(1)).delete(player1);
    }

    @Test
    public void postPlayers_WhenPlayerIsFound_PlayerShouldBeAdded()
    {
        //Assign
        Player player1 = new Player("szkrabek", "szkrabowki", 11, Position.DEFENDER, "Kanapowice", 11);
        ArrayList<Player>  players = new ArrayList<Player>();
        players.add(player1);
        players.add(new Player("mania", "mańkowska", 13, Position.WINGER, "Domownik", 13));
        when(playersRepository.findAll()).thenReturn(players);

        PlayersService target = new PlayersService(playersRepository);

        //Act
        target.postPlayer(player1);

        //Assert
        verify(playersRepository,times(1)).save(player1);
    }

}