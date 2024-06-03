
package com.example.PublicApi.Model;

import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

	@Value("${openweathermap.apiKey}")
	private String apiKey;

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public Weather getWeather(String city) {

		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
		// String response = restTemplate.getForObject(url, String.class);

//      Weather weather = new Weather();
//        weather.cityName = city ;
//        weather.temperature = 45.5 ;
		Weather weather = new Weather();
		try {

			String response = restTemplate.getForObject(url, String.class);

			System.out.println("Response from OpenWeather API : " + response);
			weather = objectMapper.readValue(response, Weather.class);

		} catch (Exception e) {
			return null;
		}
		
		return weather;
	}
}
