package com.example.PublicApi.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("name")
    private String cityName;

   

	@JsonProperty("main")
    private Main main;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
		public double temperature;

        @JsonProperty("humidity")
		public double humidity;

		@Override
		public String toString() {
			return "Main [temperature=" + temperature + ", humidity=" + humidity + "]";
		}
    }
    
    @Override
   	public String toString() {
   		return "Weather [cityName=" + cityName + ", main=" + main + "]";
   	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
