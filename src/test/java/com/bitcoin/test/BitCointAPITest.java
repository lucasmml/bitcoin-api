package com.bitcoin.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bitcoin.SpringRestApplication;
import com.bitcoin.config.SwaggerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringRestApplication.class,
							SwaggerConfig.class}, 
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BitCointAPITest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void calculateTop5HighestValuesTest() throws Exception {
		ResponseEntity<Double[]> responseEntity = restTemplate.getForEntity("/exercicie2/top-5-sales", Double[].class);
		List<Double> top5Sales = Arrays.asList(responseEntity.getBody());
		
		ResponseEntity<Double[]> responseEntity2 = restTemplate.getForEntity("/exercicie2/top-5-purchase", Double[].class);
		List<Double> top5Purshace = Arrays.asList(responseEntity2.getBody());
		
		List<Double> top5SalesExpected = new ArrayList<Double>();
		top5SalesExpected.add(9723.0);
		top5SalesExpected.add(9723.0);
		top5SalesExpected.add(9723.0);
		top5SalesExpected.add(9720.0);
		top5SalesExpected.add(9711.0);
		
		List<Double> top5PurshaceExpected = new ArrayList<Double>();
		top5PurshaceExpected.add(9738.0);
		top5PurshaceExpected.add(9738.0);
		top5PurshaceExpected.add(9738.0);
		top5PurshaceExpected.add(9738.0);
		top5PurshaceExpected.add(9735.0);
		
		assertThat(top5Sales, is(top5SalesExpected));
		assertThat(top5Purshace, is(top5PurshaceExpected));
	}
	
	@Test
	public void calculateAverageTest() throws Exception {
		Double salesAverage = restTemplate.getForObject("/exercicie2/sales-average", Double.class);
		Double purchaseAverage = restTemplate.getForObject("/exercicie2/purchase-average", Double.class);
		assertThat(salesAverage, is(9584.356203644647));
		assertThat(purchaseAverage, is(9640.285890113852));
	}
	
	@Test
	public void calculateMedianTest() throws Exception {
		Double salesMedian = restTemplate.getForObject("/exercicie2/sales-median", Double.class);
		Double purchaseMedian = restTemplate.getForObject("/exercicie2/purchase-median", Double.class);
		assertThat(salesMedian, is(9593.00001));
		assertThat(purchaseMedian, is(9650.0));
	}
	
	@Test
	public void calculateDeviationTest() throws Exception {
		Double salesMedian = restTemplate.getForObject("/exercicie2/sales-deviation", Double.class);
		Double purchaseMedian = restTemplate.getForObject("/exercicie2/purchase-deviation", Double.class);
		assertThat(salesMedian, is(60.532469088179205));
		assertThat(purchaseMedian, is(53.396850410663816));
	}
}