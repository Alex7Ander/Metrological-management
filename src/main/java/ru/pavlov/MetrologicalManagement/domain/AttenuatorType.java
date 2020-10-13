package ru.pavlov.MetrologicalManagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attenuator_types")
public class AttenuatorType {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	private String gosNumber;
	private String standart;
	private String verificationSchema;
	private String waveguide;
	private String link;
	
	private double startFreq;
	private double stopFreq;
	private double initialAttenuation;
	private double startAttenuation;
	private double stopAttenuation;
	private double vswrLimit;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGosNumber() {
		return gosNumber;
	}
	public void setGosNumber(String gosNumber) {
		this.gosNumber = gosNumber;
	}
	public String getStandart() {
		return standart;
	}
	public void setStandart(String standart) {
		this.standart = standart;
	}
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getVerificationSchema() {
		return verificationSchema;
	}
	public void setVerificationSchema(String verificationSchema) {
		this.verificationSchema = verificationSchema;
	}
	public double getVswrLimit() {
		return vswrLimit;
	}
	public void setVswrLimit(double vswrLimit) {
		this.vswrLimit = vswrLimit;
	}
	
}