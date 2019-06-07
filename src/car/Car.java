package car;

import city.CityEnvironmentalAgency;
import road.Intersection;
import road.IntersectionCarShop;
import road.Road;

import java.util.Random;

/**
 * Created by Door on 23-Dec-17.
 */
public class Car implements Runnable {

    public static final int NUMBER_OF_STREETS_DRIVEN_BEFORE_EACH_CHECK_ENVIRONMENTAL_AGENCY = 5;
    public static final int NUMBER_OF_STREETS_DRIVEN_BEFORE_CHECKING_AIR_POLLUTION = 7;
    private static int ID = 0;
    private int id;
    private Intersection currentIntersection;
    private int totalRoadsDriven = 0;
    private Engine engine;
    private CityEnvironmentalAgency cityEnvironmentalAgency;
    private Tire tire;
    private int numberOfTimesCarHasHadToStop = 0;
    private int drivenOnBadRoadNumberOfTimes = 0;

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public int getDrivenOnBadRoadNumberOfTimes() {
        return drivenOnBadRoadNumberOfTimes;
    }

    public void setDrivenOnBadRoadNumberOfTimes(int drivenOnBadRoadNumberOfTimes) {
        this.drivenOnBadRoadNumberOfTimes = drivenOnBadRoadNumberOfTimes;
    }

    public int getNumberOfTimesCarHasHadToStop() {
        return numberOfTimesCarHasHadToStop;
    }

    public void setNumberOfTimesCarHasHadToStop(int numberOfTimesCarHasHadToStop) {
        this.numberOfTimesCarHasHadToStop = numberOfTimesCarHasHadToStop;
    }

    public int getTotalRoadsDriven() {
        return totalRoadsDriven;
    }

    public void setTotalRoadsDriven(int totalRoadsDriven) {
        this.totalRoadsDriven = totalRoadsDriven;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        cityEnvironmentalAgency.addEngine(this);
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Car.ID = ID;
    }

    public Intersection getCurrentIntersection() {
        return currentIntersection;
    }

    public void setCurrentIntersection(Intersection currentIntersection) {
        this.currentIntersection = currentIntersection;
    }

    public Car(Intersection currentIntersection, Engine engine, CityEnvironmentalAgency cityEnvironmentalAgency, Tire tire) {
        this.engine = engine;
        this.cityEnvironmentalAgency = cityEnvironmentalAgency;
        this.tire = tire;
        id = ID++;
        this.currentIntersection = currentIntersection;
    }

    public void increaseNumberOfTimesCarHasHadToStop() {
        numberOfTimesCarHasHadToStop++;
    }
    public void increaseDrivenOnBadRoadNumberOfTimes() {
        drivenOnBadRoadNumberOfTimes++;
    }


    public void driveToNextIntersection() {
        try {
            Road road = currentIntersection.getRoads().get(new Random().nextInt(currentIntersection.getRoads().size()));
            addInfoOfCarToEnvironmentalAgency();

            checkIfIntersectionHasCarShop();
            currentIntersection = road.travelOnRoad(this);
            checkIfCarCanDrive();
            totalRoadsDriven++;
            checkIfCarHasToStopInNextIntersection();
            //System.out.println("Car: " + id + " Currently at: " + road.toString() + " total roads driven is : " +  totalRoadsDriven);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkIfIntersectionHasCarShop() throws InterruptedException {
        if (currentIntersection instanceof IntersectionCarShop) {
            ((IntersectionCarShop) currentIntersection).fixCarInCarShop(this);
        }
    }
    private void checkIfCarCanDrive() throws InterruptedException {
        if (totalRoadsDriven % NUMBER_OF_STREETS_DRIVEN_BEFORE_CHECKING_AIR_POLLUTION == 0) {
            cityEnvironmentalAgency.checkAirPollution(this);
        }
    }
    private void addInfoOfCarToEnvironmentalAgency() throws InterruptedException {
        if (totalRoadsDriven % NUMBER_OF_STREETS_DRIVEN_BEFORE_EACH_CHECK_ENVIRONMENTAL_AGENCY == 0) {
            cityEnvironmentalAgency.addEngine(this);
            cityEnvironmentalAgency.addCityAirPollution(engine, NUMBER_OF_STREETS_DRIVEN_BEFORE_EACH_CHECK_ENVIRONMENTAL_AGENCY);
        }
    }

    public boolean checkIfCarWantsToChangeEngine() {
        return numberOfTimesCarHasHadToStop > 2 && (engine instanceof PetrolEngine || engine instanceof DieselEngine)
                && new Random().nextInt(6) == 0;
    }

    public void checkIfCarHasToStopInNextIntersection() {
        synchronized (this) {
            if (drivenOnBadRoadNumberOfTimes >= 3 && tire.willPop()) {
                cityEnvironmentalAgency.sendOutSpecialCar(this);
                currentIntersection.stopCarOnIntersection(this);
                drivenOnBadRoadNumberOfTimes = 0;
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
