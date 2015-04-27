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
		
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		
		
	}
	
}
