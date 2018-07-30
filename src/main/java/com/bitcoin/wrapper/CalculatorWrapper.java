package com.bitcoin.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Component;

@Component
public class CalculatorWrapper {
	
	public List<Double> calculateTop5HighestValues(List<Double> doubleValues) {
		Collections.sort(doubleValues, Collections.reverseOrder()); 
        return new ArrayList<Double>(doubleValues.subList(0, 5));
	}
	
	public Double calculateAverage(List<Double> doubleValues) {
		DescriptiveStatistics statistics = getDescriptiveStatistics(doubleValues);
		return statistics.getMean();
	}

	public Double calculateMedian(List<Double> doubleValues) {
		DescriptiveStatistics statistics = getDescriptiveStatistics(doubleValues);
		return statistics.getPercentile(new Double("50"));
	}

	public Double calculateDeviation(List<Double> doubleValues) {
		DescriptiveStatistics statistics = getDescriptiveStatistics(doubleValues);
		return statistics.getStandardDeviation();
	}

	private DescriptiveStatistics getDescriptiveStatistics(List<Double> doubleValues) {
		Double[] doubleNumbers = doubleValues.toArray(new Double[doubleValues.size()]);
		double[] primitiveDoubleNumbers = ArrayUtils.toPrimitive(doubleNumbers);
		return new DescriptiveStatistics(primitiveDoubleNumbers);
	}
}