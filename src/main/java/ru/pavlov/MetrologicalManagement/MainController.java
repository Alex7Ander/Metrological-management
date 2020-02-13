package ru.pavlov.MetrologicalManagement;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pavlov.MetrologicalManagement.domain.User;
import ru.pavlov.MetrologicalManagement.repos.UserRepo;
import ru.pavlov.MetrologicalManagement.security.CustomUserDetails;

@Controller
public class MainController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String main(@AuthenticationPrincipal CustomUserDetails currentUser, Map<String, Object> model) {
		model.put("userName", currentUser.getUsername());
		model.put("userAge", Integer.toString(currentUser.getUser().getAge()));
		model.put("userEmail", currentUser.getUser().getEmail());
		
		boolean added = false;
		for (Object cRole : currentUser.getAuthorities().toArray()) {
			String role = cRole.toString();
			if (role.equals("ROLE_ADMIN")) {
				model.put("linkToAdminPage", "toAdmin");
				added = true;
				break;
			}
		}
		if (!added) model.put("linkToAdminPage", "");
		return "main";
	}
	
	@GetMapping("toAdmin")
	public String toAdminMethod(Map<String, Object> model) {
		Iterable<User> users = userRepo.findAll();
		model.put("users", users);
		return "admin";
	}
	
	@GetMapping("toAdmin/toReg")
	public String toRegMethod() {
		return "reg";
	}
	
	@PostMapping("toAdmin/reg")
	public String reg(@RequestParam String name, @RequestParam String pass, @RequestParam String email, @RequestParam int age, Map<String, Object> model) {
		User user = new User(name, pass, email, age, "User");
		userRepo.save(user);
		Iterable<User> users = userRepo.findAll();
		model.put("users", users);
		return "admin";
	}
	
	@GetMapping("toAddVerification")
	public String toAddVerification() {
		return "addVerification";
	}
	
}