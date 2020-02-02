package ru.pavlov.stydySpringSecurity.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
	public User findByName(String name);
}