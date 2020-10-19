package ru.pavlov.MetrologicalManagement.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pavlov.MetrologicalManagement.domain.D3_34A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.wrappers.InitialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.domain.wrappers.VSWRMeasurmentResultWrapper;

@Controller
@RequestMapping("/attenuators/d3-34a")
public class D3_34A_controller {

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
	@ResponseBody
	public String getVSWR(@RequestBody VSWRMeasurmentResultWrapper results) {
		int currentProcedureHashCode = results.getHashCode();		
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		for(VSWRMeasurmentResult res : results.getResults()) {
			System.out.println(res.toString());
		}
		return "";
	}
		
	@RequestMapping(value="/getInitialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public String getInitialAttenuation(@RequestBody InitialAttenuationMeasurmentResultWrapper results) {
		int currentProcedureHashCode = results.getHashCode();		
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		for(InitialAttenuationMeasurmentResult res : results.getResults()) {
			System.out.println(res.toString());
		}
		return "";
	}
	
	@PostMapping("/getDifferentialAttenuation")
	public String getDifferentialAttenuation(@RequestParam Map<String, String> allParametrs, Model model) {
		int procedureCode = Integer.parseInt(allParametrs.get("code"));
		System.out.println("Get differential attenuation for procedure with code: " + procedureCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(procedureCode);
		allParametrs.remove("code");
		
		Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResults = new LinkedHashMap<>();
		
		for(String key : allParametrs.keySet()) {			
			double value = 0;
			try {
				value = Double.parseDouble(allParametrs.get(key));
			}
			catch(NumberFormatException nfExp) {
				System.err.println("NumberFormatException thrown while parsing value for " + key);
			}
			String strFreq = key.substring(key.lastIndexOf('_') + 1);
			double freq = 0;
			try {
				freq = Double.parseDouble(strFreq);
			}
			catch(NumberFormatException nfExp) {
				System.err.println("NumberFormatException thrown while parsing freq for " + key);
				continue;
			}
			
			String strStartAtt = key.substring(0, key.lastIndexOf('-'));
			double startAtt = 0;
			try {
				startAtt = Double.parseDouble(strStartAtt);
			}
			catch(NumberFormatException nfExp) {
				System.err.println("NumberFormatException thrown while parsing start attenuation for " + key);
				continue;
			}
			
			String strStopAtt = key.substring(key.lastIndexOf('-') + 1, key.lastIndexOf('f') - 1);
			double stopAtt = 0;
			try {
				stopAtt = Double.parseDouble(strStopAtt);
			}
			catch(NumberFormatException nfExp) {
				System.err.println("NumberFormatException thrown while parsing stop attenuation for " + key);
				continue;
			}
			
			System.out.println(freq + ": " + value);
			DifferentialAttenuationMeasurmentResult result = new DifferentialAttenuationMeasurmentResult();
			result.setFreq(freq);
			result.setValue(value);
			result.setStartAttenuation(startAtt);
			result.setStopAttenuation(stopAtt);
			result.setError(0);
			differentialAttenuationResults.put(freq, result);
		}		
		currentProcedure.setDifferentialAttenuationResult(differentialAttenuationResults);
		model.addAttribute("procedure", "Отправка значений разностного ослабления");
		model.addAttribute("status", "Завершено");
		return "sendingResult";
	}
}