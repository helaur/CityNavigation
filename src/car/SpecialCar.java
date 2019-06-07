package car;

import city.CityEnvironmentalAgency;
import road.Intersection;
import road.Road;

import java.util.Optional;
import java.util.Random;

/**
 * Created by Door on 04-Jan-18.
 */
public class SpecialCar extends Car implements Runnable{
    public SpecialCar(Intersection currentIntersection, Engine engine, CityEnvironmentalAgency cityEnvironmentalAgency, Tire tire) {
        super(currentIntersection, engine, cityEnvironmentalAgency, tire);
    }

    @Override
    public void driveToNextIntersection() {
        try {
            Road road = super.getCurrentIntersection().getRoads().get(new Random().nextInt(super.getCurrentIntersection().getRoads().size()));

            super.setCurrentIntersection(road.travelOnRoad(this));
            restartStoppedCarInIntersection();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void restartStoppedCarInIntersection() {
        if(getCurrentIntersection().anyStoppedCarsInIntersection()) {
            System.out.println("Helped car");
            Optional<Car> car = getCurrentIntersection().restartStoppedCarInIntersection();

            if (car.isPresent() && (car.get().getEngine() instanceof LemonadeEngine || car.get().getEngine() instanceof ElectricEngine)) {

                car.get().setTire(new SoftTire());

            }
        }
    }
    @Override
    public void run() {
        while(true) {
            driveToNextIntersection();
        }
    }
}
