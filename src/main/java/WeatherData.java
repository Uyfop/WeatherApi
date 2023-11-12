import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public WeatherMain main; // Rename the field to WeatherMain
    public int visibility;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class WeatherMain { // Rename the class to WeatherMain
        public double temp;
        @SerializedName("feels_like")
        public double feelsLike;
        @SerializedName("temp_min")
        public double tempMin;
        @SerializedName("temp_max")
        public double tempMax;
        public int pressure;
        public int humidity;
        @SerializedName("sea_level")
        public int seaLevel;
        @SerializedName("grnd_level")
        public int groundLevel;
    }

    public static class Wind {
        public double speed;
        public int deg;
        public double gust;
    }

    public static class Rain {
        @SerializedName("1h")
        public double oneHour;
    }

    public static class Clouds {
        public int all;
    }

    public static class Sys {
        public int type;
        public int id;
        public String country;
        public long sunrise;
        public long sunset;
    }
}