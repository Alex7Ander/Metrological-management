package ru.pavlov.MetrologicalManagement.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.verifications.D1_13A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.domain.wrappers.DifferentialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.repos.DeviceTypeRepo;

@Controller
@RequestMapping("/attenuators/d1-13a")
public class D1_13A_controller {

	@Autowired
	private DeviceTypeRepo deviceTypeRepo;

	private Map<Integer, D1_13A_VerificationProcedure> verificationprocedures = new LinkedHashMap<>();
	
	@GetMapping
	public String d1_13a_Page(Model model){
		System.out.println("D1-13A page requested");
		DeviceType d1_13a_type = deviceTypeRepo.findByName("Д3-34А");
		model.addAttribute("type", d1_13a_type);
		
		D1_13A_VerificationProcedure procedure = new D1_13A_VerificationProcedure();
		int verificationProcedureHashCode = procedure.hashCode();
		verificationprocedures.put(verificationProcedureHashCode, procedure);
		model.addAttribute("procedureHashCode", verificationProcedureHashCode);
		return "d1-13a";
	}
	@RequestMapping(value="/getDifferentialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public String getDifferentialAttenuation(@RequestBody DifferentialAttenuationMeasurmentResultWrapper results, Model model) {
		String answer = "";
		
		model.addAttribute("answer", answer);
		return "lineAnswer";
	}
	
	
}