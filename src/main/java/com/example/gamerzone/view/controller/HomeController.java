package com.example.gamerzone.view.controller;
//com.example.gamerzone.controller.view.HomeController
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

 @GetMapping("/")
 public String home() {
     return "home";
 }
}
