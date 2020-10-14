package ru.pavlov.MetrologicalManagement.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping
	public String main(Map<String, Object> model) {
		return "main";
	}
	
}