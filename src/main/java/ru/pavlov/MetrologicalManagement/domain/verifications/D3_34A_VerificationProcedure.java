package ru.pavlov.MetrologicalManagement.domain.verifications;

import java.util.Map;

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
	@MapKey(name = "freq")
	private Map<Double, VSWRMeasurmentResult> vswrInResults;
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, VSWRMeasurmentResult> vswrOutResults;
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults;
		
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult;
	
	public Map<Double, VSWRMeasurmentResult> getVswrInResults() {
		return vswrInResults;
	}
	public void setVswrInResults(Map<Double, VSWRMeasurmentResult> vswrInResults) {
		this.vswrInResults = vswrInResults;
	}
	public Map<Double, InitialAttenuationMeasurmentResult> getInitialAttenuationResults() {
		return initialAttenuationResults;
	}
	public void setInitialAttenuationResults(Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults) {
		this.initialAttenuationResults = initialAttenuationResults;
	}
	public Map<Double, DifferentialAttenuationMeasurmentResult> getDifferentialAttenuationResult() {
		return differentialAttenuationResult;
	}
	public void setDifferentialAttenuationResult(
			Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult) {
		this.differentialAttenuationResult = differentialAttenuationResult;
	}
	public Map<Double, VSWRMeasurmentResult> getVswrOutResults() {
		return vswrOutResults;
	}
	public void setVswrOutResults(Map<Double, VSWRMeasurmentResult> vswrOutResults) {
		this.vswrOutResults = vswrOutResults;
	}
}