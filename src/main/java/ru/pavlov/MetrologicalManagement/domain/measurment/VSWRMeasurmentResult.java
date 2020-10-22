package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;

@Entity
@Table(name = "vswr_results")
public class VSWRMeasurmentResult extends MeasurmentResult{
	
	private int portNumber;
	
	@ManyToOne
	@JoinColumn(name = "verification_procedure_id")
	private VerificationProcedure verificationProcedure;
	
	public VerificationProcedure getVerificationProcedure() {
		return verificationProcedure;
	}
	public void setVerificationProcedure(VerificationProcedure verificationProcedure) {
		this.verificationProcedure = verificationProcedure;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	@Override
	public String toString() {
		String superString = super.toString();
		return superString + " portNumber - " +this.portNumber;
	}
}