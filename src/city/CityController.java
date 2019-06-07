package city;

/**
 * Created by Door on 23-Dec-17.
 */
public class CityController {
    public static void main(String[] args) {
        CityRoadNetworkDatabase cityRoadNetworkDatabase = new CityRoadNetworkDatabase();
        CreateCityRoadNetwork createCityRoadNetwork = new CreateCityRoadNetwork(cityRoadNetworkDatabase);
        CityEnvironmentalAgency cityEnvironmentalAgency = new CityEnvironmentalAgency(cityRoadNetworkDatabase);
        NewCars newCars = new NewCars(cityRoadNetworkDatabase, 200, createCityRoadNetwork, cityEnvironmentalAgency);
        newCars.addNewCarsToRoad();
    }
}
