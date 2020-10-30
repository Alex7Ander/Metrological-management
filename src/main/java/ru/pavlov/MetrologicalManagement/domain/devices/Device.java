package ru.pavlov.MetrologicalManagement.domain.devices;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.pavlov.MetrologicalManagement.domain.deviceTypes.DeviceType;
import ru.pavlov.MetrologicalManagement.domain.verifications.VerificationProcedure;

@Entity
@Table(name="devices")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String serialNumber;
	private String owner;
	
	@OneToMany(mappedBy="device", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<VerificationProcedure> verificationProcedures;
	
	@ManyToOne
	private DeviceType type;

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

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
