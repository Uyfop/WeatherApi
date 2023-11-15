import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        UnitTests unitTests = new UnitTests();
        if (!unitTests.runTests())
            System.exit(1);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("P - Enter a city name");
            System.out.println("Z - Exit");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("P")){
                List<City> cities = JsonSerialize.DeserializeJsonToList();
                System.out.println("City Options:");
                cities.forEach(City::printCity);
                City selectedCity = City.ChooseCity(cities);
                System.out.println("City Name: " + selectedCity.getCityName());
                System.out.println("Latitude: " + selectedCity.getLatitude());
                System.out.println("Longitude: " + selectedCity.getLongitude());
                HttpReader(selectedCity.getLatitude(), selectedCity.getLongitude());
                WeatherData weatherData = JsonSerialize.DeserializeJson();
                System.out.println("Weather:");
                System.out.println("temperature: " + weatherData.main.temp + "°C");
                System.out.println("pressure: " + weatherData.main.pressure + " hPa");
                System.out.println("humidity: " + weatherData.main.humidity + "%");
                System.out.println("Enter the type of the file u want to save to P-PDF J-JSON X-XML");
                String ExportUserInput = scanner.nextLine();
                Exporter exporter = getExporter(ExportUserInput);
                switch (ExportUserInput) {
                    case "J" -> exporter.export(weatherData, "src/main/answerJ.json");
                    case "X" -> exporter.export(weatherData, "src/main/answerX.xml");
                    case "P" -> exporter.export(weatherData, "src/main/answerP.pdf");
                }
            }
            if (userInput.equalsIgnoreCase("Z")){
                System.exit(1);
            }
        }
    }
        private static Exporter getExporter(String choice)
        {
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

        private static void HttpReader(double lat, double lon)
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String apikey = "de741ce5b423e94ec91fea7fede186a1";
            HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?lat="+ lat + "&lon="+ lon + "&appid="+ apikey);
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                System.out.println(response.getStatusLine().toString());
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    Exporter exporter = getExporter("J");
                    exporter.export(result, "src/main/resources/weather.json");
                }
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }



}