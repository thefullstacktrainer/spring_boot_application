// com.example.gamerzone.service.GamerService
package com.example.gamerzone.service;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamerService {

    @Autowired
    private GamerRepository gamerRepository;

    public List<Gamer> getAllGamers() {
        return gamerRepository.findAll();
    }

    public Gamer getGamerById(Long id) {
        return gamerRepository.findById(id).orElse(null);
    }

    public void createGamer(Gamer gamer) {
        gamerRepository.save(gamer);
    }

    public void updateGamer(Long id, Gamer updatedGamer) {
        gamerRepository.findById(id).ifPresent(existingGamer -> {
            existingGamer.setUsername(updatedGamer.getUsername());
            existingGamer.setEmail(updatedGamer.getEmail());
            existingGamer.setAge(updatedGamer.getAge());
            gamerRepository.save(existingGamer);
        });
    }

    public void deleteGamer(Long id) {
        gamerRepository.deleteById(id);
    }
}
