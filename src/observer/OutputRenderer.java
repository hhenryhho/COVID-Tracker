package observer;

import country_service.CountryList;

/*
 * This class exists as a singleton. This is to ensure that there is only one instance of the output box that
 * that is shared across the program.
 */
public class OutputRenderer implements CalculatorListener{
	
	private static OutputRenderer INSTANCE;
	private String text;
	
	private OutputRenderer() {
		text = "";
	}

	@Override
	public void update(CountryList countryList) {
		text = countryList.getCurrentCountriesAndValues();
	}
	
	public String render() {
		return text;
	}
	
	public static OutputRenderer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OutputRenderer();
		}
		
		return INSTANCE;
	}

}
