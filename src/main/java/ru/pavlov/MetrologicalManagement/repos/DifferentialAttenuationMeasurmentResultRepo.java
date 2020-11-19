package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;

public interface DifferentialAttenuationMeasurmentResultRepo extends JpaRepository<DifferentialAttenuationMeasurmentResult, Long> {

}