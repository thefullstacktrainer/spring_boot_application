// com.example.gamerzone.controller.GameController
package com.example.gamerzone.controller;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

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
	public ResponseEntity<Void> createGame(@RequestBody Game game) {
		gameService.createGame(game);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
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
		return ResponseEntity.ok(games);
	}

	@GetMapping("/by-genre/{genre}")
	public ResponseEntity<List<Game>> findGamesByGenre(@PathVariable String genre) {
		List<Game> games = gameService.findGamesByGenre(genre);
		return ResponseEntity.ok(games);
	}
}
