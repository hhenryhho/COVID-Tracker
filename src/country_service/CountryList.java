package country_service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This represents a class for the CountryList
 */
public class CountryList {
	
	private ArrayList<Country> countryList;
	private String[] listOfCountries;

	/**
	 * Constructor for the class country List
	 */
	public CountryList() {
		countryList = new ArrayList<Country>();
	}
	
	/**
	 * Adds a country to the country list
	 * @param countryName the name of the country to be added
	 */
	public void addCountry(String countryName) {
		if (checkCountrySpelling(countryName) == false) {
			return;
		}
		int addIndex = findIndexOfCountry(countryName);
		if (addIndex == -1) {
			countryList.add(new Country(countryName));
		}
	}
	
	/**
	 * Removes a country from the country list
	 * @param countryName the name of the country to be removed
	 */
	public void removeCountry(String countryName) {
		if (checkCountrySpelling(countryName) == false) {
			return;
		}
		int removeIndex = findIndexOfCountry(countryName);
		if (removeIndex != -1) {
			countryList.remove(removeIndex); 
		}
	}
	
	/**
	 *  finds the index of the country wihtin the country list 
	 * @param countryName the name of the country to search by
	 * @return index the index value of the country within the list
	 */
	private int findIndexOfCountry(String countryName) {
		for (int i = 0; i < countryList.size(); i++) {
			if (countryList.get(i).getCountryName().equals(countryName)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * a spell checker for the class Country List
	 * @param countryName
	 * @return state a Boolean value for whether the country is spelled correctly
	 */
	private boolean checkCountrySpelling(String countryName) {
		try {
			Scanner reader = new Scanner(new File("./src/assets/coordinates.csv"));  
			while (reader.hasNextLine())  
			{  
				String currentLine = reader.nextLine();
				String[] splitter = currentLine.split(",");
				if (splitter[3].equals(countryName)) {
					return true;
				} 
			}
			reader.close(); 
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @return the size of the country List
	 */
	public int size() {
		return countryList.size();
	}
	
	/**
	 * Gets a country object from a specified name
	 * @param countryName the name of the country to search by
	 * @return wanted the object the user desires
	 */
	public Country getCountryByName(String countryName) {
		return countryList.get(findIndexOfCountry(countryName));
	}
	
	/**
	 * gets the country by its index value in the list
	 */
	public Country getCountryByIndex(int index) {
		return countryList.get(index);
	}
	
	/**
	 * returns all countries within the current object
	 * @return listOfCountries a list of countries within the country list
	 */
	public String getCurrentCountries() {
		String currentCountryList = "";
		for (int i = 0; i < countryList.size(); i++) {
			currentCountryList = currentCountryList + countryList.get(i).getCountryName() + "\n";
		}
		return currentCountryList;
	}
	
	/**
	 * gets all current countries and their values in the current countries list
	 * @return listOfCountriesAndValues
	 */
	public String getCurrentCountriesAndValues() {
		String currentCountryList = "";
		for (int i = 0; i < countryList.size(); i++) {
			currentCountryList = currentCountryList + countryList.get(i).getCountryName() + "==>:" + countryList.get(i).getAnalysisValue() + "\n";
		}
		return currentCountryList;
	}
}
