package observer;

import java.util.ArrayList;

import country_service.CountryList;

public class Notifier {
	
	private ArrayList<CalculatorListener> observers = new ArrayList<CalculatorListener>();
	
	
	public void attach(CalculatorListener observer) {
		observers.add(observer);
	}
	
	public void detach(CalculatorListener observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(CountryList countryList) {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update(countryList);
		}
	}

}
