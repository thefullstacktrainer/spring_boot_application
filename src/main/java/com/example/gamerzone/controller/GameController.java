// com.example.gamerzone.controller.GameController
package com.example.gamerzone.controller;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        gameRepository.save(game);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        return gameRepository.findById(id)
                .map(existingGame -> {
                    existingGame.setTitle(updatedGame.getTitle());
                    existingGame.setGenre(updatedGame.getGenre());
                    existingGame.setReleaseYear(updatedGame.getReleaseYear());
                    gameRepository.save(existingGame);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
