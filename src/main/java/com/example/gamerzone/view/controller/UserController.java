package com.example.gamerzone.view.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamerzone.model.Gamer;
import com.example.gamerzone.service.GamerService;

//com.example.gamerzone.controller.view.UserController
@Controller
@RequestMapping("/user")
public class UserController {

 @Autowired
 private GamerService gamerService;

 @GetMapping("/signup")
 public String showSignupForm(Model model) {
     model.addAttribute("user", new Gamer());
     return "signup";
 }

 @PostMapping("/signup")
 public String signup(@ModelAttribute("user") @Valid Gamer user, BindingResult bindingResult) {
     if (bindingResult.hasErrors()) {
         return "signup";
     }
     gamerService.createGamer(user);
     return "redirect:/login";
 }

 @GetMapping("/login")
 public String showLoginForm() {
     return "login";
 }
}