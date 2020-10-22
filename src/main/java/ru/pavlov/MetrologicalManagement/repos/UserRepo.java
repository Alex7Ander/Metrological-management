package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pavlov.MetrologicalManagement.domain.users.User;

public interface UserRepo extends JpaRepository<User, Long>{
	public User findByName(String name);
}