
public class Main { 
	public static void main(String[] args) {
		String country = "Australia";
		String city = "Cidney";
		String rateFor = "AUD";
		
		Service s = new Service(country); 
		
		String weatherJson = s.getWeather(city);
		Double rate1 = s.getRateFor(rateFor); 
		Double rate2 = s.getNBPRate(); 
		
		System.out.println(weatherJson);
		System.out.println("Rate for " + rateFor + ": " + rate1);
		System.out.println("NBP rate: " + rate2);
		
		new MainForm(country, city, rateFor);
		
	}
}
