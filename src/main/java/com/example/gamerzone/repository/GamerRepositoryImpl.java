package com.example.gamerzone.repository;

//com.example.gamerzone.repository.GamerRepositoryImpl
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


import com.example.gamerzone.model.Gamer;

@Repository
public class GamerRepositoryImpl implements CustomGamerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Gamer> findGamersWithComplexCriteria(int age, String username) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Gamer> criteriaQuery = criteriaBuilder.createQuery(Gamer.class);
		Root<Gamer> root = criteriaQuery.from(Gamer.class);

		Predicate ageCondition = criteriaBuilder.equal(root.get("age"), age);
		Predicate usernameCondition = criteriaBuilder.equal(root.get("username"), username);

		criteriaQuery.where(ageCondition, usernameCondition);

		TypedQuery<Gamer> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}