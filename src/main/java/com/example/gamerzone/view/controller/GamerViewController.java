package com.example.gamerzone.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.service.GamerService;
@Controller
public class GamerViewController {
	@Autowired
	private GamerService gamerService;

	@GetMapping("/gamers/list")
	public String getAllGamers(Model model) {
		List<Gamer> gamers = gamerService.getAllGamers();
		model.addAttribute("gamers", gamers);
		return "gamer-list";
	}

}

