package ru.pavlov.MetrologicalManagement.domain.verifications;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

@Entity
@Table(name="veriication_procedures")
public class D3_34A_VerificationProcedure extends VerificationProcedure {
	
	@OneToMany(mappedBy = "verificationProcedure")
	private List<VSWRMeasurmentResult> vswrResults;
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private List<InitialAttenuationMeasurmentResult> initialAttenuationResults;
		
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private List<DifferentialAttenuationMeasurmentResult> differentialAttenuationResult;
	
	public List<InitialAttenuationMeasurmentResult> getInitialAttenuationResults() {
		return initialAttenuationResults;
	}
	public void setInitialAttenuationResults(List<InitialAttenuationMeasurmentResult> initialAttenuationResults) {
		this.initialAttenuationResults = initialAttenuationResults;
	}
	public List<DifferentialAttenuationMeasurmentResult> getDifferentialAttenuationResult() {
		return differentialAttenuationResult;
	}
	public void setDifferentialAttenuationResult(
			List<DifferentialAttenuationMeasurmentResult> differentialAttenuationResult) {
		this.differentialAttenuationResult = differentialAttenuationResult;
	}
	public List<VSWRMeasurmentResult> getVswrResults() {
		return vswrResults;
	}
	public void setVswrResults(List<VSWRMeasurmentResult> vswrResults) {
		this.vswrResults = vswrResults;
	}
}