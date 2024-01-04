// com.example.gamerzone.controller.GamerController
package com.example.gamerzone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gamerzone.model.Gamer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gamers")
public class GamerController {

    private List<Gamer> gamers = new ArrayList<>();

    // Initialize some sample gamers
    {
        gamers.add(new Gamer(generateUniqueId(), "player1", "player1@example.com", 25));
        gamers.add(new Gamer(generateUniqueId(), "player2", "player2@example.com", 30));
    }
    
    @GetMapping
    public List<Gamer> getAllGamers() {
        return gamers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gamer> getGamerById(@PathVariable Long id) {
        Gamer gamer = findGamerById(id);
        if (gamer != null) {
            return ResponseEntity.ok(gamer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createGamer(@RequestBody Gamer gamer) {
        gamer.setId(generateUniqueId());
        gamers.add(gamer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGamer(@PathVariable Long id, @RequestBody Gamer updatedGamer) {
        Gamer existingGamer = findGamerById(id);
        if (existingGamer != null) {
            // Update existing gamer
            existingGamer.setUsername(updatedGamer.getUsername());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamer(@PathVariable Long id) {
        Gamer gamer = findGamerById(id);
        if (gamer != null) {
            gamers.remove(gamer);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Gamer findGamerById(Long id) {
        return gamers.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    // Other methods for updating and deleting gamers
    // ...

    private Long generateUniqueId() {
        // In a real application, use a proper ID generation strategy.
        return System.currentTimeMillis();
    }
}
