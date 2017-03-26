import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextPane;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sun.swing.plaf.WindowsKeybindings;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainForm {

	private JFrame frame;
	private String country;
	private Service service;
	private JTextField textField;
	private WebView browser;
	
	JFXPanel wikiPanel;
	
	public void run() {
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public MainForm(String country) {
		initialize();
		
		this.country = country;
		service = new Service(country);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    	    browser = new WebView();
	    	    wikiPanel.setScene(new Scene(browser));
	    	    browser.getEngine().load("https://en.wikipedia.org/wiki/" + country);
	        }
	   });
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel weatherPanel = new JPanel();
		frame.getContentPane().add(weatherPanel);
		weatherPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblGetWeatherFor = new JLabel("Get weather for: ");
		lblGetWeatherFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		weatherPanel.add(lblGetWeatherFor);
		
		textField = new JTextField();
		weatherPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnGet = new JButton("Get!");
		weatherPanel.add(btnGet);
		
		JPanel ratePanel = new JPanel();
		frame.getContentPane().add(ratePanel);
		ratePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblGetRateFor = new JLabel("Get rate for: ");
		lblGetRateFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ratePanel.add(lblGetRateFor);
		
		JPanel nbpRate = new JPanel();
		frame.getContentPane().add(nbpRate);
		nbpRate.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblGetNbpRate = new JLabel("Get NBP rate: ");
		lblGetNbpRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nbpRate.add(lblGetNbpRate);
		
		wikiPanel = new JFXPanel();
		frame.getContentPane().add(wikiPanel);
	}

}
