package com.example.gamerzone.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//com.example.gamerzone.controller.view.LoginController
@Controller
public class LoginController {

 @GetMapping("/login")
 public String login() {
     return "login";
 }
}
