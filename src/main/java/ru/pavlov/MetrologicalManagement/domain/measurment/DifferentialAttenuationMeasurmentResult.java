package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DifferentialAttenuationMeasurmentResult implements MeasurmentResult {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private double freq;
	private double startAttenuation;
	private double stopAttenuation;
	private double value;
	private double error;
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
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	
	
}