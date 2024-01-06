// com.example.gamerzone.repository.CustomGamerRepository
package com.example.gamerzone.repository;

import java.util.List;

import com.example.gamerzone.model.Gamer;

public interface CustomGamerRepository {
	List<Gamer> findGamersWithComplexCriteria(int age, String username);
}
