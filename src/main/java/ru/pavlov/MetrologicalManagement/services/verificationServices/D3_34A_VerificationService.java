package ru.pavlov.MetrologicalManagement.services.verificationServices;

import java.util.List;

import ru.pavlov.MetrologicalManagement.domain.AttenuatorMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.MeasurmentResult;

public class D3_34A_VerificationService implements VerificationService{

	@Override
	public boolean verificate(List<MeasurmentResult> results) {
		for(MeasurmentResult result : results) {
			AttenuatorMeasurmentResult attResult = (AttenuatorMeasurmentResult)result;

		}
		return false;
	}


	
}
