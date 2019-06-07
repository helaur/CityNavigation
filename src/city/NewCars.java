package city;

import car.*;
import road.Intersection;
import road.IntersectionEnterCity;
import road.Road;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Door on 25-Dec-17.
 */
public class NewCars {
    private CityRoadNetworkDatabase cityRoadNetworkDatabase;
    private int numberOfCars;
    private  CreateCityRoadNetwork createCityRoadNetwork;
    private CityEnvironmentalAgency cityEnvironmentalAgency;
    private ExecutorService executorService;
    private static final int EXTRA_NUMBER_OF_THREADS_FOR_BIRD_AND_ENVIROMENTAL_AGENCY = 10;

    public NewCars(CityRoadNetworkDatabase cityRoadNetworkDatabase, int numberOfCars, CreateCityRoadNetwork createCityRoadNetwork, CityEnvironmentalAgency cityEnvironmentalAgency) {
        executorService = Executors.newFixedThreadPool(numberOfCars + EXTRA_NUMBER_OF_THREADS_FOR_BIRD_AND_ENVIROMENTAL_AGENCY);
        this.cityRoadNetworkDatabase = cityRoadNetworkDatabase;
        this.numberOfCars = numberOfCars;
        this.createCityRoadNetwork = createCityRoadNetwork;
        this.cityEnvironmentalAgency = cityEnvironmentalAgency;
        cityEnvironmentalAgency.setExecutorService(executorService);
        createOneEnterCityIntersectionWithRoads();
    }
    public void addNewCarsToRoad() {
        List<IntersectionEnterCity> intersectionEnterCity = cityRoadNetworkDatabase.getEnterCityIntersections();
        addEnvironmentallyFriendlyCars(intersectionEnterCity);
        addPetrolAndDieselCars(intersectionEnterCity);
        addBirdToCity();
        executorService.submit(cityEnvironmentalAgency);
    }
    private void addEnvironmentallyFriendlyCars(List<IntersectionEnterCity> intersectionEnterCity) {
        List<Engine> EnvironmentallyFriendlyEngineTypes = Arrays.asList(new LemonadeEngine(), new ElectricEngine());
        for (int i = 0; i < numberOfCars * 0.1; i++) {
            IntersectionEnterCity intersectionEnterCity1 = intersectionEnterCity.get(new Random().nextInt(intersectionEnterCity.size()));
            Car car = new Car(intersectionEnterCity1, EnvironmentallyFriendlyEngineTypes.get(new Random().nextInt(EnvironmentallyFriendlyEngineTypes.size())), cityEnvironmentalAgency, new Tire());
            cityRoadNetworkDatabase.addCar(car);
            executorService.submit(car);

        }
    }

    public void addPetrolAndDieselCars(List<IntersectionEnterCity> intersectionEnterCity) {
        List<Engine> PetrolAndDieselEngineTypes = Arrays.asList(new DieselEngine(), new PetrolEngine());
        for (int i = 0; i < numberOfCars * 0.9; i++) {
            IntersectionEnterCity intersectionEnterCity1 = intersectionEnterCity.get(new Random().nextInt(intersectionEnterCity.size()));
            Car car = new Car(intersectionEnterCity1, PetrolAndDieselEngineTypes.get(new Random().nextInt(PetrolAndDieselEngineTypes.size())), cityEnvironmentalAgency, new Tire());
            cityRoadNetworkDatabase.addCar(car);
            executorService.submit(car);
        }
    }

    private void createOneEnterCityIntersectionWithRoads() {
        Road road1 = new Road(createCityRoadNetwork, "1");
        Road road2 = new Road(createCityRoadNetwork, "2");

        List<Road> roads = new ArrayList<>();
        roads.add(road1);
        roads.add(road2);
        Intersection intersection = new IntersectionEnterCity(roads, cityRoadNetworkDatabase);
        road1.addIntersection(intersection);
        road2.addIntersection(intersection);
        cityRoadNetworkDatabase.addIntersection(intersection);
    }

    public void addBirdToCity() {
        executorService.submit(new Bird(cityEnvironmentalAgency));
    }

    public void shutdownNewCarProduction() {
        executorService.shutdown();
    }

}
