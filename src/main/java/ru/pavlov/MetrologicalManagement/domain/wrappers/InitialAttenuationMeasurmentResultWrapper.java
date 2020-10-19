package ru.pavlov.MetrologicalManagement.domain.wrappers;

import java.util.List;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;

public class InitialAttenuationMeasurmentResultWrapper {

	private List<InitialAttenuationMeasurmentResult> results;
	private int hashCode;
	public List<InitialAttenuationMeasurmentResult> getResults() {
		return results;
	}
	public void setResults(List<InitialAttenuationMeasurmentResult> results) {
		this.results = results;
	}
	public int getHashCode() {
		return hashCode;
	}
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
}
