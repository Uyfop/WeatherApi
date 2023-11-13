import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UnitTests unitTests = new UnitTests();
        if(!unitTests.runTests())
            System.exit(1);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("P - Enter a city name");
            System.out.println("Z - Exit");

            String userInput = scanner.nextLine();
            if (userInput.equals("P") || userInput.equals("p"))
            {
                Gson citiesjson = new Gson();
                try (FileReader reader = new FileReader("src/main/resources/cities.json"))
                {
                    Type cityListType = new TypeToken<List<City>>() {}.getType();

                    List<City> cities =  citiesjson.fromJson(reader, cityListType);
                    System.out.println("City Options:");
                    cities.stream().forEach(city -> {
                        city.printCity();
                    });
                    City selectedCity = null;
                    while(true)
                    {
                        System.out.println("Enter the city name:");
                        String CityUserInput = scanner.nextLine();
                        selectedCity = City.findCityByName(cities, CityUserInput);
                        if(selectedCity != null)
                            break;
                        else
                            System.out.println("City not found. try again");
                    }

                    System.out.println("City Name: " + selectedCity.getCityName());
                    System.out.println("Latitude: " + selectedCity.getLatitude());
                    System.out.println("Longitude: " + selectedCity.getLongitude());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                try (FileReader reader = new FileReader("src/main/resources/weather.json"))
                {
                    Gson gson = new Gson();
                    WeatherData weatherData = gson.fromJson(reader, WeatherData.class);

                    System.out.println("Weather:");
                    System.out.println("temperature: " + weatherData.main.temp + "°C");
                    System.out.println("pressure: " + weatherData.main.pressure + " hPa");
                    System.out.println("humidity: " + weatherData.main.humidity + "%");

                    System.out.println("Enter the type of the file u want to save to P-PDF J-JSON X-XML");
                    String ExportUserInput = scanner.nextLine();
                    Exporter exporter = getExporter(ExportUserInput);
                    if("J".equals(ExportUserInput))
                        exporter.export(weatherData, "src/main/answerJ.json");
                    else if("X".equals(ExportUserInput))
                        exporter.export(weatherData, "src/main/answerX.xml");
                    else if("P".equals((ExportUserInput)))
                        exporter.export(weatherData, "src/main/answerP.pdf");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            if(userInput.equals("Z") || userInput.equals("z"))
                System.exit(1);

        }
    }
    private static Exporter getExporter(String choice) {
        switch (choice) {
            case "J":
                return new JsonSerialize();
            case "X":
                return new XMLSerialize();
            case "P":
                return new PDFSerialize();
            default:
                throw new IllegalArgumentException("U chose none");
        }
    }

}