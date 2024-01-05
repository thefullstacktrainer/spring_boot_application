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
}
