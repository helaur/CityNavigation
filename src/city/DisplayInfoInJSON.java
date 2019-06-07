package city;

import car.Car;
import car.Engine;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Door on 04-Jan-18.
 */
public class DisplayInfoInJSON implements DisplayInfoStrategy {
    @Override
    public void displayInfoAboutCarsAndEngines(HashMap<Car, Engine> carEngineHashMap) {
        JSONObject infoAboutCarsAndEnginesInJSON = new JSONObject();
        Iterator iterator = carEngineHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();

            try {
                infoAboutCarsAndEnginesInJSON.append(pair.getKey().toString(), pair.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        System.out.println(infoAboutCarsAndEnginesInJSON.toString());
    }
}
