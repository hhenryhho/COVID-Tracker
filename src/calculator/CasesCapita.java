package calculator;

import data_acquisition.StatisticObject;

/**
 * This is a class to represent a way of analyizing Covid data by Cases per Capita
 */
public class CasesCapita implements AnalysisMethod{

	private StatisticObject statistics;

	/**
	 * This represents a constructor for cases per capita
	 * @param country
	 */
	public CasesCapita(String country) {
		statistics = new StatisticObject(country);
	}

	/**
	 * This class represents an over ride for the value in analyze Data 
	 */
	@Override
	public double analyzeData() {
		statistics.getData();
		return statistics.getConfirmedCases()/statistics.getPopulation();
	}
}
