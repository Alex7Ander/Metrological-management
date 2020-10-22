package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;

@Entity
@Table(name = "differential_attenuation_results")
public class DifferentialAttenuationMeasurmentResult extends MeasurmentResult {
	
	private double startAttenuation;
	private double stopAttenuation;
	
	@ManyToOne
	@JoinColumn(name = "verification_procedure_id")
	private VerificationProcedure verificationProcedure;
	
	public double getStartAttenuation() {
		return startAttenuation;
	}
	public void setStartAttenuation(double startAttenuation) {
		this.startAttenuation = startAttenuation;
	}
	public double getStopAttenuation() {
		return stopAttenuation;
	}
	public void setStopAttenuation(double stopAttenuation) {
		this.stopAttenuation = stopAttenuation;
	}
	public VerificationProcedure getVerificationProcedure() {
		return verificationProcedure;
	}
	public void setVerificationProcedure(VerificationProcedure verificationProcedure) {
		this.verificationProcedure = verificationProcedure;
	}
	@Override
	public String toString() {
		String superString = super.toString();
		return superString + " startAttenuation - " + this.startAttenuation + " stopAttenuation - " + this.stopAttenuation;
	}	
}