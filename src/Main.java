
public class Main { 
	public static void main(String[] args) { 
		Service s = new Service("Canada"); 
		String weatherJson = s.getWeather("Warsaw"); 
		Double rate1 = s.getRateFor("USD"); 
		Double rate2 = s.getNBPRate(); 
		// ... 
		// а здесь GUI 
	} 
}
