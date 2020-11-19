package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;

public interface InitialAttenuationMeasurmentResultRepo extends JpaRepository<InitialAttenuationMeasurmentResult, Long> {

}
