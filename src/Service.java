import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class Service {
	private String currentCountry;
	private String shortCur;

	Service(String country) {
		setCurrentCountry(country);

		shortCur = convertCountryNameToIsoCode(country);
	}

	String getWeather(String city) {
		String answer = "";

		try {
			 answer =
			 getJson("http://api.openweathermap.org/data/2.5/weather?q=" +
			 city +"&appid=5440d302539a6a8021c62ed9d280f661");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	Double getRateFor(String kod_waluty) {
		Double answer = 1.0;
		String temp = "";
		kod_waluty = kod_waluty.toUpperCase();
		
		if (!kod_waluty.equals(shortCur)) {
			String json = "";

			try {

				json = getJson("http://api.fixer.io/latest?base=" + shortCur + "&symbols=" + kod_waluty);
				temp = parseJson(json, kod_waluty);

				try {
					answer = Double.parseDouble(temp);
				} catch (Exception e) {
					System.out.println("Wrong currency type");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			answer = 1.0;
		}
		return answer;
	}

	Double getNBPRate() {
		double rate = 1.0;
		
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(getJson("http://www.nbp.pl/kursy/xml/a058z170323.xml")));
			Document xml = documentBuilder.parse(is);
			
			String result = findInXML(xml);
			
			if(result == "")
			{
				is = new InputSource(new StringReader(getJson("http://www.nbp.pl/kursy/xml/b012z170322.xml")));
				xml = documentBuilder.parse(is);
				
				result = findInXML(xml);
			}
			
			result = result.replaceAll(",", ".");
			
			if(!result.isEmpty())
				rate = Double.parseDouble(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rate;
	}
	
	String findInXML(Document xml)
	{
		try {
			XPathFactory pathFactory = XPathFactory.newInstance();
	        XPath xpath = pathFactory.newXPath();
	        XPathExpression expr = xpath.compile("//tabela_kursow/pozycja[kod_waluty='" + shortCur + "']/kurs_sredni");
	        
	        Node node = (Node) expr.evaluate(xml, XPathConstants.NODE);
	       
	        if(node != null)
	        {
	        	return node.getTextContent();
	        }
	        else return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	

	private String parseJson(String json, String target) {
		String temp = json;
		String answer = "";

		Pattern pattern = Pattern.compile("\"(" + target + ")\":+(\\d.*\\d)"); // ""
		Matcher matcher = pattern.matcher(temp);

		if (matcher.find()) {
			if (matcher.group(1) != target) {
				answer = matcher.group(2);
			}
		}

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
		} catch (Exception e) {
			//Can't find country
			return "";
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
		shortCur = convertCountryNameToIsoCode(currentCountry);
	}
	
	public String convertCountryNameToIsoCode(String countryName)
	{
	    for(Locale l : Locale.getAvailableLocales()) {
	        if (l.getDisplayCountry().equals(countryName)) {
	    		Currency currency = Currency.getInstance(l);
	    		return currency.getCurrencyCode();
	        }
	    }
	    return null;
	}
}