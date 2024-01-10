// com.example.gamerzone.service.GameService
package com.example.gamerzone.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.repository.GameRepository;

@Service
public class GameService {

	private final Logger logger = LoggerFactory.getLogger(GameService.class);

	@Autowired
	private GameRepository gameRepository;

	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}

	public Game getGameById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}

	public void createGame(Game game) {
		try {
			validateGame(game);
			gameRepository.save(game);
		} catch (ValidationException ex) {
			// Handle validation exception
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	private void validateGame(Game game) {
		Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Game>> violations = validator.validate(game);

		if (!violations.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
			for (ConstraintViolation<Game> violation : violations) {
				errorMessage.append(violation.getMessage()).append("; ");
			}
			throw new ValidationException(errorMessage.toString());
		}
	}

	public void updateGame(Long id, Game updatedGame) {
		gameRepository.findById(id).ifPresent(existingGame -> {
			existingGame.setTitle(updatedGame.getTitle());
			existingGame.setGenre(updatedGame.getGenre());
			existingGame.setReleaseYear(updatedGame.getReleaseYear());
			gameRepository.save(existingGame);
		});
	}

	public void deleteGame(Long id) {
		gameRepository.deleteById(id);
	}

	// Named query methods
	@Cacheable("gamesByTitleCache")
	public List<Game> findGamesByTitle(String title) {
		logger.info("Executing findGamesByTitle for title: {}", title);
		return ((GameService) gameRepository).findGamesByTitle(title);
	}

	public List<Game> findGamesByGenre(String genre) {
		return ((GameService) gameRepository).findGamesByGenre(genre);
	}

	public List<Game> findByTitleContaining(String partialTitle) {
		return ((GameService) gameRepository).findByTitleContaining(partialTitle);
	}

	public List<Game> findByGenreContaining(String partialGenre) {
		return ((GameService) gameRepository).findByGenreContaining(partialGenre);
	}
}
