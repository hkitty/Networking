import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;
	private String country;
	private Service service;

	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;

	private JComboBox<Object> countryComboBox;
	private JComboBox<Object> currencyComboBox;

	private WebView browser;

	String rateFor;
	String city;

	JFXPanel wikiPanel;

	private JTextField weatherField;

	public void run() {
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainForm(String country, String city, String rateFor) {
		initialize();

		this.country = country;
		service = new Service(country);

		this.city = city;
		this.rateFor = rateFor;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				weatherField.setText(service.getWeather(city));
				textField1.setText(service.getRateFor(rateFor).toString());
				textField2.setText(service.getNBPRate().toString());

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
		panel.setBounds(0, 0, 540, 116);
		frame.getContentPane().add(panel);

		JPanel weatherPanel = new JPanel();
		weatherPanel.setBounds(10, 3, 520, 25);
		weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		weatherPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel lblGetWeatherFor = new JLabel("Get weather for: ");
		panel_1.add(lblGetWeatherFor);
		lblGetWeatherFor.setFont(new Font("Tahoma", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel ratePanel = new JPanel();
		ratePanel.setBounds(10, 59, 520, 25);

		JPanel nbpRate = new JPanel();
		nbpRate.setBounds(10, 87, 520, 25);
		nbpRate.setLayout(new BoxLayout(nbpRate, BoxLayout.X_AXIS));

		JLabel lblGetNbpRate = new JLabel("Get NBP rate: ");
		nbpRate.add(lblGetNbpRate);
		lblGetNbpRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.setLayout(null);
		panel.add(weatherPanel);

		JPanel panel_2 = new JPanel();
		weatherPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton("Get weather");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeCity();
			}
		});
		panel_2.add(btnNewButton);

		JPanel jsonPanel = new JPanel();
		jsonPanel.setBounds(10, 31, 520, 25);
		panel.add(jsonPanel);
		jsonPanel.setLayout(new BoxLayout(jsonPanel, BoxLayout.X_AXIS));

		weatherField = new JTextField();
		jsonPanel.add(weatherField);
		weatherField.setColumns(10);
		panel.add(ratePanel);
		ratePanel.setLayout(new BoxLayout(ratePanel, BoxLayout.X_AXIS));

		JLabel lblGetRateFor = new JLabel("Get rate for: ");
		ratePanel.add(lblGetRateFor);
		lblGetRateFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(nbpRate);

		textField1 = new JTextField();
		ratePanel.add(textField1);
		textField1.setColumns(10);

		String countries[] = { "Australia", "Brazil", "Bulgaria", "Canada", "Switzerland", "China", "Czech", "Denmark",
				"United Kingdom", "Hong Kong", "Croatia", "Hungary", "Indonesia", "Israel", "India", "Japan",
				"South Korea", "Mexico", "Malaysia", "Norway", "New Zeland", "Poland", "Romania", "Russia", "Sweden",
				"Thailand", "Turkey", "United States", "South Africa" };

		String currencies[] = { "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF",
				"IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PLN", "RON", "RUB", "SEK", "THB", "TRY",
				"USD", "ZAR" };

		currencyComboBox = new JComboBox<Object>(currencies);

		ratePanel.add(currencyComboBox);
		currencyComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					changeCurrency();
			}
		});

		textField2 = new JTextField();
		nbpRate.add(textField2);
		textField2.setColumns(10);

		countryComboBox = new JComboBox<Object>(countries);
		nbpRate.add(countryComboBox);

		countryComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					changeCountry();
			}
		});

		wikiPanel = new JFXPanel();
		wikiPanel.setBounds(0, 112, 540, 208);
		frame.getContentPane().add(wikiPanel);
	}

	private void changeCountry() {
		country = countryComboBox.getSelectedItem().toString();

		service.setCurrentCountry(country);
		textField1.setText(service.getRateFor(rateFor).toString());
		textField2.setText(service.getNBPRate().toString());

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				weatherField.setText(service.getWeather(city));
				textField1.setText(service.getRateFor(rateFor).toString());
				textField2.setText(service.getNBPRate().toString());

				browser = new WebView();
				wikiPanel.setScene(new Scene(browser));
				browser.getEngine().load("https://en.wikipedia.org/wiki/" + country);
			}
		});

		run();
	}

	private void changeCurrency() {
		rateFor = currencyComboBox.getSelectedItem().toString();

		textField1.setText(service.getRateFor(rateFor).toString());
		textField2.setText(service.getNBPRate().toString());
	}

	private void changeCity() {
		weatherField.setText(service.getWeather(textField.getText()));
	}
}
