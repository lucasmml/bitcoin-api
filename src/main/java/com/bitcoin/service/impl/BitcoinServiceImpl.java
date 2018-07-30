package com.bitcoin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitcoin.dto.MercadoBitcoinDTO;
import com.bitcoin.enums.TransferTypeEnum;
import com.bitcoin.service.BitcoinService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinServiceImpl implements BitcoinService {
	
	//TODO ALTERAR PARA VARIAVEL @Value("${url.mercado.bitcoin}")
	private static final String MERCADO_BITCOIN_URL = "https://www.mercadobitcoin.net/api/BTC/trades/1501871369/1501891200/";
	private static List<MercadoBitcoinDTO> bitcoinTranfers;
	
	public List<MercadoBitcoinDTO> getBitcoinTranfers() {
		if (bitcoinTranfers == null) {
			bitcoinTranfers = getAllTransfers();
		}
		return bitcoinTranfers;
	}
	
	@Override
	@Cacheable("transfers")
	public List<MercadoBitcoinDTO> getAllTransfers() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<MercadoBitcoinDTO>> response = restTemplate.exchange(
				  MERCADO_BITCOIN_URL, HttpMethod.GET, null,
				  new ParameterizedTypeReference<List<MercadoBitcoinDTO>>(){});
		
		List<MercadoBitcoinDTO> bitcoinList = response.getBody();
		return bitcoinList;
	}
	
	@Override
	@Cacheable("sellprices")
	public List<Double> getPricesOfSellTranfers() {
		List<Double> sellPrices = new ArrayList<Double>();
		List<MercadoBitcoinDTO> bitcoinTranfers = getBitcoinTranfers();
		
		for (MercadoBitcoinDTO mercadoBitcoinDTO : bitcoinTranfers) {
			if(TransferTypeEnum.SELL.getType().equals(mercadoBitcoinDTO.getType())) {
				sellPrices.add(mercadoBitcoinDTO.getPrice());
			}
		}
		return sellPrices;
	}
	
	@Override
	@Cacheable("purschaseprices")
	public List<Double> getPricesOfPurchaseTranfers() {
		List<Double> purshasePrices = new ArrayList<Double>();
		List<MercadoBitcoinDTO> bitcoinTranfers = getBitcoinTranfers();
		
		for (MercadoBitcoinDTO mercadoBitcoinDTO : bitcoinTranfers) {
			if(TransferTypeEnum.BUY.getType().equals(mercadoBitcoinDTO.getType())) {
				purshasePrices.add(mercadoBitcoinDTO.getPrice());
			}
			
		}
		return purshasePrices;
	}
}