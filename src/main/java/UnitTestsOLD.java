import java.util.ArrayList;
import java.util.List;

public class UnitTestsOLD {

    public boolean runTests()
    {
        UnitTestsOLD unitTests = new UnitTestsOLD();
        if (!unitTests.testFindCityByName("Warszawa") || !unitTests.testFindCityByName("Krakow"))
        {
            System.out.println("FindCityByName Method not working");
            return false;
        }
        if (unitTests.testFindCityByName("Karpacz"))
        {
            System.out.println("FindCityByName Method not working");
            return false;
        }
        return true;
    }
    public boolean testFindCityByName(String cityName)
    {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Warszawa", 52.2297, 21.0122));
        cities.add(new City("Krakow", 50.0647, 19.9450));
        City CityTest = null;
        CityTest = City.findCityByName(cities, cityName);
        if(CityTest != null)
            return true;
        return false;
    }
}
