package data_acquisition;

/**
 * This is a class for adapting the data for ingestion
 */
public class DataAdapter {

	private ConfirmedCasesReader confirmedCasesReader;
	private ConfirmedCasesPerSexReader confirmedCasesPerSexReader;
	private PopulationByCountryReader popByCountryReader;
	
	private double confirmedCases;
	private double[] confirmedCasesPerSex;
	private double population;
	
	/**
	 * This represents a constructor for the class
	 * @param country the name of the country
	 */
	public DataAdapter(String country) {
		confirmedCasesReader = new ConfirmedCasesReader(country);
		confirmedCasesPerSexReader = new ConfirmedCasesPerSexReader(country);
		popByCountryReader = new PopulationByCountryReader(country);
	}
	
	/**
	 * This method is for collection data from the various readers in the program
	 */
	public void collectData() {
		confirmedCases = confirmedCasesReader.retrieveData();
		confirmedCasesPerSex = confirmedCasesPerSexReader.retrieveData();
		population = popByCountryReader.retrieveData();
	}
	
	/**
	 * get the number of confirmed covid cases in the object
	 * @return confirmed cases
	 */
	public double getConfirmedCases() {
		return confirmedCases;
	}
	
	/**
	 * get the number of confirmed cases per sex
	 * @return the confirmed cases per sex
	 */
	public double[] getConfirmedCasesPerSex() {
		return confirmedCasesPerSex;
	}
	
	/**
	 * get the population of a country
	 * @return population
	 */
	public double getPopulation() {
		return population;
	}
}
