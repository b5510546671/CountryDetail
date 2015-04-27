package main;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import view.MainFrame;
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


		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		
		/* How to call GUI is here
		while (true) {
			try {
				mainFrame.showStatus("Connecting...");
				FrequencyUnit factory = new FrequencyUnit();
				FrequencyUnitSoap proxy = factory.getFrequencyUnitSoap();
				
				mainFrame.setSoap(proxy);
				break;
			} catch (WebServiceException e) {
				mainFrame.showStatus("Connection error");
				e.printStackTrace();
				
				JDialog.setDefaultLookAndFeelDecorated(true);
			    int response = JOptionPane.showConfirmDialog(null, "YES to check again or NO to close.", "Connection error!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			    
			    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
			    	System.exit(0);
			    	break;
			    }
			}
		}
		*/ 

	}
}
