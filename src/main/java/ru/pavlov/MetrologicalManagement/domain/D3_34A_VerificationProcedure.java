package ru.pavlov.MetrologicalManagement.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

@Entity
@Table(name="veriication_procedures")
public class D3_34A_VerificationProcedure extends VerificationProcedure {
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, VSWRMeasurmentResult> vswrResults;
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults;
		
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult;
	
	@OneToOne
	private D3_34A device;	
	public D3_34A getDevice() {
		return device;
	}
	public void setDevice(D3_34A device) {
		this.device = device;
	}
	public Map<Double, VSWRMeasurmentResult> getVswrResults() {
		return vswrResults;
	}
	public void setVswrResults(Map<Double, VSWRMeasurmentResult> vswrResults) {
		this.vswrResults = vswrResults;
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
}