package city;

import car.Car;
import road.Intersection;
import road.IntersectionCarShop;
import road.IntersectionEnterCity;
import road.Road;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Door on 23-Dec-17.
 */
public class CityRoadNetworkDatabase {
    private List<Car> allCarsOnRoad = new ArrayList<>();
    private List<Intersection> allIntersections = new ArrayList<>();
    private List<IntersectionEnterCity> enterCityIntersections = new ArrayList<>();
    private List<IntersectionCarShop> carShopIntersections = new ArrayList<>();
    private List<Road> allRoads = new ArrayList<>();

    public List<IntersectionCarShop> getCarShopIntersections() {
        return carShopIntersections;
    }

    public void setCarShopIntersections(List<IntersectionCarShop> carShopIntersections) {
        carShopIntersections = carShopIntersections;
    }

    public List<IntersectionEnterCity> getEnterCityIntersections() {
        return enterCityIntersections;
    }

    public void setEnterCityIntersections(List<IntersectionEnterCity> enterCityIntersections) {
        enterCityIntersections = enterCityIntersections;
    }
    public void addEnterCityIntersection(IntersectionEnterCity enterCityIntersections) {
        this.enterCityIntersections.add(enterCityIntersections);
    }
    public void addCar(Car car) {
        allCarsOnRoad.add(car);
    }
    public void addRoad(Road road) {
        allRoads.add(road);
    }
    public void addManyRoad(List<Road> roads) {
        allRoads.addAll(roads);
    }
    public void addIntersection(Intersection intersection) {
        allIntersections.add(intersection);
    }

    public List<Intersection> getAllIntersections() {
        return allIntersections;
    }

    public void setAllIntersections(List<Intersection> allIntersections) {
        this.allIntersections = allIntersections;
    }

    public List<Road> getAllRoads() {
        return allRoads;
    }

    public void setAllRoads(List<Road> allRoads) {
        this.allRoads = allRoads;
    }

    public List<Car> getAllCarsOnRoad() {
        return allCarsOnRoad;
    }

    public void setAllCarsOnRoad(List<Car> allCarsOnRoad) {
        this.allCarsOnRoad = allCarsOnRoad;
    }

    public void addCarShopIntersections(IntersectionCarShop Intersection) {
        carShopIntersections.add(Intersection);
    }

    public void addCarToRoad(Car car) {
        allCarsOnRoad.add(car);
    }



}
