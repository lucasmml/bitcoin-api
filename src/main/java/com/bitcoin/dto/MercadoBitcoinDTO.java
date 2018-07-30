package com.bitcoin.dto;

import java.util.Date;

public class MercadoBitcoinDTO {

	private Date date;
	private Double price;
	private Double amount;
	private Integer tid;
	private String type;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(long date) {
		this.date = new Date(date * 1000);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MercadoBitcoinDTO [date=" + date + ", price=" + price + ", amount=" + amount + ", tid=" + tid + ", type=" + type + "]";
	}
}
