import java.util.List;

public class City {
    private String cityName;
    private double latitude;
    private double longitude;

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
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
    public void printCity() {
        System.out.println("City Name: " + cityName);
    }

    public static City findCityByName(List<City> cities, String cityName) {
        for (City city : cities) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                return city;
            }
        }
        return null;
    }
}