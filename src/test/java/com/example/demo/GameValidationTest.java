package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.gamerzone.model.Game;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameValidationTest {

	private final Validator validator = new LocalValidatorFactoryBean();

//	@Test
//	public void testValidGame() {
//		Game game = new Game(1L, "ValidTitle", "ValidGenre", 2022);
//		Set<ConstraintViolation<Game>> violations = validator.validate(game);
//		assertEquals(0, violations.size());
//	}
//
//	@Test
//	public void testInvalidGame() {
//		Game game = new Game(1L, "", "", 999);
//		Set<ConstraintViolation<Game>> violations = validator.validate(game);
//		assertEquals(3, violations.size()); // Expecting violations for title, genre, and releaseYear
//	}
}
