package road;

import car.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Door on 23-Dec-17.
 */
public class Intersection {
    private List<Road> roads = new ArrayList<>();
    private int numberOfStoppedCars = 0;
    private Car lastRepairedCar;

    public void addRoad(Road road) {
        roads.add(road);
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public Intersection(List<Road> roads) {
        this.roads = roads;
    }


    public void stopCarOnIntersection(Car car) {
        synchronized (this) {
            numberOfStoppedCars++;
            try {
                wait();

                lastRepairedCar = car;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Optional<Car> restartStoppedCarInIntersection() {
        synchronized (this) {
            notify();
            numberOfStoppedCars--;
        }
        return Optional.ofNullable(lastRepairedCar);
    }


    public boolean anyStoppedCarsInIntersection() {
        return numberOfStoppedCars > 0;

    }
    @Override
    public String toString() {
        return "";
    }



}
