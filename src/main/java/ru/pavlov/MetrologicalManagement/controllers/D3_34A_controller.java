package ru.pavlov.MetrologicalManagement.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.MetrologicalManagement.domain.D3_34A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

@Controller
@RequestMapping("/attenuators/d3-34a")
public class D3_34A_controller {

	private Map<Integer, D3_34A_VerificationProcedure> verificationProcedures = new HashMap<>();
	@GetMapping
	public String d3_34a_Page(Model model){
		System.out.println("D3-34A page requested");
		D3_34A_VerificationProcedure currentProcedure = new D3_34A_VerificationProcedure();
		int verificationProcedureCode = currentProcedure.hashCode();
		verificationProcedures.put(verificationProcedureCode, currentProcedure);
		model.addAttribute("procedureCode", verificationProcedureCode);
		return "d3-34a";
	}
	
	@PostMapping("/getVSWR")
	public String d3_34a_getvswr(@RequestParam Map<String, String> allParametrs, Model model){
		
		StringBuilder resultStatus = new StringBuilder();
		int procedureCode = Integer.parseInt(allParametrs.get("code"));
		System.out.println("Get vswr for procedure with code: " + procedureCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(procedureCode);
		allParametrs.remove("code");
		
		Map<Double, VSWRMeasurmentResult> vswrInResults = new LinkedHashMap<>();
		Map<Double, VSWRMeasurmentResult> vswrOutResults = new LinkedHashMap<>();
		
		for(String key : allParametrs.keySet()) {
			Map<Double, VSWRMeasurmentResult> currentMap = vswrOutResults;
			if(key.contains("in")) {
				currentMap = vswrInResults;
			}
			
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
			
			System.out.println(freq + ": " + value);
			VSWRMeasurmentResult result = new VSWRMeasurmentResult();
			result.setFreq(freq);
			result.setValue(value);
			result.setError(0);
			currentMap.put(freq, result);
		}
		resultStatus.append("Завершено. Получено значенией КСВН входа - " + vswrInResults.size() + "; КСВН выхода -  " + vswrOutResults.size());
		currentProcedure.setVswrInResults(vswrInResults);
		currentProcedure.setVswrOutResults(vswrOutResults);
		
		model.addAttribute("procedure", "Отправка значений КСВН");
		model.addAttribute("status", resultStatus.toString());
		return "sendingResult";
	}
	
	@PostMapping("/getInitialAttenuation")
	public String getInitialAttenuation(@RequestParam Map<String, String> allParametrs, Model model) {
		int procedureCode = Integer.parseInt(allParametrs.get("code"));
		System.out.println("Get initial attenuation for procedure with code: " + procedureCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(procedureCode);
		allParametrs.remove("code");
		
		Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults = new LinkedHashMap<>();
		
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
			
			System.out.println(freq + ": " + value);
			InitialAttenuationMeasurmentResult result = new InitialAttenuationMeasurmentResult();
			result.setFreq(freq);
			result.setValue(value);
			result.setError(0);
			initialAttenuationResults.put(freq, result);
		}
		currentProcedure.setInitialAttenuationResults(initialAttenuationResults);
		
		model.addAttribute("procedure", "Отправка значений начального ослабления");
		model.addAttribute("status", "Завершено");
		return "sendingResult";
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