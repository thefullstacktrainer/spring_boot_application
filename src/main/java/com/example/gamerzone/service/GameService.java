// com.example.gamerzone.service.GameService
package com.example.gamerzone.service;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}

	public Game getGameById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}

	public void createGame(Game game) {
		gameRepository.save(game);
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
	public List<Game> findGamesByTitle(String title) {
		return gameRepository.findGamesByTitle(title);
	}

	public List<Game> findGamesByGenre(String genre) {
		return gameRepository.findGamesByGenre(genre);
	}

	public List<Game> findByTitleContaining(String partialTitle) {
		return gameRepository.findByTitleContaining(partialTitle);
	}

	public List<Game> findByGenreContaining(String partialGenre) {
		return gameRepository.findByGenreContaining(partialGenre);
	}
}
