package com.example.gamerzone.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//com.example.gamerzone.controller.view.SignupController
@Controller
public class SignupController {

 @GetMapping("/signup")
 public String signup() {
     return "signup";
 }
}
