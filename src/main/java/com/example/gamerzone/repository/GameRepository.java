// com.example.gamerzone.repository.GameRepository
package com.example.gamerzone.repository;

import com.example.gamerzone.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    // Named Query: Find games by title
    @Query(name = "Game.findByTitle")
    List<Game> findGamesByTitle(String title);

    // Named Query: Find games by genre
    @Query(name = "Game.findByGenre")
    List<Game> findGamesByGenre(String genre);
}
