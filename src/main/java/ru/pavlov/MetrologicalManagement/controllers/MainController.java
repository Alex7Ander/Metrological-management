package ru.pavlov.MetrologicalManagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.devices.Device;
import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;
import ru.pavlov.MetrologicalManagement.repos.DeviceRepo;
import ru.pavlov.MetrologicalManagement.repos.DeviceTypeRepo;
import ru.pavlov.MetrologicalManagement.repos.VerificationProcedureRepo;

@Controller
public class MainController {
	
	@Autowired
	private VerificationProcedureRepo verificationProcedureRepo;
	
	@Autowired
	private DeviceTypeRepo deviceTypeRepo;
	
	@Autowired
	private DeviceRepo deviceRepo;
	
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
		DeviceType verificatedDeviceType = procedure.getDevice().getType();
		String redirectLink = "redirect:" + verificatedDeviceType.getLink() + "/showVerificationProcedure?id=" + id;
		return redirectLink;
	}
	
	@GetMapping("/devicesList")
	public String getDevicesList(@RequestParam(required = false) String typeName, Model model) {
		List<DeviceType> deviceTypes = deviceTypeRepo.findAll();
		model.addAttribute("deviceTypes", deviceTypes);
		List<Device> devices = null;
		if(typeName != null) {
			DeviceType deviceType = deviceTypeRepo.findByName(typeName);
			if(deviceType != null) devices = deviceRepo.findByType(deviceType);
		}
		else {
			devices = deviceRepo.findAll();
		}
		model.addAttribute("devices", devices);
		return "devicesList";
	}
	
	@PostMapping("/deleteDevices")
	public String deleteDevices(@RequestParam List<Long> ids) {
		for(long id : ids) {
			Device device = deviceRepo.findById(id);
			if(device != null) deviceRepo.delete(device);
		}
		return "redirect:/devicesList";
	}	
}