import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("P - Enter a city name");
            System.out.println("Z - Exit");

            String userInput = scanner.nextLine();
            if (userInput.equals("P") || userInput.equals("p")) {
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




                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}