package ru.pavlov.stydySpringSecurity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.pavlov.stydySpringSecurity.domain.User;
import ru.pavlov.stydySpringSecurity.domain.UserRepo;

@Controller
public class MainController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String main() {
		return "main";
	}
	
	@GetMapping("toAdmin")
	public String toAdminMethod(Map<String, Object> model) {
		Iterable<User> users = userRepo.findAll();
		model.put("users", users);
		return "admin";
	}
	
	@GetMapping("toReg")
	public String toRegMethod() {
		return "reg";
	}
	
	@PostMapping("reg")
	public String reg(@RequestParam String name, @RequestParam String pass, @RequestParam String email, @RequestParam int age, Map<String, Object> model) {
		User user = new User(name, pass, email, age, "User");
		userRepo.save(user);
		Iterable<User> users = userRepo.findAll();
		model.put("users", users);
		return "admin";
	}
	
}