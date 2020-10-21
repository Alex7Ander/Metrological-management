package ru.pavlov.MetrologicalManagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.pavlov.MetrologicalManagement.domain.VerificationProcedure;
import ru.pavlov.MetrologicalManagement.repos.VerificationProcedureRepo;

@Controller
public class MainController {
	
	@Autowired
	private VerificationProcedureRepo verificationProcedureRepo;
	
	@GetMapping
	public String main(Model model) {
		return "main";
	}
	
	@GetMapping("/verificationProcedures")
	public String showResults(Model model) {
		List<VerificationProcedure> procedures = verificationProcedureRepo.findAll();
		model.addAttribute("procedures", procedures);
		return "verificationProcedure";
	}
	
}