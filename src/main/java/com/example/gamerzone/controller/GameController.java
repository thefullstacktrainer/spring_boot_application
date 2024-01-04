// com.example.gamerzone.controller.GameController
package com.example.gamerzone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gamerzone.model.Game;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private List<Game> games = new ArrayList<>();

    // Initialize some sample games
    {
        games.add(new Game(generateUniqueId(), "Game A", "Action", 2022));
        games.add(new Game(generateUniqueId(), "Game B", "Adventure", 2023));
    }
    
    @GetMapping
    public List<Game> getAllGames() {
        return games;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = findGameById(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        game.setId(generateUniqueId());
        games.add(game);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        Game existingGame = findGameById(id);
        if (existingGame != null) {
            // Update existing game
            existingGame.setTitle(updatedGame.getTitle());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = findGameById(id);
        if (game != null) {
            games.remove(game);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Game findGameById(Long id) {
        return games.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    // Other methods for updating and deleting games
    // ...

    private Long generateUniqueId() {
        // In a real application, use a proper ID generation strategy.
        return System.currentTimeMillis();
    }
}
