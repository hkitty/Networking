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
		
		//System.out.println("Weather in " + city + ": " + weatherJson);
		//System.out.println("Rate in " + country + " for " + rateFor + " : " + rate1);
		//System.out.println("Rate in " + country + " for zloty: " + rate2);
		
		MainForm form = new MainForm(country, city, rateFor);
	}
}
