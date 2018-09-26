package fr.iut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Money {
	
	private double amount;
	private String currency;
	private Convertion convertion;
	
	private List<String> available = new ArrayList<>(Arrays.asList("EUR", "USD"));
	
	public Money() {
		
	}
	
	public Money(double amount, String currency) {
		if((amount < 0) || (currency == null) || (!available.contains(currency))) {
			throw new IllegalArgumentException(); 
		}
		
		this.amount = amount;
		this.currency = currency;
	}
	
	public void setConvertion(Convertion c){
		this.convertion = c;
	}
	
	public Convertion getConvertion() {
		return convertion;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void add(Money m) {
		this.add(m.getAmount(), m.getCurrency());
	}

	public void add(double namount, String ncurrency) {
		if(!available.contains(ncurrency)) {
			throw new IllegalArgumentException();
		}
		if(currency.equals("EUR")) {
			if(ncurrency.equals("USD")) {
				this.amount += namount*1.29;
			} else {
				this.amount += namount;
			}
		}else {
			if(ncurrency.equals("EUR")) {
				this.amount += namount/1.29;
			}else {
				this.amount += namount;
			}
		}
	}
}
