package com.bitcoin.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.bitcoin.SpringRestApplication;
import com.bitcoin.config.SwaggerConfig;
import com.bitcoin.wrapper.CalculatorWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringRestApplication.class,
							SwaggerConfig.class}, 
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorWrapperTest {

	@Autowired
	private CalculatorWrapper calculatorWrapper;
	
	@Test
	public void calculateTop5HighestValuesTest() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(4.01);
		numbers.add(5.0);
		numbers.add(2.5);
		numbers.add(2.0);
		numbers.add(8.08);
		numbers.add(10.2);
		numbers.add(7.0);
		List<Double> top5 = calculatorWrapper.calculateTop5HighestValues(numbers);
		
		List<Double> top5Expected = new ArrayList<Double>();
		top5Expected.add(10.2);
		top5Expected.add(8.08);
		top5Expected.add(7.0);
		top5Expected.add(5.0);
		top5Expected.add(4.01);
		
		assertThat(top5, is(top5Expected));
	}
	
	@Test
	public void calculateTop5HighestValuesTest2() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(14.01);
		numbers.add(5.0);
		numbers.add(22.5);
		numbers.add(9.99);
		numbers.add(29.0);
		numbers.add(8.08);
		numbers.add(7.0);
		numbers.add(5.6);
		numbers.add(10.2);
		numbers.add(29.0);
		numbers.add(17.0);
		List<Double> top5 = calculatorWrapper.calculateTop5HighestValues(numbers);
		
		List<Double> top5Expected = new ArrayList<Double>();
		top5Expected.add(29.0);
		top5Expected.add(29.0);
		top5Expected.add(22.5);
		top5Expected.add(17.0);
		top5Expected.add(14.01);
		
		assertThat(top5, is(top5Expected));
	}
	
	@Test
	public void calculateAverageTest() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(1.0);
		numbers.add(3.0);
		Double median = calculatorWrapper.calculateAverage(numbers);
		assertThat(median, is(2.0));
	}
	
	@Test
	public void calculateAverageTest2() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(1.0);
		numbers.add(2.0);
		numbers.add(3.0);
		numbers.add(7.0);
		numbers.add(8.0);
		numbers.add(10.0);
		Double median = calculatorWrapper.calculateAverage(numbers);
		assertThat(median, is(5.166666666666667));
	}
	
	@Test
	public void calculateMedianTest() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(1.0);
		numbers.add(3.0);
		numbers.add(3.0);
		numbers.add(6.0);
		numbers.add(7.0);
		numbers.add(8.0);
		numbers.add(9.0);
		Double median = calculatorWrapper.calculateMedian(numbers);
		assertThat(median, is(6.0));
	}
	
	@Test
	public void calculateMedianTest2() throws Exception {
		List<Double> number = new ArrayList<Double>();
		number.add(4.0);
		number.add(5.0);
		number.add(7.0);
		number.add(4.0);
		number.add(7.0);
		Double median = calculatorWrapper.calculateMedian(number);
		assertThat(median, is(5.0));
	}
	
	@Test
	public void calculateDeviationTest() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(1.3);
		numbers.add(0.3);
		numbers.add(0.1);
		
		Double median = calculatorWrapper.calculateDeviation(numbers);
		assertThat(median, is(0.6429100507328638));
	}
	
	@Test
	public void calculateDeviationTest2() throws Exception {
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(2.1);
		numbers.add(3.1);
		numbers.add(4.1);
		
		Double median = calculatorWrapper.calculateDeviation(numbers);
		assertThat(median, is(0.9999999999999998));
	}
}