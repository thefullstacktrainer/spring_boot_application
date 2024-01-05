// com.example.gamerzone.repository.GameRepository
package com.example.gamerzone.repository;

import com.example.gamerzone.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

    private final List<Game> games = new ArrayList<>();

    public List<Game> findAll() {
        return games;
    }

    public Game findById(Long id) {
        return games.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Game game) {
        game.setId(generateUniqueId());
        games.add(game);
    }

    public void saveAll(List<Game> gameList) {
        gameList.forEach(this::save);
    }

    public void update(Game updatedGame) {
        Game existingGame = findById(updatedGame.getId());
        if (existingGame != null) {
            existingGame.setTitle(updatedGame.getTitle());
            existingGame.setGenre(updatedGame.getGenre());
            existingGame.setReleaseYear(updatedGame.getReleaseYear());
        }
    }

    public void delete(Long id) {
        Game game = findById(id);
        if (game != null) {
            games.remove(game);
        }
    }

    private Long generateUniqueId() {
        // In a real application, use a proper ID generation strategy.
        return System.currentTimeMillis();
    }
}
