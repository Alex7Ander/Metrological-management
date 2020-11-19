package ru.pavlov.MetrologicalManagement.domain.deviceTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DeviceType {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	private String gosNumber;
	private String standart;
	private String verificationSchema;
	private String link;
	
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
}
