package com.bitcoin.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitcoin.dto.MercadoBitcoinDTO;
import com.bitcoin.service.BitcoinService;
import com.bitcoin.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class BitcoinAPIController {

	@Autowired
	private BitcoinService bitcoinService;

	@Autowired
	private CalculatorService calculatorService;

	@GetMapping("exercicie1/json")
	public ResponseEntity<String> getJson() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();

		Map<String, Object> map = new HashMap<String, Object>();

		getTop5(salesPrice, purchasePrices, map);
		getAverage(salesPrice, purchasePrices, map);
		getMedian(salesPrice, purchasePrices, map);
		getDeviation(salesPrice, purchasePrices, map);

		Gson gson = new Gson(); 
		String json = gson.toJson(map); 
		
		return ResponseEntity.ok(json);
	}

	private void getTop5(List<Double> salesPrice, List<Double> purchasePrices, Map<String, Object> map) {
		List<Double> topFiveSales = calculatorService.calculateTop5HighestValues(salesPrice);
		List<Double> topFivePurchase = calculatorService.calculateTop5HighestValues(purchasePrices);

		map.put("top_5_sales", topFiveSales);
		map.put("top_5_purchase", topFivePurchase);
	}

	private void getDeviation(List<Double> salesPrice, List<Double> purchasePrices, Map<String, Object> map) {
		Double salesDeviation = calculatorService.calculateDeviation(salesPrice);
		Double purchaseDeviation = calculatorService.calculateDeviation(purchasePrices);

		map.put("sales_deviation", salesDeviation);
		map.put("purchase_deviation", purchaseDeviation);
	}

	private void getMedian(List<Double> salesPrice, List<Double> purchasePrices, Map<String, Object> map) {
		Double salesMedian = calculatorService.calculateMedian(salesPrice);
		Double purchaseMedian = calculatorService.calculateMedian(purchasePrices);

		map.put("sales_median", salesMedian);
		map.put("purchase_median", purchaseMedian);
	}

	private void getAverage(List<Double> salesPrice, List<Double> purchasePrices, Map<String, Object> map) {
		Double salesAverage = calculatorService.calculateAverage(salesPrice);
		Double purshaceAverage = calculatorService.calculateAverage(purchasePrices);

		map.put("sales_average", salesAverage);
		map.put("purchase_average", purshaceAverage);
	}

	@GetMapping("exercicie2/top-5-sales")
	public ResponseEntity<List<Double>> top5Sales() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		List<Double> topFiveSales = calculatorService.calculateTop5HighestValues(salesPrice);
		return ResponseEntity.ok(topFiveSales);
	}

	@GetMapping("exercicie2/top-5-purchase")
	public ResponseEntity<List<Double>> top5Purchase() {
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();
		List<Double> topFivePurchase = calculatorService.calculateTop5HighestValues(purchasePrices);
		return ResponseEntity.ok(topFivePurchase);
	}

	@GetMapping("exercicie2/sales-average")
	public ResponseEntity<Double> salesAverage() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		Double salesAverage = calculatorService.calculateAverage(salesPrice);
		return ResponseEntity.ok(salesAverage);
	}

	@GetMapping("exercicie2/purchase-average")
	public ResponseEntity<Double> purchaseAverage() {
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();
		Double purshaceAverage = calculatorService.calculateAverage(purchasePrices);
		return ResponseEntity.ok(purshaceAverage);
	}

	@GetMapping("exercicie2/sales-median")
	public ResponseEntity<Double> salesMedian() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		Double salesMedian = calculatorService.calculateMedian(salesPrice);
		return ResponseEntity.ok(salesMedian);
	}

	@GetMapping("exercicie2/purchase-median")
	public ResponseEntity<Double> purchaseMedian() {
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();
		Double purchaseMedian = calculatorService.calculateMedian(purchasePrices);
		return ResponseEntity.ok(purchaseMedian);
	}

	@GetMapping("exercicie2/sales-deviation")
	public ResponseEntity<Double> salesDeviation() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		Double salesDeviation = calculatorService.calculateDeviation(salesPrice);
		return ResponseEntity.ok(salesDeviation);
	}

	@GetMapping("exercicie2/purchase-deviation")
	public ResponseEntity<Double> purchaseDeviation() {
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();
		Double purchaseDeviation = calculatorService.calculateDeviation(purchasePrices);
		return ResponseEntity.ok(purchaseDeviation);
	}

	@GetMapping("extra/transfers")
	public ResponseEntity<List<MercadoBitcoinDTO>> transfers() {
		List<MercadoBitcoinDTO> bitcoinList = bitcoinService.getAllTransfers();
		return ResponseEntity.ok(bitcoinList);
	}

	@GetMapping("extra/sell-prices")
	public ResponseEntity<List<Double>> transfersPricesOfSell() {
		List<Double> salesPrice = bitcoinService.getPricesOfSellTranfers();
		return ResponseEntity.ok(salesPrice);
	}

	@GetMapping("extra/buy-prices")
	public ResponseEntity<List<Double>> buyTransfers() {
		List<Double> purchasePrices = bitcoinService.getPricesOfPurchaseTranfers();
		return ResponseEntity.ok(purchasePrices);
	}
}
