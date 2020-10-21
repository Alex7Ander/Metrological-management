package ru.pavlov.MetrologicalManagement.exceptions;

public class FrequencyOutOfRangeException extends Exception{
	private static final long serialVersionUID = 1L;
	public FrequencyOutOfRangeException(double freq, String situation) {
		super("Частота " + freq + " вне диапазона (" + situation + ")");
	}	
}
