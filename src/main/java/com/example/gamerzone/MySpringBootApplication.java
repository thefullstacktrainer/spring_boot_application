// com.example.gamerzone.MySpringBootApplication
package com.example.gamerzone;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.model.Game;
import com.example.gamerzone.repository.GamerRepository;
import com.example.gamerzone.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(GamerRepository gamerRepository, GameRepository gameRepository) {
        return args -> {
            // Sample gamer data
            Gamer gamer1 = new Gamer(null, "player1", "player1@example.com", 25);
            Gamer gamer2 = new Gamer(null, "player2", "player2@example.com", 30);

            // Save gamers to the database
            gamerRepository.saveAll(Arrays.asList(gamer1, gamer2));

            // Sample game data
            Game game1 = new Game(null, "Game A", "Action", 2022);
            Game game2 = new Game(null, "Game B", "Adventure", 2023);

            // Save games to the database
            gameRepository.saveAll(Arrays.asList(game1, game2));
        };
    }
}
