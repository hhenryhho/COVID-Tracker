package data_acquisition;

public class StatisticObject {
	
	private double confirmedCases;
	private double population;
	private double maleConfirmedCases;
	private double femaleConfirmedCases;
	
	private DataAdapter adapter;
	
	public StatisticObject(String country) {
		confirmedCases = 0;
		population = 0;
		maleConfirmedCases = 0;
		femaleConfirmedCases = 0;
	
		adapter = new DataAdapter(country); 
	}

	public void getData() {
		adapter.collectData();
		confirmedCases = adapter.getConfirmedCases();
		population = adapter.getPopulation();
		maleConfirmedCases = adapter.getConfirmedCasesPerSex()[0];
		femaleConfirmedCases = adapter.getConfirmedCasesPerSex()[1];
	}
	
	public double getConfirmedCases() {
		return confirmedCases;
	}
	public double getPopulation() {
		return population;
	}
	public double getMaleConfirmedCases() {
		return maleConfirmedCases;
	}
	public double getFemaleConfirmedCases() {
		return femaleConfirmedCases;
	}
}
