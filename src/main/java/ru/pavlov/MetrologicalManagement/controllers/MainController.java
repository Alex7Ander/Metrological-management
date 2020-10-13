package ru.pavlov.MetrologicalManagement.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pavlov.MetrologicalManagement.repos.UserRepo;

@Controller
public class MainController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String main(Map<String, Object> model) {
		return "main";
	}
	
}