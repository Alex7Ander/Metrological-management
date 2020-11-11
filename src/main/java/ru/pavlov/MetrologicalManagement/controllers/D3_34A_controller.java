package ru.pavlov.MetrologicalManagement.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.AttenuatorType;
import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.devices.Device;
import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.MeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.verifications.D3_34A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.domain.wrappers.DifferentialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.domain.wrappers.InitialAttenuationMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.domain.wrappers.VSWRMeasurmentResultWrapper;
import ru.pavlov.MetrologicalManagement.services.verificationServices.D3_34A_VerificationService;
import ru.pavlov.MetrologicalManagement.repos.*;

@Controller
@RequestMapping("/attenuators/d3-34a")
public class D3_34A_controller {

	private String datePattern = "yyyy-MM-dd";
	private DateFormat dateFormat = new SimpleDateFormat(datePattern);
	
	
	@Autowired
	private AttenuatorTypeRepo attenuatorTypeRepo;
	
	@Autowired
	private DeviceTypeRepo deviceTypeRepo;
	
	@Autowired
	private VerificationProcedureRepo verificationProcedureRepo;
	
	@Autowired
	private DeviceRepo deviceRepo;
	
	@Autowired
	private D3_34A_VerificationService verificationService;
	
	@Autowired
	VSWRMeasurmentResultRepo vswrMeasurmentResultRepo;
	
	@Autowired
	InitialAttenuationMeasurmentResultRepo initialAttenuationMeasurmentResultRepo;
	
	@Autowired
	DifferentialAttenuationMeasurmentResultRepo differentialAttenuationMeasurmentResultRepo;
	
	private Map<Integer, D3_34A_VerificationProcedure> verificationProcedures = new HashMap<>();
	
	@GetMapping
	public String d3_34a_Page(Model model){
		System.out.println("D3-34A page requested");
		DeviceType d3_34a_type = deviceTypeRepo.findByName("Д3-34А");
		model.addAttribute("type", d3_34a_type);
		
		D3_34A_VerificationProcedure currentProcedure = new D3_34A_VerificationProcedure();
		int verificationProcedureHashCode = currentProcedure.hashCode();
		verificationProcedures.put(verificationProcedureHashCode, currentProcedure);
		model.addAttribute("procedureHashCode", verificationProcedureHashCode);
		return "d3-34a";
	}
	
	@RequestMapping(value="/getMainData", method=RequestMethod.POST)
	public String getMainData(@RequestParam int procedureHashCode,
								@RequestParam String serialNumber,
								@RequestParam String date,
								@RequestParam double temperature, 
								@RequestParam double humidity,
								@RequestParam double preasure, Model model) {
		System.out.println("Getting main info for D3-34A attenuator");
		StringBuilder answer = new StringBuilder();
		AttenuatorType d3_34a_type = attenuatorTypeRepo.findByName("Д3-34А");
		Device currentDevice = deviceRepo.findByTypeAndSerialNumber(d3_34a_type, serialNumber);
		if(currentDevice == null) {
			currentDevice = new Device();
			currentDevice.setSerialNumber(serialNumber);
			currentDevice.setType(d3_34a_type);
			deviceRepo.save(currentDevice);
			answer.append("Аттенюатор Д3-34А с серийным номером " + serialNumber + " добавлен в БД\n");
		}
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(procedureHashCode);
		Date verificationDate = null;
		try {
			verificationDate = dateFormat.parse(date);
		} catch (ParseException e) {
			verificationDate = new Date();
			e.printStackTrace();
		}
		currentProcedure.setDevice(currentDevice);
		currentProcedure.setDate(verificationDate);
		currentProcedure.setTemperature(temperature);
		currentProcedure.setHumidity(humidity);
		currentProcedure.setPreasure(preasure);
		
		answer.append("Процедура поверки создана");
		model.addAttribute("answer", answer.toString());
		return "lineAnswer";
	}
	
