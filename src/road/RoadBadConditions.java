package road;

import car.Car;
import city.CreateCityRoadNetwork;

/**
 * Created by Door on 04-Jan-18.
 */
public class RoadBadConditions extends Road{

    public RoadBadConditions(CreateCityRoadNetwork createCityRoadNetwork, String name) {
        super(createCityRoadNetwork, name);
    }

    @Override
    public Intersection travelOnRoad(Car car) throws InterruptedException {
        car.increaseDrivenOnBadRoadNumberOfTimes();
        return super.travelOnRoad(car);
    }
}
