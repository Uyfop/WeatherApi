import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class JsonSerialize implements Exporter
{
    @Override
    public void export(Object data, String filePath) throws IOException
    {
        try (Writer writer = new FileWriter(filePath))
        {
            Gson gson = new Gson();
            gson.toJson(data, writer);
        }
    }
    public static List<City> DeserializeJsonToList()
    {
        Gson citiesjson = new Gson();
        List<City> cities = null;
        try (FileReader reader = new FileReader("src/main/resources/cities.json")) {
            Type cityListType = new TypeToken<List<City>>() {
            }.getType();

            cities = citiesjson.fromJson(reader, cityListType);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static WeatherData DeserializeJson() {
        Gson gson = new Gson();
        WeatherData weatherData = null;

        try (FileReader reader = new FileReader("src/main/resources/weatherMock.json")) {
           weatherData = gson.fromJson(reader, WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData;
    }
}