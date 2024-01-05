// com.example.gamerzone.service.GamerService
package com.example.gamerzone.service;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class GamerService {

	@Autowired
	private GamerRepository gamerRepository;

	@Autowired
	private EntityManager entityManager;

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

	public List<Gamer> getGamersByAge(int age) {
		return gamerRepository.findByAge(age);
	}

	public List<String> getUsernamesByAgeGreaterThan(int age) {
		return gamerRepository.findUsernamesByAgeGreaterThan(age);
	}

	public List<Gamer> getGamersByUsernameContaining(String username) {
		return gamerRepository.findByUsernameContaining(username);
	}

	public Double getAverageAge() {
		return gamerRepository.getAverageAge();
	}

	public Long countGamersByAgeGreaterThanEqual(int age) {
		return gamerRepository.countGamersByAgeGreaterThanEqual(age);
	}

	@Transactional
	public int increaseAgeForGamersBelowMaxAge(int maxAge) {
		Query query = entityManager.createQuery("UPDATE Gamer SET age = age + 1 WHERE age < :maxAge");
		query.setParameter("maxAge", maxAge);
		return query.executeUpdate();
	}

	public List<Gamer> findGamersByAgeWithSorting(int minAge) {
		Query query = entityManager.createQuery("SELECT g FROM Gamer g WHERE g.age >= :minAge ORDER BY g.age ASC");
		query.setParameter("minAge", minAge);
		return query.getResultList();
	}

	public List<String> findUsernamesByAge(int age) {
		Query query = entityManager.createQuery("SELECT g.username FROM Gamer g WHERE g.age = :age");
		query.setParameter("age", age);
		return query.getResultList();
	}

	public Gamer findByUsername(String username) {
		Query query = entityManager.createQuery("FROM Gamer WHERE username = :username");
		query.setParameter("username", username);
		return (Gamer) query.getSingleResult();
	}

	// New method using HCQL
	public List<Gamer> findGamersByAgeAndUsername(int age, String username) {
		return gamerRepository.findGamersByAgeAndUsername(age, username);
	}
}
