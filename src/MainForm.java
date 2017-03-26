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
import java.awt.FlowLayout;

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
		
		run();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 540, 80);
		frame.getContentPane().add(panel);
		
		JPanel weatherPanel = new JPanel();
		weatherPanel.setBounds(10, 0, 459, 25);
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
		ratePanel.setBounds(10, 25, 116, 25);
		ratePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel nbpRate = new JPanel();
		nbpRate.setBounds(10, 49, 128, 25);
		nbpRate.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblGetNbpRate = new JLabel("Get NBP rate: ");
		nbpRate.add(lblGetNbpRate);
		lblGetNbpRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.setLayout(null);
		panel.add(weatherPanel);
		panel.add(ratePanel);
		
		JLabel lblGetRateFor = new JLabel("Get rate for: ");
		ratePanel.add(lblGetRateFor);
		lblGetRateFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(nbpRate);
		
		wikiPanel = new JFXPanel();
		wikiPanel.setBounds(0, 80, 540, 240);
		frame.getContentPane().add(wikiPanel);
	}

}
