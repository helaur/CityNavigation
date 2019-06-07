package road;

import car.Car;
import car.Engine;
import city.CityRoadNetworkDatabase;

import java.util.List;

/**
 * Created by Door on 23-Dec-17.
 */
public class IntersectionEnterCity extends Intersection {

    public IntersectionEnterCity(List<Road> roads, CityRoadNetworkDatabase cityRoadNetworkDatabase) {
        super(roads);
        cityRoadNetworkDatabase.addEnterCityIntersection(this);
    }



}
