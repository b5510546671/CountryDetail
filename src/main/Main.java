package main;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import view.MainFrame;
import net.webservicex.*;

public class Main {
	public static void main(String[] args){
		Country factory = new Country();
		CountrySoap proxy = factory.getCountrySoap();
		
		
		String thisd = proxy.getCurrencies();
		
		String THISD = proxy.getISD("Thailand");
		
		String country = proxy.getCountryByCountryCode("TH");
		System.out.println(THISD);
		
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
