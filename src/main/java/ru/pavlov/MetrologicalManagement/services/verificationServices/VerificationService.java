package ru.pavlov.MetrologicalManagement.services.verificationServices;

import java.util.List;

import ru.pavlov.MetrologicalManagement.domain.measurment.MeasurmentResult;

public interface VerificationService {

	boolean verificate(List<MeasurmentResult> results);
}