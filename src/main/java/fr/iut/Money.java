package fr.iut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Money {
	
	private double amount;
	private String currency;
	private Convertion convertion = new Convertion();
	
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
		if(!available.contains(ncurrency) || namount < 0.0) {
			throw new IllegalArgumentException();
		}
		
		if(currency != ncurrency) {
			this.amount += namount*this.convertion.unit_Convertion(currency + "-" + ncurrency);
		} else {
			this.amount += namount;
		}
	}
	
	public void sub(Money m) {
		this.sub(m.getAmount(), m.getCurrency());
	}
	
	public void sub(double namount, String ncurrency) {
		if(!available.contains(ncurrency) || namount < 0.0) {
			throw new IllegalArgumentException();
		}
		
		if(currency != ncurrency) {
			this.amount -= namount*this.convertion.unit_Convertion(ncurrency + "-" + currency);
		} else {
			this.amount -= namount;
		}
	}
	
	
}
