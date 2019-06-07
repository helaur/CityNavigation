package road;

import car.Car;
import city.CreateCityRoadNetwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by Door on 23-Dec-17.
 */
public class Road {
    private int roadLengthInTime = new Random().nextInt(18) + 3;
    private List<Intersection> intersections = new ArrayList<>();
    private CreateCityRoadNetwork createCityRoadNetwork;
    private String name;

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public int getRoadLengthInTime() {
        return roadLengthInTime;
    }

    public void setRoadLengthInTime(int roadLengthInTime) {
        this.roadLengthInTime = roadLengthInTime;
    }

    public void addIntersection(Intersection intersection) {
        if (intersections.size() < 2) {
            intersections.add(intersection);
        } else {
            System.out.println("Already has 2 intersections");
        }


    }

    public Road(CreateCityRoadNetwork createCityRoadNetwork, String name) {

        this.createCityRoadNetwork = createCityRoadNetwork;
        this.name = name;
    }


    public Intersection travelOnRoad(Car car) throws InterruptedException {
        Thread.sleep(roadLengthInTime);
        if (!checkIfCanCreateNewConnections(car.getCurrentIntersection())) return car.getCurrentIntersection();
        if(intersections.get(0) == car.getCurrentIntersection()) {
            return intersections.get(1);

        } else {
            return intersections.get(0);
        }
    }

    private boolean checkIfCanCreateNewConnections(Intersection intersection) {
        if (intersections.size() == 0) {
            intersections.add(intersection);
        }
        if (intersections.size() < 2) {
            if (!createCityRoadNetwork.createNewIntersectionForRoad(this)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return "Road name is : " + name;
    }
}
