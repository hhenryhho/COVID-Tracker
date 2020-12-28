package controller;

import calculator.Calculator;
import country_service.CountryList;
import observer.ImageRenderer;
import observer.Notifier;
import observer.OutputRenderer;

/**
 * This is a class for the system facade. Linking together all elements of the program.
 */
public class SystemFacade {
	
	private CountryList countryList;
	private String analysisMethod;
	private Notifier modelUpdater;
	private ImageRenderer imageRenderer;
	private OutputRenderer outputRenderer;

	/**
	 * This is the constructor class for the system facade
	 */
	public SystemFacade() {
		countryList = new CountryList();
		analysisMethod = "";
		modelUpdater = new Notifier();
		imageRenderer = ImageRenderer.getInstance();
		outputRenderer = OutputRenderer.getInstance();
		modelUpdater.attach(imageRenderer);
		modelUpdater.attach(outputRenderer);
	}

	/**
	 * this method is to add a country into the list of countries to be analyized
	 * @param countryName the name of the country to be added
	 */
	public void addCountry(String countryName) {
		countryList.addCountry(countryName);
	}
	
	/**
	 * this method is to remove a country from the current list of countries to be analyzied
	 * @param countryName the name of the country to be removed
	 */
	public void removeCountry(String countryName) {
		countryList.removeCountry(countryName);
	}
	
	/**
	 * get current list of countries currently in the list of countries to be analyzied
	 * @return currentCountries
	 */
	public String getCountryList() {
		return countryList.getCurrentCountries();
	}
	
	/**
	 * get current names of countries and their associated values from the list
	 * @return countryListandValues
	 */
	public String getCountryListAndValues() {
		return countryList.getCurrentCountriesAndValues();
	}
	
	/**
	 * set analysis method for the countries currently in the countries list 
	 * @param analysisMethod
	 */
	public void setAnalysisMethod(String analysisMethod) {
		this.analysisMethod = analysisMethod;
	}
	
	/**
	 * perform a complete analysis of all countries
	 */
	public void performCompleteAnalysis() {
		for (int i = 0; i < countryList.size(); i++) {
			Calculator calculator = new Calculator(countryList.getCountryByIndex(i).getCountryName(), analysisMethod);
			countryList.getCountryByIndex(i).setAnalysisValue(calculator.performAnalysis());
		}
		modelUpdater.notifyObservers(countryList);
	}
}
