package ru.pavlov.MetrologicalManagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;
import ru.pavlov.MetrologicalManagement.repos.DeviceTypeRepo;
import ru.pavlov.MetrologicalManagement.repos.VerificationProcedureRepo;

@Controller
public class MainController {
	
	@Autowired
	private VerificationProcedureRepo verificationProcedureRepo;
	
	@Autowired
	private DeviceTypeRepo deviceTypeRepo;
	
	@GetMapping
	public String main(Model model) {
		return "main";
	}
	
	@GetMapping("/verificationProcedures")
	public String showResults(Model model) {
		System.out.println("/verificationProcedures - List of verification procedures requested");
		List<DeviceType> deviceTypes = deviceTypeRepo.findAll();
		for(DeviceType type : deviceTypes) {
			System.out.println(type.getName());
		}
		model.addAttribute("deviceTypes", deviceTypes);		
		List<VerificationProcedure> procedures = verificationProcedureRepo.findAll();
		model.addAttribute("procedures", procedures);
		return "verificationProcedures";
	}
	
	@GetMapping("/verificationProcedure/show")
	public String showVerificationProcedure(@RequestParam long id, Model model) {
		VerificationProcedure procedure = verificationProcedureRepo.findById(id);
		model.addAttribute("procedure", procedure);
		return "verificationProcedureResults";
	}
	
}