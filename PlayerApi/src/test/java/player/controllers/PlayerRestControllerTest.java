package player.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import player.Player;
import player.Position;
import player.controllers.PlayerRestController;
import player.domain.PlayersService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlayerRestControllerTest {

    @Mock
    PlayersService playersService;

    @Test
    public void getPlayers_WhenTwoPlayersAreAvalable_TwoPlayersShouldBeReturn() {
        //Assign
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("mania","mańkowska", 13, Position.ATTACKER,"Domownik",13));
        players.add(new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11));
        when(playersService.getPlayers()).thenReturn(players);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Iterable<Player> response = target.getPlayers();

        //Assert
        assertNotNull(response);
        assertEquals(players, response);
    }

    @Test
    public void getPlayers_WhenListIsEmpty_NoPlayersAvailable() {
        //Assign
        ArrayList<Player> players = new ArrayList<Player>();
        when(playersService.getPlayers()).thenReturn(players);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Iterable<Player> response = target.getPlayers();

        //Assert
        assertEquals(new ArrayList<Player>(), response);
    }

    @Test
    public void getPlayer_WhenPlayerIsFound_PlayersShouldBeReturned() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.getPlayer(11);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Player returnedPlayer = response.getBody();

        assertEquals("szkrabek", returnedPlayer.getName());
        assertEquals((Integer) 11, returnedPlayer.getId());

    }

    @Test
    public void getPlayer_WhenPlayerIsNotFound_PlayersNotShouldBeReturned() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.getPlayer(13);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void getPlayer_WhenListIsEmpty_PlayersNotShouldBeReturned() {
        //Assign
        Player targetPlayer = new Player();

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.getPlayer(13);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void deletePlayer_WhenPlayerIsFound_PlayersShouldBeDeleded() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.deletePlayer(11);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    public void deletePlayer_WhenPlayerIsNotFound_NoPlayersDeleded() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.deletePlayer(13);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deletePlayer_WhenListIsEmptyd_NoPlayersDeleded() {
        //Assign
        Player targetPlayer = new Player();

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        ResponseEntity<Player> response =target.deletePlayer(13);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void editPlayer_WhenPlayerIsFound_PlayerShouldBeEdit() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Player newPlayer = new Player("maja","pszczółkowska",7,Position.GOELIE,"pszczółkowo", 11);
        ResponseEntity<Player> response =target.editPlayer(11,newPlayer);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void editPlayer_WhenPlayerIsNotFound_PlayerNotShouldBeEdit() {
        //Assign
        Player targetPlayer = new Player("szkrabek","szkrabowki", 11, Position.DEFENDER,"Kanapowice",11);
        when(playersService.getPlayer(11)).thenReturn(targetPlayer);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Player newPlayer = new Player("maja","pszczółkowska",7,Position.GOELIE,"pszczółkowo", 11);
        ResponseEntity<Player> response =target.editPlayer(14,newPlayer);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void editPlayer_WhenListIsEmpty_PlayerNotShouldBeEdit() {
        //Assign
        Player targetPlayer = new Player();

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Player newPlayer = new Player("maja","pszczółkowska",7,Position.GOELIE,"pszczółkowo", 11);
        ResponseEntity<Player> response =target.editPlayer(11,newPlayer);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void postPlayer_WhenListIsEmpty_playerShouldBeAdded(){
        //Assign
        ArrayList<Player> players = new ArrayList<Player>();
        when(playersService.getPlayers()).thenReturn(players);

        PlayerRestController target = new PlayerRestController(playersService);

        //Act
        Player newPlayer = new Player("maja","pszczółkowska",7,Position.GOELIE,"pszczółkowo", 11);
        Player response = target.postPlayer(newPlayer);

        //Assert
        verify(playersService,times(1)).postPlayer(newPlayer);
    }

}