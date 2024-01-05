// com.example.gamerzone.controller.GamerController
package com.example.gamerzone.controller;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamers")
public class GamerController {

    @Autowired
    private GamerRepository gamerRepository;

    @GetMapping
    public List<Gamer> getAllGamers() {
        return gamerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gamer> getGamerById(@PathVariable Long id) {
        Gamer gamer = gamerRepository.findById(id);
        if (gamer != null) {
            return ResponseEntity.ok(gamer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createGamer(@RequestBody Gamer gamer) {
        gamerRepository.save(gamer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGamer(@PathVariable Long id, @RequestBody Gamer updatedGamer) {
        gamerRepository.update(updatedGamer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamer(@PathVariable Long id) {
        gamerRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
