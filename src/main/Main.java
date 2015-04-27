package main;

import net.webservicex.*;

public class Main {
	private static String countryName = "United Kingdom";
	public static void main(String[] args){
		Country factory = new Country();
		CountrySoap proxy = factory.getCountrySoap();
		
		
		String thisd = proxy.getCurrencies();
		
		String THISD = proxy.getCurrencyByCountry("Thailand");
		
		String country = proxy.getCountryByCountryCode("TH");
		
		
		System.out.println(proxy.getCountryByCurrencyCode("THB"));
		System.out.println("-----------------");
		System.out.println(proxy.getCurrencyByCountry(countryName));
		System.out.println("-----------------");
		System.out.println(get("Currency", proxy.getCurrencyByCountry(countryName)));
		
		
	}
	
	private static String get(String elementName, String xml) {
		String value = xml.split(String.format("<%s>", elementName))[1].split(String.format("</%s>", elementName))[0];
		if (value.charAt(0) == '<')
			return null;
		return value;
	}
}
