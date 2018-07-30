package com.bitcoin.service.impl;

import java.util.List;

import com.bitcoin.service.CalculatorService;
import com.bitcoin.wrapper.CalculatorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
		
	@Autowired
	private CalculatorWrapper calculatorWrapper;
	
	@Override
	public List<Double> calculateTop5HighestValues(List<Double> doubleValues) {
		return calculatorWrapper.calculateTop5HighestValues(doubleValues);
	}

	@Override
	public Double calculateAverage(List<Double> doubleValues) {
		return calculatorWrapper.calculateAverage(doubleValues);
	}

	@Override
	public Double calculateMedian(List<Double> doubleValues) {
		return calculatorWrapper.calculateMedian(doubleValues);
	}

	@Override
	public Double calculateDeviation(List<Double> doubleValues) {
		return calculatorWrapper.calculateDeviation(doubleValues);
	}
}