// com.example.gamerzone.repository.CustomGamerRepository
package com.example.gamerzone.repository;

import java.util.List;

import com.example.gamerzone.model.Gamer;

public interface CustomGamerRepository {
	List<Gamer> findGamersWithComplexCriteria(int age, String username);

	List<Gamer> findGamersWithCustomCriteria(Integer age, String username, String orderBy, String orderDirection,
			int firstResult, int maxResults);
}
