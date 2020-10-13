package ru.pavlov.MetrologicalManagement.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.MetrologicalManagement.domain.AttenuatorType;
import ru.pavlov.MetrologicalManagement.repos.AttenuatorTypeRepo;

@Controller
@RequestMapping("/attenuators/**")
public class AttenuatorsTypeController {

	@Autowired
	private AttenuatorTypeRepo attenuatorTypeRepo;
	
	@GetMapping("typeslist")
	public String getAttenuatorsTypesList(Model model) {
		List<AttenuatorType> types = attenuatorTypeRepo.findAll();
		model.addAttribute("types", types);
		return "attenuatorTypesList";
	}
	
	@GetMapping("getTypeInfo")
	public String getTypeInfo(@RequestParam long id, Model model) {
		AttenuatorType type = attenuatorTypeRepo.findById(id);
		model.addAttribute("attenuatorType", type);
		return "attenuatorTypeInfoResponse";
	}
	
	@GetMapping("editTypePage")
	public String getTypeEditPage(Model model, @RequestParam long id) {
		AttenuatorType type = attenuatorTypeRepo.findById(id);
		model.addAttribute("attenuatorType", type);
		return "editAttenuatorType";
	}
	
	@PostMapping("editType")
	public String editType(@RequestParam Map<String, String> allParametrs, Model model) {
		Long id = Long.parseLong(allParametrs.get("id"));
		allParametrs.remove("id");
		AttenuatorType type = attenuatorTypeRepo.findById((long)id);
		
		double startFreq = Double.parseDouble(allParametrs.get("startFreq"));
		type.setStartFreq(startFreq);
		allParametrs.remove("startFreq");
		
		double stopFreq = Double.parseDouble(allParametrs.get("stopFreq"));
		type.setStopFreq(stopFreq);
		allParametrs.remove("stopFreq");
		
		double initialAttenuation = Double.parseDouble(allParametrs.get("initialAttenuation"));
		type.setInitialAttenuation(initialAttenuation);
		allParametrs.remove("initialAttenuation");
		
		double startAttenuation = Double.parseDouble(allParametrs.get("startAttenuation"));
		type.setStartAttenuation(startAttenuation);
		allParametrs.remove("startAttenuation");
		
		double stopAttenuation = Double.parseDouble(allParametrs.get("stopAttenuation"));
		type.setStopAttenuation(stopAttenuation);
		allParametrs.remove("stopAttenuation");
		
		double vswrLimit = Double.parseDouble(allParametrs.get("vswrLimit"));
		type.setVswrLimit(vswrLimit);
		allParametrs.remove("vswrLimit");
		
		for(String key : allParametrs.keySet()) {
			String value = allParametrs.get(key);
			try {
				Field field = type.getClass().getDeclaredField(key);
				field.setAccessible(true);
				field.set(type, value);	
			}
			catch(NoSuchFieldException | IllegalAccessException reflExp) {
				System.out.println("Ошибка при изменении значения поля: " + key);
				reflExp.printStackTrace();
			}
		}
		attenuatorTypeRepo.save(type);
		List<AttenuatorType> types = attenuatorTypeRepo.findAll();
		model.addAttribute("types", types);
		return "attenuatorTypesList";
	}
	
	@GetMapping("d1-13")
	public String d1_13_Page(){
		return "d1-13";
	}
	

	
}