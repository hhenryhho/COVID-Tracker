package country_service;

/**
 * This is to represent a class country that holds information relavant
 */
public class Country {
	
	private String countryName;
	private double analysisValue;
	
	/**
	 * Constructor for class country
	 */
	public Country() {
		this.countryName = "";
		this.analysisValue = 0;
	}

	/**
	 * Constructor for class country with parameter
	 * @param countryName Name of country to create object with
	 */
	public Country(String countryName) {
		this.countryName = countryName;
		this.analysisValue = 0;
	}
	
	/**
	 * Constructor for the class with multiple parameters
	 * @param countryName Name of the country 
	 * @param analysisValue
	 */
	public Country(String countryName, double analysisValue) {
		this.countryName = countryName;
		this.analysisValue = analysisValue;
	}
	
	/**
	 * Modify the country object name
	 * @param countryName the name of the country to be set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * Set the type of analysis to be conducted
	 * @param analysisValue the value of analysis to be performed
	 */
	public void setAnalysisValue(double analysisValue) {
		this.analysisValue = analysisValue;
	}
	
	/**
	 * gets the country name of the object
	 * @return countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * gets the analysis value of the object
	 * @return analysisValue
	 */
	public double getAnalysisValue() {
		return analysisValue;
	}
}