	@RequestMapping(value="/getVSWR", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public String getVSWR(@RequestBody VSWRMeasurmentResultWrapper results, Model model) {
		int currentProcedureHashCode = results.getHashCode();
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(currentProcedureHashCode);
		String answer = null;
		if(currentProcedure != null) {
			Map<Double,  VSWRMeasurmentResult> vswrInResults = new LinkedHashMap<>();
			Map<Double,  VSWRMeasurmentResult> vswrOutResults = new LinkedHashMap<>();
			for(VSWRMeasurmentResult result : results.getResults()) {
				System.out.println(result.toString());
				result.setVerificationProcedure(currentProcedure);
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
			answer = verificationService.verificateVswr();			
		}
		else {
			answer = "Ошибка! Не удалось найти объект текущей процедуры поверки. Обратитесь к разработчикам.";
		}
		model.addAttribute("answer", answer);
		return "lineAnswer";
	}
		
	@RequestMapping(value="/getInitialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public String getInitialAttenuation(@RequestBody InitialAttenuationMeasurmentResultWrapper results, Model model) {
		int currentProcedureHashCode = results.getHashCode();
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(currentProcedureHashCode);
		String answer = null;
		if(currentProcedure != null) {
			Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults = new LinkedHashMap<>();
			for(InitialAttenuationMeasurmentResult result : results.getResults()) {
				System.out.println(result.toString());
				result.setVerificationProcedure(currentProcedure);
				initialAttenuationResults.put(result.getFreq(), result);
			}
			currentProcedure.setInitialAttenuationResults(initialAttenuationResults);
			verificationService.setVerificationProcedure(currentProcedure);
			answer = verificationService.verificateInitialAttenuation();
		}
		else {
			answer = "Ошибка! Не удалось найти объект текущей процедуры поверки. Обратитесь к разработчикам.";
		}
		model.addAttribute("answer", answer);
		return "lineAnswer";
	}
	
	@RequestMapping(value="/getDifferentialAttenuation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public String getDifferentialAttenuation(@RequestBody DifferentialAttenuationMeasurmentResultWrapper results, Model model) {
		int currentProcedureHashCode = results.getHashCode();		
		System.out.println("currentProcedureHashCode = " + currentProcedureHashCode);
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(currentProcedureHashCode);
		String answer = null;
		if(currentProcedure != null) {
			Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult = new LinkedHashMap<>();
			for(DifferentialAttenuationMeasurmentResult result : results.getResults()) {
				System.out.println(result.toString());
				result.setVerificationProcedure(currentProcedure);
				differentialAttenuationResult.put(result.getFreq(), result);
			}
			currentProcedure.setDifferentialAttenuationResult(differentialAttenuationResult);
			verificationService.setVerificationProcedure(currentProcedure);
			answer = verificationService.verificateDifferentialAttenuation();
		}
		else {
			answer = "Ошибка! Не удалось найти объект текущей процедуры поверки. Обратитесь к разработчикам.";
		}

		model.addAttribute("answer", answer);
		return "lineAnswer";
	}
	
	@PostMapping("saveVerificationProcedure")
	public String saveVerificationProcedure(@RequestParam int procedureHashCode, Model model) {
		System.out.println("Start saving verification procedure of D3-34A (with temp hash code " + procedureHashCode + ")");
		D3_34A_VerificationProcedure currentProcedure = verificationProcedures.get(procedureHashCode);
		
		System.out.println("Saving procedure object");
		verificationProcedureRepo.save(currentProcedure);
		
		System.out.println("Saving VSWR");
		for(Double freq : currentProcedure.getVswrInResults().keySet()) {
			VSWRMeasurmentResult result = currentProcedure.getVswrInResults().get(freq);
			vswrMeasurmentResultRepo.save(result);
		}
		for(Double freq : currentProcedure.getVswrOutResults().keySet()) {
			VSWRMeasurmentResult result = currentProcedure.getVswrInResults().get(freq);
			vswrMeasurmentResultRepo.save(result);
		}
		
		System.out.println("Saving initial attenuation");
		for(Double freq : currentProcedure.getInitialAttenuationResults().keySet()) {
			InitialAttenuationMeasurmentResult result = currentProcedure.getInitialAttenuationResults().get(freq);
			initialAttenuationMeasurmentResultRepo.save(result);
		}
		
		System.out.println("Saving differential attenuation");
		for(Double freq : currentProcedure.getDifferentialAttenuationResult().keySet()) {
			DifferentialAttenuationMeasurmentResult result = currentProcedure.getDifferentialAttenuationResult().get(freq);
			differentialAttenuationMeasurmentResultRepo.save(result);
		}
		
		String answer = "Результаты процедуры поверки успешно сохранены (id = "+currentProcedure.getId()+")";
		System.out.println("id after saving " + currentProcedure.getId());		
		model.addAttribute("answer", answer);
		return "lineAnswer";
	}
	
	@GetMapping("showVerificationProcedure")
	public String showVerificationProcedure(@RequestParam long id, Model model) {
		D3_34A_VerificationProcedure procedure = (D3_34A_VerificationProcedure)verificationProcedureRepo.findById(id);
		
		Map<Double, VSWRMeasurmentResult> vswrInResults = procedure.getVswrInResults();
		Set<Double> freqs = vswrInResults.keySet();
		model.addAttribute("freqs", freqs);
		
		model.addAttribute("vswrInResults", vswrInResults);
		model.addAttribute("procedure", procedure);
		
		return "d3-34a_verificationProcedureResults";
	}
	
}