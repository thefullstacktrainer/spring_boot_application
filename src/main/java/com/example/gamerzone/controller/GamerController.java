// com.example.gamerzone.controller.GamerController
package com.example.gamerzone.controller;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamers")
public class GamerController {

    @Autowired
    private GamerService gamerService;

    @GetMapping
    public List<Gamer> getAllGamers() {
        return gamerService.getAllGamers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gamer> getGamerById(@PathVariable Long id) {
        Gamer gamer = gamerService.getGamerById(id);
        if (gamer != null) {
            return ResponseEntity.ok(gamer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createGamer(@RequestBody Gamer gamer) {
        gamerService.createGamer(gamer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGamer(@PathVariable Long id, @RequestBody Gamer updatedGamer) {
        gamerService.updateGamer(id, updatedGamer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamer(@PathVariable Long id) {
        gamerService.deleteGamer(id);
        return ResponseEntity.ok().build();
    }
}
