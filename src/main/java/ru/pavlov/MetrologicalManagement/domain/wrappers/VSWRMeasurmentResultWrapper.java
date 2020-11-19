package ru.pavlov.MetrologicalManagement.domain.wrappers;

import java.util.List;

import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;

public class VSWRMeasurmentResultWrapper {

	private List<VSWRMeasurmentResult> results;
	private int hashCode;

	public List<VSWRMeasurmentResult> getResults() {
		return results;
	}

	public void setResults(List<VSWRMeasurmentResult> results) {
		this.results = results;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
	
}