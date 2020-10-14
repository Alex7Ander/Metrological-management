package ru.pavlov.MetrologicalManagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="atts_D3_34A")
public class D3_34A {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String serialNumber;
	
	@OneToOne
	private D3_34A_VerificationProcedure lastVerification;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public D3_34A_VerificationProcedure getLastVerification() {
		return lastVerification;
	}

	public void setLastVerification(D3_34A_VerificationProcedure lastVerification) {
		this.lastVerification = lastVerification;
	}
}
