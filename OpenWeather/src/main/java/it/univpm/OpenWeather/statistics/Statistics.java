package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONObject;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.WeatherData;

/**
 * @author Leonardo Pieralisi
 *
 */

public class Statistics {

	protected double avgTempMin;
	protected double avgTempMax;
	protected double avgTemp;
	protected double avgFeelsLike;

	protected City city;

	public Statistics(City city) {
		this.city=city;
	}
	public void calculateStatistics() {

		for(WeatherData singleWeatherData : this.city.getForecast()) {
			this.avgTemp += singleWeatherData.getTemp();
			this.avgTempMin += singleWeatherData.getTempMin();
			this.avgTempMax += singleWeatherData.getTempMax();
			this.avgFeelsLike += singleWeatherData.getFeelsLike();
		}

		try {
			this.avgTempMin = this.avgTempMin/this.city.getForecast().size();
			this.avgTempMax = this.avgTempMax/this.city.getForecast().size();
			this.avgTemp = this.avgTemp/this.city.getForecast().size();
			this.avgFeelsLike = this.avgFeelsLike/this.city.getForecast().size();
		}
		catch (ArithmeticException e){
			System.out.println("Errore divisione per zero! passato parametro invalido");
		}
	}

	public JSONObject getJSONStatistics() {

		calculateStatistics();

		JSONObject statsJSON = new JSONObject();
		JSONObject output = new JSONObject();

		statsJSON.put("avg_Temp", this.avgTemp);
		statsJSON.put("avg_TempMin", this.avgTempMin);
		statsJSON.put("avg_TempMax", this.avgTempMax);
		statsJSON.put("avg_FellsLike", this.avgFeelsLike);

		output.put("stats", statsJSON);

		output.put("city", this.city.getName());
		output.put("id", this.city.getID());
		output.put("country", this.city.getCountry());

		return output;
	}

	public String toString() {

		String output =
				"Average Temperature: "+ this.avgTemp +"\n"
						+ "Average Min Temperature: "+ this.avgTempMin +"\n"
						+ "Average Max Temperature: "+ this.avgTempMax +"\n"
						+"Average Feels Like: "+ this.avgFeelsLike +"\n" ;

		return output;
	}

	public boolean equals() {
		//TODO: fare l'equals!
		return true;
	}
	public double getAvgTempMin() {
		return avgTempMin;
	}
	public void setAvgTempMin(double avgTempMin) {
		this.avgTempMin = avgTempMin;
	}
	public double getAvgTempMax() {
		return avgTempMax;
	}
	public void setAvgTempMax(double avgTempMax) {
		this.avgTempMax = avgTempMax;
	}
	public double getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}
	public double getAvgFeelsLike() {
		return avgFeelsLike;
	}
	public void setAvgFeelsLike(double avgFeelsLike) {
		this.avgFeelsLike = avgFeelsLike;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}

}