package ru.pavlov.MetrologicalManagement.services.verificationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import ru.pavlov.MetrologicalManagement.domain.measurment.DifferentialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.InitialAttenuationMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.measurment.VSWRMeasurmentResult;
import ru.pavlov.MetrologicalManagement.domain.verifications.D3_34A_VerificationProcedure;
import ru.pavlov.MetrologicalManagement.exceptions.FrequencyOutOfRangeException;

@Service
public class D3_34A_VerificationService {

	private final double initialAttLimitValue = 0.5;
	private final double lowFreqVswrLimitValue = 1.2;
	private final double hightFreqVswrLimitValue = 1.15;
	
	public D3_34A_VerificationProcedure verificationProcedure;
	
	public D3_34A_VerificationProcedure getVerificationProcedure() {
		return verificationProcedure;
	}

	public void setVerificationProcedure(D3_34A_VerificationProcedure verificationProcedure) {
		this.verificationProcedure = verificationProcedure;
	}
	
	/*
	 * VSWR
	 */
	public String verificateVswr() {
		List<Double> errorFreqsForVswrIn = new ArrayList<>();
		List<Double> errorFreqsForVswrOut = new ArrayList<>();
		
		for(double freq : this.verificationProcedure.getVswrInResults().keySet()) {
			VSWRMeasurmentResult currentRes = this.verificationProcedure.getVswrInResults().get(freq);
			try {
				if( ! this.verificateSingleVswrValue(freq, currentRes.getValue()) ) {
					currentRes.setVerificationStatus("Не годен");
					errorFreqsForVswrIn.add(freq);
					continue;
				}
			}
			catch(FrequencyOutOfRangeException fExp) {
				currentRes.setVerificationStatus(fExp.getMessage());
			}
			currentRes.setVerificationStatus("Годен");			
		}
		
		for(double freq : this.verificationProcedure.getVswrOutResults().keySet()) {
			VSWRMeasurmentResult currentRes = this.verificationProcedure.getVswrOutResults().get(freq);
			try {
				if( ! this.verificateSingleVswrValue(freq, currentRes.getValue()) ) {
					currentRes.setVerificationStatus("Не годен");
					errorFreqsForVswrOut.add(freq);
					continue;
				}
				currentRes.setVerificationStatus("Годен");
			}
			catch(FrequencyOutOfRangeException fExp) {
				currentRes.setVerificationStatus(fExp.getMessage());
			}			
		}
		
		String answer = null;
		if(errorFreqsForVswrIn.isEmpty() && errorFreqsForVswrOut.isEmpty()) {
			answer = "Значения КСВН входа и выхода не более 1,20 в диапазоне частот 12,05-12,30 и\nне более 1,15 вдиапазоне частот 12,30-17,44 ГГц";
		}
		else {
			StringBuilder strBuilder = new StringBuilder();
			if(!errorFreqsForVswrIn.isEmpty()) {
				strBuilder.append("Значения КСВН входа на частотах ");
				strBuilder.append(scanVswrErrorFreqList(errorFreqsForVswrIn));
			}
			if(!errorFreqsForVswrOut.isEmpty()) {
				strBuilder.append("Значения КСВН выхода на частотах ");
				strBuilder.append(scanVswrErrorFreqList(errorFreqsForVswrOut));
			}
			answer = strBuilder.toString();
		}
		return answer;
	}
	
	private boolean verificateSingleVswrValue(double freq, double value) throws FrequencyOutOfRangeException {
		double currentLimitvalue = 0;
		if(freq >= 12.05 || freq <=12.30) {
			currentLimitvalue = this.lowFreqVswrLimitValue;
		}
		else if(freq > 12.30 || freq <=17.44) {
			currentLimitvalue = this.hightFreqVswrLimitValue;
		}
		else {
			throw new FrequencyOutOfRangeException(freq, "Прибор Д3-34А работает в диапазоне от 12,05 до 17,44 ГГц");
		}
		if(value > currentLimitvalue) {
			return false;
		}
		return true;
	}
	
	private String scanVswrErrorFreqList(List<Double> errorFreqs) {
		StringBuilder strBuilder = new StringBuilder();
		for(int i = 0; i<errorFreqs.size(); i++) {
			double errorFreq = errorFreqs.get(i);
			strBuilder.append(errorFreq + " ГГц");
			if(i < errorFreqs.size()-1) strBuilder.append(", ");
		}
		strBuilder.append(" больше нормы.\n");
		return strBuilder.toString();
	}

