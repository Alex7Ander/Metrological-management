package ru.pavlov.MetrologicalManagement.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VerificationProcedure {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private Date date;
	private double temperature;
	private double humidity;
	private double preasure;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getPreasure() {
		return preasure;
	}
	public void setPreasure(double preasure) {
		this.preasure = preasure;
	}
}