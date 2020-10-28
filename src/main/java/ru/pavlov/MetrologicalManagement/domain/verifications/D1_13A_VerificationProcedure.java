package ru.pavlov.MetrologicalManagement.domain.verifications;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;

@Entity
@Table(name="veriication_procedures")
public class D1_13A_VerificationProcedure extends VerificationProcedure {

	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult;

	public Map<Double, DifferentialAttenuationMeasurmentResult> getDifferentialAttenuationResult() {
		return differentialAttenuationResult;
	}

	public void setDifferentialAttenuationResult(Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult) {
		this.differentialAttenuationResult = differentialAttenuationResult;
	}
}
