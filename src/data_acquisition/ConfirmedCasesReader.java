package data_acquisition;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * This is a constructor for the class Confirmed case Reader
 */
public class ConfirmedCasesReader {
	
	private String country;
	
	/**
	 * This is a constructor 
	 * @param country The name of the country to be analyzed
	 */
	public ConfirmedCasesReader(String country) {
		this.country = country;
	}
	
	/**
	 * This is a method to retrieve data from a web source
	 * @return Json a json for all the confirmed cases
	 */
	public double retrieveData() {
		String urlString = String.format("https://api.covid19api.com/total/dayone/country/%s/status/confirmed", country);
		double cases = 0.0;
		try {
			// GET 
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				// JSON stuff
				JsonArray jsonArray = JsonParser.parseString(inline).getAsJsonArray();
				int size = jsonArray.size();
				cases = jsonArray.get(size - 1).getAsJsonObject().get("Cases").getAsInt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cases;
	}
}
