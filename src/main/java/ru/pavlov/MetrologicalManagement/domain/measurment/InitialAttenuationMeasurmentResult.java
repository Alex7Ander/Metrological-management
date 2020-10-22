package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;

@Entity
@Table(name = "init_attenuation_results")
public class InitialAttenuationMeasurmentResult extends MeasurmentResult {
	
	@ManyToOne
	@JoinColumn(name = "verification_procedure_id")
	private VerificationProcedure verificationProcedure;
	
	public VerificationProcedure getVerificationProcedure() {
		return verificationProcedure;
	}
	public void setVerificationProcedure(VerificationProcedure verificationProcedure) {
		this.verificationProcedure = verificationProcedure;
	}
}
