package com.example.PublicApi.Controller;

import com.example.PublicApi.Model.Weather;
import com.example.PublicApi.Model.WeatherService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/weather")
	public String getWeather(@RequestParam(name = "city", required = false, defaultValue = "London") String city, Model model) {
		Weather weather = weatherService.getWeather(city);
		
		if(weather == null)
			return  "weather";
		
		System.out.println( weather);

		model.addAttribute("weather", weather);
		
		

//		if (weather.getMain() != null) {
//			model.addAttribute("temparature", weather.getMain().temperature);
//			System.out.println(weather.getMain().temperature);
//
//		}
//		if (weather.getMain() != null) {
//			model.addAttribute("humidity", weather.getMain().humidity);
//			System.out.println(weather.getMain().humidity);
//
//		}
		
		System.out.println(weather);
		return "weather";
	}
	
	 @GetMapping("/downloadWeather")
	    public ResponseEntity<InputStreamResource> downloadWeather(@RequestParam("city") String city) throws IOException {
	        Weather weather = weatherService.getWeather(city);

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        PrintWriter writer = new PrintWriter(out);
	        
	        writer.println("City,Temperature (°C),Humidity (%)");
	        writer.println(weather.getCityName() + "," + weather.getMain().temperature + "," + weather.getMain().humidity);
	        writer.flush();

	        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
	        InputStreamResource resource = new InputStreamResource(in);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=weather.csv");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentType(MediaType.parseMediaType("application/csv"))
	                .body(resource);
	    }
}
