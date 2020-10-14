package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "init_attenuation_results")
public class InitialAttenuationMeasurmentResult implements MeasurmentResult {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private double freq;
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
