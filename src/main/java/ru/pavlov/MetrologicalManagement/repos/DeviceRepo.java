package ru.pavlov.MetrologicalManagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.devices.Device;

public interface DeviceRepo extends JpaRepository<Device, Long> {
	
	List<Device> findByType(DeviceType type);
	Device findById(long id);
	Device findByTypeAndSerialNumber(DeviceType type, String serialNumber);
}
