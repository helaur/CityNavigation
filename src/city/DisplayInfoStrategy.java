package city;

import car.Car;
import car.Engine;

import java.util.HashMap;

/**
 * Created by Door on 04-Jan-18.
 */
public interface DisplayInfoStrategy {
    void displayInfoAboutCarsAndEngines(HashMap<Car, Engine> carEngineHashMap);
}
