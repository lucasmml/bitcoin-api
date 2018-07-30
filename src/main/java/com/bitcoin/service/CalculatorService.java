package com.bitcoin.service;

import java.util.List;

public interface CalculatorService {

	List<Double> calculateTop5HighestValues(List<Double> doubleValues);
	
	Double calculateAverage(List<Double> doubleValues);

	Double calculateMedian(List<Double> doubleValues);

	Double calculateDeviation(List<Double> doubleValues);

}