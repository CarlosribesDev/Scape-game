package com.games.calendar.rest;

import com.games.calendar.model.Game;
import com.games.calendar.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GameController {

    private final GameService gameService;

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getGame(@PathVariable final Long id){
        return ResponseEntity.ok(this.gameService.retrieveGameById(id));
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> getGames(){
        return ResponseEntity.ok(this.gameService.retrieveGames());
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public ResponseEntity<Game> saveGame(@RequestBody final Game game){
        return ResponseEntity.ok(this.gameService.saveGame(game));
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> updateGame(@PathVariable final Long id, @RequestBody final Game game){
        return ResponseEntity.ok(this.gameService.updateGame(id, game));
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGame(@PathVariable final Long id){
        this.gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }


}
