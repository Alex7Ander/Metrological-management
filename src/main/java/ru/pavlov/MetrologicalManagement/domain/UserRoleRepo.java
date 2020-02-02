package ru.pavlov.MetrologicalManagement.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
	public UserRole findByUserId(int userId);
}
