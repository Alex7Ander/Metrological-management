package ru.pavlov.MetrologicalManagement.domain.wrappers;

import java.util.List;

import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;

public class DifferentialAttenuationMeasurmentResultWrapper {
	private List<DifferentialAttenuationMeasurmentResult> results;
	private int hashCode;
	public List<DifferentialAttenuationMeasurmentResult> getResults() {
		return results;
	}
	public void setResults(List<DifferentialAttenuationMeasurmentResult> results) {
		this.results = results;
	}
	public int getHashCode() {
		return hashCode;
	}
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
}
