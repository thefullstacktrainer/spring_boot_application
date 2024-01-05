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
        return gamerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGamer(@RequestBody Gamer gamer) {
        gamerRepository.save(gamer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGamer(@PathVariable Long id, @RequestBody Gamer updatedGamer) {
        return gamerRepository.findById(id)
                .map(existingGamer -> {
                    existingGamer.setUsername(updatedGamer.getUsername());
                    existingGamer.setEmail(updatedGamer.getEmail());
                    existingGamer.setAge(updatedGamer.getAge());
                    gamerRepository.save(existingGamer);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamer(@PathVariable Long id) {
        gamerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
