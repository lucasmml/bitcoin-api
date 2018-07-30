package com.bitcoin.service;

import java.util.List;

import com.bitcoin.dto.MercadoBitcoinDTO;

public interface BitcoinService {

	List<MercadoBitcoinDTO> getAllTransfers();

	List<Double> getPricesOfSellTranfers();

	List<Double> getPricesOfPurchaseTranfers();
}