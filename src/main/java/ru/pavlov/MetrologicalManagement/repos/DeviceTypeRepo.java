package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;

public interface DeviceTypeRepo extends JpaRepository<DeviceType, Long> {
	DeviceType findByName(String name);
	
}
