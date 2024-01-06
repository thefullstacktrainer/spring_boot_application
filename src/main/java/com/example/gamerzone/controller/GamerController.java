// com.example.gamerzone.controller.GamerController
package com.example.gamerzone.controller;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamers")
public class GamerController {

	@Autowired
	private GamerService gamerService;

	@GetMapping
	public List<Gamer> getAllGamers() {
		return gamerService.getAllGamers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Gamer> getGamerById(@PathVariable Long id) {
		Gamer gamer = gamerService.getGamerById(id);
		if (gamer != null) {
			return ResponseEntity.ok(gamer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Void> createGamer(@RequestBody Gamer gamer) {
		gamerService.createGamer(gamer);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateGamer(@PathVariable Long id, @RequestBody Gamer updatedGamer) {
		gamerService.updateGamer(id, updatedGamer);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGamer(@PathVariable Long id) {
		gamerService.deleteGamer(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/by-age/{age}")
	public ResponseEntity<List<Gamer>> getGamersByAge(@PathVariable int age) {
		List<Gamer> gamers = gamerService.getGamersByAge(age);
		return ResponseEntity.ok(gamers);
	}

	@GetMapping("/usernames-by-age-greater-than/{age}")
	public ResponseEntity<List<String>> getUsernamesByAgeGreaterThan(@PathVariable int age) {
		List<String> usernames = gamerService.getUsernamesByAgeGreaterThan(age);
		return ResponseEntity.ok(usernames);
	}

	@GetMapping("/by-username-containing/{username}")
	public ResponseEntity<List<Gamer>> getGamersByUsernameContaining(@PathVariable String username) {
		List<Gamer> gamers = gamerService.getGamersByUsernameContaining(username);
		return ResponseEntity.ok(gamers);
	}

	@GetMapping("/average-age")
	public ResponseEntity<Double> getAverageAge() {
		Double averageAge = gamerService.getAverageAge();
		return ResponseEntity.ok(averageAge);
	}

	@GetMapping("/count-by-age-greater-than-equal/{age}")
	public ResponseEntity<Long> countGamersByAgeGreaterThanEqual(@PathVariable int age) {
		Long count = gamerService.countGamersByAgeGreaterThanEqual(age);
		return ResponseEntity.ok(count);
	}

	@GetMapping("/increase-age/{maxAge}")
	public ResponseEntity<String> increaseAgeForGamersBelowMaxAge(@PathVariable int maxAge) {
		int updatedRecords = gamerService.increaseAgeForGamersBelowMaxAge(maxAge);
		return ResponseEntity.ok(updatedRecords + " records updated.");
	}

	@GetMapping("/by-age-with-sorting/{minAge}")
	public ResponseEntity<List<Gamer>> findGamersByAgeWithSorting(@PathVariable int minAge) {
		List<Gamer> gamers = gamerService.findGamersByAgeWithSorting(minAge);
		return ResponseEntity.ok(gamers);
	}

	@GetMapping("/usernames-by-age/{age}")
	public ResponseEntity<List<String>> findUsernamesByAge(@PathVariable int age) {
		List<String> usernames = gamerService.findUsernamesByAge(age);
		return ResponseEntity.ok(usernames);
	}

	@GetMapping("/by-username/{username}")
	public ResponseEntity<Gamer> findByUsername(@PathVariable String username) {
		Gamer gamer = gamerService.findByUsername(username);
		return ResponseEntity.ok(gamer);
	}

	// New endpoint using HCQL
	@GetMapping("/by-age-and-username")
	public ResponseEntity<List<Gamer>> findGamersByAgeAndUsername(@RequestParam int age,
			@RequestParam String username) {
		List<Gamer> gamers = gamerService.findGamersByAgeAndUsername(age, username);
		return ResponseEntity.ok(gamers);
	}

	@GetMapping("/with-complex-criteria")
	public ResponseEntity<List<Gamer>> findGamersWithComplexCriteria(@RequestParam int age,
			@RequestParam String username) {
		List<Gamer> gamers = gamerService.findGamersWithComplexCriteria(age, username);
		return ResponseEntity.ok(gamers);
	}

	@GetMapping("/custom-criteria")
    public ResponseEntity<List<Gamer>> findGamersWithCustomCriteria(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue = "username") String orderBy,
            @RequestParam(defaultValue = "asc") String orderDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Gamer> gamers = gamerService.findGamersWithCustomCriteria(age, username, orderBy, orderDirection, page, size);
        return ResponseEntity.ok(gamers);
    }
}
