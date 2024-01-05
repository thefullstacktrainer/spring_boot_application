// com.example.gamerzone.repository.GamerRepository
package com.example.gamerzone.repository;

import com.example.gamerzone.model.Gamer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GamerRepository {

    private final List<Gamer> gamers = new ArrayList<>();

    public List<Gamer> findAll() {
        return gamers;
    }

    public Gamer findById(Long id) {
        return gamers.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Gamer gamer) {
        gamer.setId(generateUniqueId());
        gamers.add(gamer);
    }

    public void saveAll(List<Gamer> gamerList) {
        gamerList.forEach(this::save);
    }

    public void update(Gamer updatedGamer) {
        Gamer existingGamer = findById(updatedGamer.getId());
        if (existingGamer != null) {
            existingGamer.setUsername(updatedGamer.getUsername());
            existingGamer.setEmail(updatedGamer.getEmail());
            existingGamer.setAge(updatedGamer.getAge());
        }
    }

    public void delete(Long id) {
        Gamer gamer = findById(id);
        if (gamer != null) {
            gamers.remove(gamer);
        }
    }

    private Long generateUniqueId() {
        // In a real application, use a proper ID generation strategy.
        return System.currentTimeMillis();
    }
}
