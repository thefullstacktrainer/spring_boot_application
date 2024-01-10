// com.example.gamerzone.repository.GameRepository
package com.example.gamerzone.repository;

import com.example.gamerzone.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Additional custom queries or methods can be added here if needed
}
