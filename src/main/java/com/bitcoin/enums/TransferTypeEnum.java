package com.bitcoin.enums;

public enum TransferTypeEnum {
	SELL("sell"), BUY("buy");

	public String type;

	TransferTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}