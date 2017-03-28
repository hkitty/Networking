import java.awt.EventQueue;
import java.util.Currency;
import java.util.Locale;

public class Main { 
	public static void main(String[] args) {
		String country = "Italy";
		String city = "Rome";
		String rateFor = "USD";
		
		Service s = new Service(country); 
		
		String weatherJson = s.getWeather(city);
		Double rate1 = s.getRateFor(rateFor); 
		Double rate2 = s.getNBPRate(); 
		
		System.out.println(weatherJson);
		System.out.println("Rate for " + rateFor + ": " + rate1);
		System.out.println("NBP rate: " + rate2);
		
//		MainForm form = new MainForm(country, city, rateFor);
		
	}
}
