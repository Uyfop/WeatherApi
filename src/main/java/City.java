    import java.util.List;
    import java.util.Optional;
    import java.util.Scanner;

    public class City {
        private String cityName;
        private double latitude;
        private double longitude;

        public City(String cityName, double latitude, double longitude)
        {
            this.cityName = cityName;
            this.latitude = latitude;
            this.longitude = longitude;
        }
        public String getCityName() {
            return cityName;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }
        @Override
        public String toString()
        {
            return "City{" +
                    "cityName='" + cityName + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
        public void printCity()
        {
            System.out.println("City Name: " + cityName);
        }

        public static City findCityByName(List<City> cities, String cityName) {
            Optional<City> foundCity = cities.stream()
                    .filter(city -> city.getCityName().equalsIgnoreCase(cityName))
                    .findFirst();

            return foundCity.orElse(null);
        }
        public static City ChooseCity(List<City> cities)
        {
            Scanner scanner = new Scanner(System.in);
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
            return selectedCity;
        }
    }