package calculator;

/**
 * Represents a calculator class for processing the analysis method of Covid data.
 */
public class Calculator {

	private String country;
	private AnalysisMethod method;
	
	/**
	 * Calculator constructor
	 * @param country This is the name of the country to be analyzed.
	 * @param analysisMethod This is the name of the analysis method to be used.
	 */
	public Calculator(String country, String analysisMethod) {
		if (analysisMethod.equals("Confirmed Cases")) {
			method = new Cases(country);
		} else if (analysisMethod.equals("Confirmed Cases Per Capita")) {
			method = new CasesCapita(country);
		} else if (analysisMethod.equals("Confirmed Male Cases")) {
			method = new CasesMale(country);
		} else if (analysisMethod.equals("Confirmed Female Cases")) {
			method = new CasesFemale(country);
		}
	}
	
	/**
	 * Performs the calculation
	 * @return the Analyzed data
	 */
	public double performAnalysis() {
		return method.analyzeData();
	}
}
