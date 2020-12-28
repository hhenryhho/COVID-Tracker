package calculator;

import data_acquisition.StatisticObject;

/**
 * This represents a class for Cases
 */
public class Cases implements AnalysisMethod {
	
	private StatisticObject statistics;

	/**
	 * This is a constructor for Cases
	 */
	public Cases(String country) {
		statistics = new StatisticObject(country);
	}

	/**
	 * This class represents an over ride for the value in analyze Data 
	 */
	@Override
	public double analyzeData() {
		statistics.getData();
		return statistics.getConfirmedCases();
	}
}
