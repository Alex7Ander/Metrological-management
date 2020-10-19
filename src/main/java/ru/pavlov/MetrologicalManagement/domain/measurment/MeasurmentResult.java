package ru.pavlov.MetrologicalManagement.domain.measurment;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MeasurmentResult {
	
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
	
	@Override
	public String toString() {
		return "id - " + this.id +
				" freq - " + this.freq +
				" value - " + this.value +
				" error - " + this.error;
	}
}