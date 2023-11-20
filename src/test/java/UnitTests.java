import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

class UnitTests {

    private List<City> testCities;

    @Before
    public void setUp() {
        testCities = new ArrayList<>;
        testCities.add(new City("New York", 40.7128, -74.0060));
        testCities.add(new City("London", 51.5074, -0.1278));
        testCities.add(new City("Tokyo", 35.6895, 139.6917));
    }
    @Test
    public void testFindCityByName_ExistingCity() {
        String cityName = "London";
        City foundCity = City.findCityByName(testCities, cityName);
        assertEquals(cityName, foundCity.getCityName());
    }

    @Test
    public void testFindCityByName_NonExistingCity() {
        String cityName = "Paris";
        City foundCity = City.findCityByName(testCities, cityName);
        assertNull(foundCity);
    }
    @Test
    public void testChooseCity_ExistingCity() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("London");
        City selectedCity = City.ChooseCity(testCities);
        assertEquals("London", selectedCity.getCityName());
    }

    @Test
    public void testChooseCity_NonExistingCity() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("Paris");
        City selectedCity = City.ChooseCity(testCities);
        assertNull(selectedCity);
    }

    @Test
    public void testDeserialize()
    {
        JsonSerialize jsonSerialize = mock(JsonSerialize.class);
        WeatherData mockedWeatherData = new WeatherData();
        mockedWeatherData.main.temp = 298.48;
        mockedWeatherData.main.pressure = 1015;
        mockedWeatherData.visibility = 10000;
        when(jsonSerialize.DeserializeJson(Mockito.anyString()))
                .thenReturn(mockedWeatherData);
        WeatherData weatherData = jsonSerialize.DeserializeJson("src/main/resources/weatherMock.json");
        assertAll("weatherData",
                () -> assertEquals(mockedWeatherData.main.temp, weatherData.main.temp),
                () -> assertEquals(mockedWeatherData.main.pressure, weatherData.main.pressure),
                () -> assertEquals(mockedWeatherData.visibility, weatherData.visibility));
    }


    @Test
    public void testExport() throws IOException {
        Exporter exporter = mock(Exporter.class);
        when(Main.getExporter(Mockito.anyString())).thenReturn(new JsonSerialize());
        WeatherData mockedweatherData = JsonSerialize.DeserializeJson("src/main/resources/weatherMock.json");
        exporter.export(mockedweatherData, "src/test/mocked-answers/mockedAnswerJ.json");
        WeatherData weatherData = JsonSerialize.DeserializeJson("src/test/mocked-answers/mockedAnswerJ.json");
        assertAll("weatherData",
                () -> assertEquals(weatherData.main.temp,  mockedweatherData.main.temp),
                () -> assertEquals(weatherData.main.temp,  mockedweatherData.main.pressure),
                () -> assertEquals(weatherData.main.temp,  mockedweatherData.visibility));
    }

}