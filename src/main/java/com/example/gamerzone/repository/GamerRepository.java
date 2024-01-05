// com.example.gamerzone.repository.GamerRepository
package com.example.gamerzone.repository;

import com.example.gamerzone.model.Gamer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Long> {
	// Additional custom queries or methods can be added here if needed

	@Query("FROM Gamer WHERE age = :age")
	List<Gamer> findByAge(@Param("age") int age);

	@Query("SELECT g.username FROM Gamer g WHERE g.age > :age")
	List<String> findUsernamesByAgeGreaterThan(@Param("age") int age);

	@Query("FROM Gamer WHERE username LIKE %:username%")
	List<Gamer> findByUsernameContaining(@Param("username") String username);

	@Query("SELECT AVG(g.age) FROM Gamer g")
	Double getAverageAge();

	@Query("SELECT COUNT(g) FROM Gamer g WHERE g.age >= :age")
	Long countGamersByAgeGreaterThanEqual(@Param("age") int age);
}
