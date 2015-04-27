package model;

import javax.swing.SwingWorker;
import javax.xml.bind.*;

import net.webservicex.Country;
import net.webservicex.CountrySoap;

public class Service {
	
	private Country factory;
	
	private CountrySoap proxy;
	
	private String countryName;
	
	private String countryCode;
	
	private String countryCurrency;
	
	private String countryCurrencyCode;
	
	private String countryISD;
	
	private String GMT;
	
	public Service(){
		
		factory = new Country();
		proxy = factory.getCountrySoap();
		
		this.countryName = "";
		this.countryCode = "";
		this.countryCurrency = "";
		this.countryCurrencyCode = "";
		this.countryISD = "";
		this.GMT = "";
	}
	
	public String[] getCountryDetails(){
		String[] details = {this.countryName, this.countryCode, this.countryCurrency, this.countryCurrencyCode, this.countryISD, this.GMT};
		
		return details;
	}
		
	public void setCountryDetails(String countryName, String countryCode, String countryCurrency, String countryCurrencyCode){
		
		//String[] details = {this.countryName, this.countryCode, this.countryCurrency, this.countryCurrencyCode, this.countryISD, this.GMT};
		
		if(countryName != this.countryName){
			this.countryName = countryName;
			
			String xml = proxy.getCurrencyByCountry(this.countryName);
			String xml1 = proxy.getISD(countryName);
			String xml2 = proxy.getGMTbyCountry(countryName);
			
			this.countryCode = this.get("CountryCode", xml);
			this.countryCurrency = this.get("Currency", xml);
			this.countryCurrency = this.get("CurrencyCode", xml);
			this.countryISD = this.get("code", xml1);
			this.GMT = this.get(GMT, xml2);
//			details = new String[] {this.countryName, this.countryCode, this.get("Currency", xml), this.get("CurrencyCode", xml), this.get("code", xml1), this.get(GMT, xml2)};
		}
		if(countryCode != this.countryCode){
			this.countryCode = countryCode;
			
			String xml = proxy.getCurrencyByCountry(this.countryName);
			String xml1 = proxy.getISD(countryName);
			String xml2 = proxy.getGMTbyCountry(countryName);
			String xml3 = proxy.getCountryByCountryCode(countryCode);
			
			this.countryName = this.get("Country", xml3);
			this.countryCurrency = this.get("Currency", xml);
			this.countryCurrencyCode = this.get("CurrencyCode", xml);
			this.countryISD = this.get("code", xml1);
			this.GMT = this.get(GMT, xml2);

		}
		if(countryCurrency != this.countryCurrency){
			this.countryCurrency = countryCurrency;
			
			String xml3 = proxy.getCurrencyCodeByCurrencyName(countryCurrency);
			this.countryCurrencyCode = this.get("CurrencyCode", xml3);
			
			String xml = proxy.getCountryByCurrencyCode(countryCurrencyCode);
			this.countryName = this.get("Name", xml);
			
			String xml2 = proxy.getGMTbyCountry(countryName);
			this.GMT = this.get(GMT, xml2);
			
			String xml1 = proxy.getISD(countryName);
			this.countryISD = this.get("code", xml1);
			
			this.countryCode = this.get("CountryCode", xml);
			
		}
		if(countryCurrencyCode != this.countryCurrencyCode){
			this.countryCurrencyCode = countryCurrencyCode;
			
			String xml = proxy.getCountryByCurrencyCode(this.countryCurrencyCode);
			
			this.countryName = this.get("Name", xml);
			this.countryCode = this.get("CountryCode", xml);
			this.countryCurrency = this.get("Currency", xml);
			
			String xml1 = proxy.getGMTbyCountry(this.countryName);
			String xml2 = proxy.getISD(this.countryName);
			
			this.GMT = this.get("GMT", xml1);
			this.countryISD = this.get("code",xml2);
		}
				
//		String[] details = {this.countryName, this.countryCode, this.countryCurrency, this.countryCurrencyCode, this.countryISD, this.GMT};
//		
//		return details;
	}
	
	private String get(String elementName, String xml) {
		String value = xml.split(String.format("<%s>", elementName))[1].split(String.format("</%s>", elementName))[0];
		if (value.charAt(0) == '<')
			return null;
		return value;
	}

	
	
}
