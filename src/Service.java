import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Service {
	private String currentCountry;
	private String shortCur;// = "PLN";

	private Map<String, String> cur = new HashMap<String, String>();

	Service(String country) {
		cur.put("Canada", "CAD");
		cur.put("Switzerland", "CHF");
		cur.put("Cyprus", "CYP");
		cur.put("Danish", "DKK");
		cur.put("Estonia", "EEK");
		cur.put("United Kingdom", "GBP");
		cur.put("Hong Kong", "HKD");
		cur.put("Hungary", "HUF");
		cur.put("Iceland", "ISK");
		cur.put("Japan", "JPY");
		cur.put("South Korea", "KRW");
		cur.put("Lithuania", "LTL");
		cur.put("Latvia", "LVL");
		cur.put("Malta", "MTL");
		cur.put("Norway", "NOK");
		cur.put("New Zeland", "NZD");
		cur.put("Poland", "PLN");
		cur.put("Romania", "ROL");
		cur.put("Sweden", "SEK");
		cur.put("Singapore", "SGD");
		cur.put("Slovenia", "SIT");
		cur.put("Slovakia", "SKK");
		cur.put("Turkey", "TRL");
		cur.put("United States", "USD");
		cur.put("South Africa", "ZAR");

		setCurrentCountry(country);

		shortCur = cur.get(currentCountry);

	}

	String getWeather(String city) {
		String answer = "";

		try {
			// answer =
			// getJson("http://api.openweathermap.org/data/2.5/weather?q=" +
			// city +"&appid=5440d302539a6a8021c62ed9d280f661");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(answer);

		return answer;
	}

	Double getRateFor(String kod_waluty) {
		Double answer = 1.0;
		if (shortCur != kod_waluty) {
			String json = "";

			try {

				json = getJson("http://api.fixer.io/latest?base=" + shortCur + "&symbols=" + kod_waluty);
				answer = parseJson(json);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(answer);

		return answer;
	}

	Double getNBPRate() {
		Double answer = 0.0;

		return answer;
	}

	private static Double parseJson(String json) {
		Double answer = 0.0;

		String[] ss = json.split(":");
		String temp = ss[ss.length - 1];

		temp = temp.substring(0, temp.length() - 2);

		answer = Double.parseDouble(temp);

		return answer;

	}

	private static String getJson(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}
}