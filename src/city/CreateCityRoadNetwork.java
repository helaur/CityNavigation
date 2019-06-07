package city;

import road.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Door on 23-Dec-17.
 */
public class CreateCityRoadNetwork {
    private CityRoadNames cityRoadNames;
    private CityRoadNetworkDatabase cityRoadNetworkDatabase;
    private static final int MAX_NUMBER_OF_ENTER_CITY_INTERSECTIONS = 4;
    private static final int MAX_NUMBER_OF_CAR_SHOP_INTERSECTIONS = 4;
    private int currentNumberOfCarShopIntersections = 0;
    private int currentNumberOfEnterCityIntersections = 0;
    private static final int MAX_NUMBER_OF_BAD_CONDITION_ROADS = 2;
    private int currentNumberOfBadConditionRoads = 0;

    public CreateCityRoadNetwork(CityRoadNetworkDatabase cityRoadNetwforkDatabase) {
        this.cityRoadNetworkDatabase = cityRoadNetwforkDatabase;
        cityRoadNames = new CityRoadNames();
    }

    public boolean createNewIntersectionForRoad(Road road) {


        synchronized (this) {

                List<Road> newRoads = new ArrayList<>();
                for (int i = 0; i < new Random().nextInt(4) + 1; i++) {
                    Optional<String> name = cityRoadNames.getNextRoadName();
                    if (name.isPresent()) {
                        if(currentNumberOfBadConditionRoads < MAX_NUMBER_OF_BAD_CONDITION_ROADS) {
                            newRoads.add(new RoadBadConditions(this, name.get()));
                            currentNumberOfBadConditionRoads++;
                        } else {
                            newRoads.add(new Road(this, name.get()));
                        }
                    } else {
                        return false;
                    }
                }
                newRoads.add(road);
                Intersection intersection = new Intersection(newRoads);

                if(currentNumberOfEnterCityIntersections <= MAX_NUMBER_OF_ENTER_CITY_INTERSECTIONS) {
                    intersection = new IntersectionEnterCity(newRoads, cityRoadNetworkDatabase);
                    cityRoadNetworkDatabase.addEnterCityIntersection((IntersectionEnterCity) intersection);
                    currentNumberOfEnterCityIntersections++;
                } else if(currentNumberOfCarShopIntersections <= MAX_NUMBER_OF_CAR_SHOP_INTERSECTIONS) {
                    intersection = new IntersectionCarShop(newRoads);
                    cityRoadNetworkDatabase.addCarShopIntersections((IntersectionCarShop) intersection);
                    currentNumberOfCarShopIntersections++;
                }
                for (Road newRoad : newRoads) {
                    newRoad.addIntersection(intersection);
                }
                cityRoadNetworkDatabase.addIntersection(intersection);
                cityRoadNetworkDatabase.addManyRoad(newRoads);

        return true;

        }
    }

}
