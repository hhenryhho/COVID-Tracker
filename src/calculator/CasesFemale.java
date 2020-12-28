package calculator;

import data_acquisition.StatisticObject;

public class CasesFemale implements AnalysisMethod {

	private StatisticObject statistics;

	/**
	 * This is to represent an analysis method for Covid data representing cases of females
	 * @param country
	 */
	public CasesFemale(String country) {
		statistics = new StatisticObject(country);
	}

	/**
	 * This class represents an over ride for the value in analyze Data
	 */
	@Override
	public double analyzeData() {
		statistics.getData();
		return statistics.getFemaleConfirmedCases();
	}
}