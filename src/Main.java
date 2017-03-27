import java.awt.EventQueue;

public class Main { 
	public static void main(String[] args) {
		String country = "Canada";
		String city = "Warsaw";
		String rateFor = "USD";
		
		Service s = new Service(country); 
		
		String weatherJson = s.getWeather(city);
		Double rate1 = s.getRateFor(rateFor); 
		Double rate2 = s.getNBPRate(); 
		
		MainForm form = new MainForm(country, city, rateFor);
	}
}
