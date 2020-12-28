package data_acquisition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * This is to represent a class for the confirmed cases for each sex
 */
public class ConfirmedCasesPerSexReader {

	private String country;
	private File PATH = new File("./src/assets/countries.json");

	/**
	 * Constructor for the class
	 * @param country name of country to be added
	 */
	public ConfirmedCasesPerSexReader(String country) {
		this.country = country;
	}
	
	/**
	 * method to get data from online source
	 * @return json A json file with all the relavant information
	 */
	public double[] retrieveData() {
		String urlString = String.format("https://api.globalhealth5050.org/api/v1/agesex/?code=%s", getCountryCode(country));
		double[] cases = new double[2];
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
				JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();
				cases = getTotalCases(jsonObject);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cases;
	}
	
	/*
	 *  To obtain the data for a single country, you need to pass a 2-letter country code into the GET requests.
	 *  This is difficult because our program uses the country's full name. So it is necessary to get the 2-letter
	 *  value of the country. This is done by scanning a local file that contains each country and
	 *  its 2-letter country code.
	 */

	private String getCountryCode(String country) {
		String countryCode = "";
		try {
			Scanner reader = new Scanner(PATH);
			String inline = "";
			while (reader.hasNextLine()) {
				inline += reader.nextLine();
			}
			reader.close();
			JsonArray jsonArray = JsonParser.parseString(inline).getAsJsonArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				if (jsonArray.get(i).getAsJsonObject().get("Name").getAsString().equals(country)) {
					return jsonArray.get(i).getAsJsonObject().get("Code").getAsString();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return countryCode;
	}

	/*
	 * The API response is difficult to process because it contains so much data. Therefore this function will
	 * take the response from a single country and process it to find its male and female cases. 
	 */
	private double[] getTotalCases(JsonObject cases) {
		double maleCases = 0.0;
		double femaleCases = 0.0;
		
		Set<String> wanted  = cases.get("data").getAsJsonObject().keySet();
		String countryName = wanted.iterator().next();
		
		if (cases.get("data").getAsJsonObject().get(countryName).getAsJsonObject().keySet().contains("CasebyAgeSex")){
			try{
				JsonArray jsonArray = cases.get("data").getAsJsonObject().get(countryName).getAsJsonObject().get("CasebyAgeSex").getAsJsonArray();
				for (int i = 0; i < jsonArray.size(); i++) {
					maleCases += jsonArray.get(i).getAsJsonObject().get("casesM").getAsDouble();
					femaleCases += jsonArray.get(i).getAsJsonObject().get("casesF").getAsDouble();
				}
			}catch(NullPointerException e){
	
			}
		}
		
		return new double[] {maleCases, femaleCases};
	}
}
