package com.example.gamerzone.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gamerzone.model.Game;
import com.example.gamerzone.service.GameService;

@Controller
public class GameViewController {
	@Autowired
	private GameService gameService;

	@GetMapping("/games/list")
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "game-list";
    }

}
