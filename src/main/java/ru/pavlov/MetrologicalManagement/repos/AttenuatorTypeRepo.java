package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.AttenuatorType;

public interface AttenuatorTypeRepo extends JpaRepository<AttenuatorType, Long>{
	AttenuatorType findById(long id);
	AttenuatorType findByName(String name);
}