	/*
	 * Initial attenuation
	 */
	public String verificateInitialAttenuation() {
		List<Double> errorFreqs = new ArrayList<>();
		Map<Double, InitialAttenuationMeasurmentResult> initialAttenuationResults = this.verificationProcedure.getInitialAttenuationResults();
		for(double freq : initialAttenuationResults.keySet()) {
			InitialAttenuationMeasurmentResult currentRes = initialAttenuationResults.get(freq);
			try {
				if(!this.verificateSingleInitialAttenuationValue(freq, currentRes.getValue())) {
					currentRes.setVerificationStatus("Не годен");
					errorFreqs.add(freq);
					continue;
				}
				currentRes.setVerificationStatus("Годен");
			}
			catch(FrequencyOutOfRangeException fExp) {
				currentRes.setVerificationStatus(fExp.getMessage());
			}			
		}
		
		String answer = null;
		if(errorFreqs.isEmpty()) {
			answer = "Начальное ослабление в рабочем диапазоне частот не более " + this.initialAttLimitValue + " дБ";
		}
		else {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("Начальное ослабление на частотах ");
			for (int i = 0; i < errorFreqs.size(); i++) {
				strBuilder.append(errorFreqs.get(i));
				if(i < errorFreqs.size() - 1) strBuilder.append(", ");
			}
			strBuilder.append(" выше нормы");
			answer = strBuilder.toString();
		}		
		return answer;
	}
	
	private boolean verificateSingleInitialAttenuationValue(double freq, double value) throws FrequencyOutOfRangeException {
		if(freq < 12.05 || freq > 17.44) {
			throw new FrequencyOutOfRangeException(freq, "Прибор Д3-34А работает в диапазоне от 12,05 до 17,44 ГГц");
		}
		if(value < initialAttLimitValue) {
			return true;
		}
		return false;
	}
	
	/*
	 * Differential attenuation
	 */	
	public String verificateDifferentialAttenuation() {
		List<Double> errorFreqs = new ArrayList<>();
		Map<Double, DifferentialAttenuationMeasurmentResult> differentialAttenuationResult = this.verificationProcedure.getDifferentialAttenuationResult();
		for(double freq : differentialAttenuationResult.keySet()) {
			DifferentialAttenuationMeasurmentResult currentRes = differentialAttenuationResult.get(freq);
			try {
				if(!verificateSingleDifferentialAttenuationValue(freq, 
																currentRes.getValue(), 
																currentRes.getStartAttenuation(), 
																currentRes.getStopAttenuation())) {
					currentRes.setVerificationStatus("Не годен");
					errorFreqs.add(freq);
					continue;
				}
				currentRes.setVerificationStatus("Годен");
			}
			catch(FrequencyOutOfRangeException fExp) {
				currentRes.setVerificationStatus(fExp.getMessage());
			}
		}
		
		String answer = null;
		if(errorFreqs.isEmpty()) {
			answer = "Разностное ослабление в рабочем диапазоне частот удовлетворяет заданным на данный тип нормам";
		}
		else {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("Разностное ослабление на частотах ");
			for (int i = 0; i < errorFreqs.size(); i++) {
				strBuilder.append(errorFreqs.get(i));
				if(i < errorFreqs.size() - 1) strBuilder.append(", ");
			}
			strBuilder.append(" за допуском");
			answer = strBuilder.toString();
		}		
		return answer;
	}
	
	private boolean verificateSingleDifferentialAttenuationValue(double freq, double value, double startAtt, double stopAtt) throws FrequencyOutOfRangeException {
		if(freq < 12.05 || freq > 17.44) {
			throw new FrequencyOutOfRangeException(freq, "Прибор Д3-34А работает в диапазоне от 12,05 до 17,44 ГГц");
		}
		double attenuationAbsolutValue = stopAtt - startAtt;
		double limitValue = 0;
		if(attenuationAbsolutValue < 50) {
			limitValue = 0.01 + 0.005*attenuationAbsolutValue;
		}
		else {
			limitValue = 0.26 + 0.04*(attenuationAbsolutValue-50);
		}
		
		double realAttenuationValue = attenuationAbsolutValue - value;
		if(realAttenuationValue > limitValue || realAttenuationValue < (-1)*limitValue) {
			return false;
		}
		return true;
	}
	
}