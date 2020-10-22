package ru.pavlov.MetrologicalManagement.domain.deviceTypes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attenuator_types")
public class AttenuatorType extends DeviceType {

	private String waveguide;
	private double startFreq;
	private double stopFreq;
	private double initialAttenuation;
	private double startAttenuation;
	private double stopAttenuation;
	private double vswrLimit;
	
	public String getWaveguide() {
		return waveguide;
	}
	public void setWaveguide(String waveguide) {
		this.waveguide = waveguide;
	}
	public double getStartFreq() {
		return startFreq;
	}
	public void setStartFreq(double startFreq) {
		this.startFreq = startFreq;
	}
	public double getStopFreq() {
		return stopFreq;
	}
	public void setStopFreq(double stopFreq) {
		this.stopFreq = stopFreq;
	}
	public double getInitialAttenuation() {
		return initialAttenuation;
	}
	public void setInitialAttenuation(double initialAttenuation) {
		this.initialAttenuation = initialAttenuation;
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
	public double getVswrLimit() {
		return vswrLimit;
	}
	public void setVswrLimit(double vswrLimit) {
		this.vswrLimit = vswrLimit;
	}
	
}