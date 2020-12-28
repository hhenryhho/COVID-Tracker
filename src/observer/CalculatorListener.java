package observer;

import country_service.CountryList;

public interface CalculatorListener {
	public void update(CountryList countryList);
}
