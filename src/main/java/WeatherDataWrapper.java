import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WeatherDataWrapper {

    private WeatherData weatherData;

    public WeatherDataWrapper() {
    }

    public WeatherDataWrapper(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @XmlElement
    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
}