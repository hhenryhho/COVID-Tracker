package data_acquisition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * This is for the class Population by country reader
 */
public class PopulationByCountryReader {
	
	private File PATH = new File("./src/assets/country-by-population.json");
	private String country;
	
	/**
	 * This is a constructor for the class
	 * @param country the name of the country to be analyzied
	 */
	public PopulationByCountryReader(String country) {
		this.country = country;
	}

	/**
	 * This is the method to retrieveData from Json Readers
	 */
	public double retrieveData() {
		double population = 0.0;
		try {
			Scanner reader = new Scanner(PATH);
			String inline = "";
			while (reader.hasNextLine()) {
				inline += reader.nextLine();
			}
			reader.close();
			JsonArray jsonArray = JsonParser.parseString(inline).getAsJsonArray();
			population = jsonArray.get(findIndexOfCountry(jsonArray, country)).getAsJsonObject().get("population").getAsDouble();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return population;
	}
	
	/**
	 * Method to find index of country within json structure
	 * @param jsonArray
	 * @param country
	 * @return
	 */
	private int findIndexOfCountry(JsonArray jsonArray, String country) {
		for (int i = 0; i < jsonArray.size(); i++) {
			if (jsonArray.get(i).getAsJsonObject().get("country").getAsString().equals(country)) {
				return i;
			}
		}
		return -1;
	}

}
