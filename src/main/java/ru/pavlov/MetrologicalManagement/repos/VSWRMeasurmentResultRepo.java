package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

public interface VSWRMeasurmentResultRepo extends JpaRepository<VSWRMeasurmentResult, Long>{

}