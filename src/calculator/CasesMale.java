package calculator;

import data_acquisition.StatisticObject;

public class CasesMale implements AnalysisMethod{

	private StatisticObject statistics;

	/**
	 * This is to represent an anaylsis method for Covid data for male cases
	 * @param country
	 */
	public CasesMale(String country) {
		statistics = new StatisticObject(country);
	}

	/**
	 * This class represents an over ride for the value in analyze Data
	 */
	@Override
	public double analyzeData() {
		statistics.getData();
		return statistics.getMaleConfirmedCases();
	}
}