// com.example.gamerzone.controller.GameController
package com.example.gamerzone.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

	private final Logger logger = LoggerFactory.getLogger(GameService.class);

	@Autowired
	private GameService gameService;

	@GetMapping
	public List<Game> getAllGames() {
		return gameService.getAllGames();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Game> getGameById(@PathVariable Long id) {
		Game game = gameService.getGameById(id);
		if (game != null) {
			return ResponseEntity.ok(game);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<String> createGame(@RequestBody @Valid Game game, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(error -> String.format("%s: %s", error.getCode(), error.getDefaultMessage()))
					.collect(Collectors.toList());

			String errorMessage = String.join("; ", errorMessages);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		gameService.createGame(game);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody @Valid Game updatedGame,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());
			throw new IllegalArgumentException(String.join("; ", errorMessages));
		}

		gameService.updateGame(id, updatedGame);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
		gameService.deleteGame(id);
		return ResponseEntity.ok().build();
	}

	// Named query endpoints
	@GetMapping("/by-title/{title}")
	public ResponseEntity<List<Game>> findGamesByTitle(@PathVariable String title) {
		List<Game> games = gameService.findGamesByTitle(title);
		logger.info("Executing gameService.findGamesByTitle for title: {}", title);
		return ResponseEntity.ok(games);
	}

	@GetMapping("/by-genre/{genre}")
	public ResponseEntity<List<Game>> findGamesByGenre(@PathVariable String genre) {
		List<Game> games = gameService.findGamesByGenre(genre);
		return ResponseEntity.ok(games);
	}

	@GetMapping("/by-partial-title")
	public ResponseEntity<List<Game>> findByPartialTitle(@RequestParam String title) {
		List<Game> games = gameService.findByTitleContaining(title);
		return ResponseEntity.ok(games);
	}

	@GetMapping("/by-partial-genre")
	public ResponseEntity<List<Game>> findByPartialGenre(@RequestParam String genre) {
		List<Game> games = gameService.findByGenreContaining(genre);
		return ResponseEntity.ok(games);
	}
}
