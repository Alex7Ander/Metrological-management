package ru.pavlov.MetrologicalManagement.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

@Entity
@Table(name="veriication_procedures")
public class D3_34A_VerificationProcedure implements VerificationProcedure {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private Date date;
	private double temperature;
	private double humidity;
	private double preasure;
	
	@OneToMany(mappedBy = "verificationProcedure")
	@MapKey(name = "freq")
	private Map<Double, VSWRMeasurmentResult> vswrResults;
	
	@OneToOne
	private D3_34A device;
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
	public D3_34A getDevice() {
		return device;
	}
	public void setDevice(D3_34A device) {
		this.device = device;
	}
}
