package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.devices.Device;

public interface DeviceRepo extends JpaRepository<Device, Long> {

	Device findByTypeAndSerialNumber(DeviceType type, String serialNumber);
}
