package city;

import car.Car;
import car.Engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Door on 04-Jan-18.
 */
public class DisplayInfoInPlainText implements DisplayInfoStrategy {
    @Override
    public void displayInfoAboutCarsAndEngines(HashMap<Car, Engine> carEngineHashMap) {
        Iterator iterator = carEngineHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());

        }

    }
}
