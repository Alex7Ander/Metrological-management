package ru.pavlov.MetrologicalManagement.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pavlov.MetrologicalManagement.domain.D3_34A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.wrappers.DifferentialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.domain.wrappers.InitialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.domain.wrappers.VSWRMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.services.verificationServices.D3_34A_VerificationService;

@Controller
@RequestMapping("/attenuators/d3-34a")
public class D3_34A_controller {

	@Autowired
	private D3_34A_VerificationService verificationService;
	
	private Map<Integer, D3_34A_VerificationProcedure> verificationProcedures = new HashMap<>();
	
	@GetMapping
	public String d3_34a_Page(Model model){
		System.out.println("D3-34A page requested");
		D3_34A_VerificationProcedure currentProcedure = new D3_34A_VerificationProcedure();
		int verificationProcedureHashCode = currentProcedure.hashCode();
		verificationProcedures.put(verificationProcedureHashCode, currentProcedure);
		model.addAttribute("procedureHashCode", verificationProcedureHashCode);
		return "d3-34a";
	}
	
	@RequestMapping(value="/getVSWR", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	//@ResponseBody
	public String getVSWR(@RequestBody VSWRMeasurmentResultWrapper results, Model model) {
		int currentProcedureHashCode = results.getHashCode();
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(currentProcedureHashCode);
		if(currentProcedure != null) {
			Map<Double,  VSWRMeasurmentResult> vswrInResults = new LinkedHashMap<>();
			Map<Double,  VSWRMeasurmentResult> vswrOutResults = new LinkedHashMap<>();
			for(VSWRMeasurmentResult result : results.getResults()) {
				System.out.println(result.toString());
				if(result.getPortNumber() == 1) {
					vswrInResults.put(result.getFreq(), result);
				}
				else {
					vswrOutResults.put(result.getFreq(), result);
				}
			}
			currentProcedure.setVswrInResults(vswrInResults);
			currentProcedure.setVswrOutResults(vswrOutResults);
			
			verificationService.setVerificationProcedure(currentProcedure);
			String answer = verificationService.verificateVswr();
			model.addAttribute("answer", answer);
		}
		return "lineAnswer";
	}
		
	@RequestMapping(value="/getInitialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public String getInitialAttenuation(@RequestBody InitialAttenuationMeasurmentResultWrapper results, Model model) {
		int currentProcedureHashCode = results.getHashCode();		
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		for(InitialAttenuationMeasurmentResult res : results.getResults()) {
			System.out.println(res.toString());
		}
		return "lineAnswer";
	}
	
	@RequestMapping(value="/getDifferentialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public String getDifferentialAttenuation(@RequestBody DifferentialAttenuationMeasurmentResultWrapper results) {
		int currentProcedureHashCode = results.getHashCode();		
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		for(DifferentialAttenuationMeasurmentResult res : results.getResults()) {
			System.out.println(res.toString());
		}
		return "sendingResult";
	}
}