package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.VerificationProcedure;

@Entity
@Table(name = "vswr_results")
public class VSWRMeasurmentResult implements MeasurmentResult{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private double freq;
	private double result;
	private double error;
	
	@ManyToOne
	@JoinColumn(name = "verification_procedure_id")
	private VerificationProcedure verificationProcedure;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getFreq() {
		return freq;
	}
	public void setFreq(double freq) {
		this.freq = freq;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
}