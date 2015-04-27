package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker.StateValue;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.xml.bind.JAXBException;

import controller.FrequencyUnitConverterController;
import model.Service;
import net.webservicex.CountrySoap;
import net.webservicex.FrequencyUnitSoap;
import net.webservicex.Frequencys;

/**
 * Main user interface.
 * Need to be {@link MainFrame#setSoap(FrequencyUnitSoap)} to be able to {@link MainFrame#convert()}.
 * 
 * @author Poramate Homprakob 5510546077
 *
 */

public class MainFrame extends JFrame {
	
	/** User interface components */
	private JPanel mainPanel;
	private JTextField countryNameTextField;
	private JTextField countryCodeTextField;
	private JTextField currencyTextField;
	private JTextField ISDTextField;
	private JTextField GMTTextField;
	private JButton getButton;

	private JPanel statusPanel;
	private JLabel statusLabel;
	
	/** Soap, forwarded to controller every time it is initialized */
	private CountrySoap proxy;
	
	/** Will be initialized every time convert() invoked to be able to convert many time. */
	private Service controller;
	
	/**
	 * Plain constructor, require nothing.
	 */
	public MainFrame() {
		super("Contry Detail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(450, 180));
		setResizable(false);
		
		initComponents();
	}

	/**
	 * Initialize components.
	 */
	private void initComponents() {
		setLayout(new FlowLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		/* CountryName */
		countryNameTextField = new JTextField(27);
		countryNameTextField.setToolTipText("Ex. Thailand");
		countryNameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					get();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		mainPanel.add(countryNameTextField);
		
		/* CountryCode */
		countryCodeTextField = new JTextField(27);
		countryCodeTextField.setToolTipText("Ex. TH");
		countryCodeTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					get();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		mainPanel.add(countryCodeTextField);
		
		/* Currency */
		currencyTextField = new JTextField(27);
		currencyTextField.setToolTipText("Ex. THB");
		currencyTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					get();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		mainPanel.add(currencyTextField);
		
		/* ISD */
		ISDTextField = new JTextField(27);
		ISDTextField.setToolTipText("Ex. 66");
		ISDTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					get();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		mainPanel.add(ISDTextField);
		
		/* GMT */
		GMTTextField = new JTextField(27);
		GMTTextField.setToolTipText("Ex. +7");
		GMTTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					get();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		mainPanel.add(GMTTextField);
		
		/* GET */
		getButton = new JButton("GET");
		getButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				get();
			}
			
			@Override
			public void mousePressed(MouseEvent e) { }
			
			@Override
			public void mouseExited(MouseEvent e) { }
			
			@Override
			public void mouseEntered(MouseEvent e) { }
			
			@Override
			public void mouseClicked(MouseEvent e) { }
		});
		mainPanel.add(getButton);
		
		
		
		/* Status Panel */
		statusPanel = new JPanel(new FlowLayout());
		statusLabel = new JLabel("Preparing...");
		statusLabel.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 25));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(statusLabel);
		mainPanel.add(statusPanel);
		
		this.add(mainPanel);
	}
	
	public void run() {
		pack();
		setVisible(true);
	}
	
	/**
	 * Set Soap for calling for services.
	 * If not, cannot call convert().
	 * @param proxy the Soap for frequency unit converter
	 */
	public void setSoap(CountrySoap proxy) {
		this.proxy = proxy;
		controller = new Service(proxy, this);
	}
	
	public void setResult(String countryName, String countryCode, String currency, String ISD, String GMT) {
		countryNameTextField.setText(countryName);
		countryCodeTextField.setText(countryCode);
		currencyTextField.setText(currency);
		ISDTextField.setText(ISD);
	}
	
	public void setStatus(String status) {
		statusLabel.setText(status);
	}

	private void get() {
		if (proxy == null)
			return;
		
		double value = Double.parseDouble(valueTextField.getText().toString());
		Frequencys from = (Frequencys) fromCombobox.getSelectedItem();
		Frequencys to = (Frequencys) toCombobox.getSelectedItem();
		
		if (controller == null || controller.getState() == StateValue.STARTED || controller.getState() == StateValue.DONE)
			controller = new FrequencyUnitConverterController(proxy, this);
		
//		controller.setParameters(value, from, to);
//		controller.execute();
		controller.get(countryNameTextField.getText(), countryCodeTextField.getText(), currencyTextField.getText(), ISDTextField.getText());
	}
}
