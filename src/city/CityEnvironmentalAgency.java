package city;

import car.*;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

/**
 * Created by Door on 25-Dec-17.
 */
public class CityEnvironmentalAgency implements Runnable{
    private volatile double  airPollution = 0;
    private HashMap<Car, Engine> carEngines = new HashMap<>();
    private final Object lock = new Object();
    private ExecutorService executorService;
    private CityRoadNetworkDatabase cityRoadNetworkDatabase;
    private static final int MAX_NUMBER_OF_SPECIAL_CARS = 1;
    private int currentNumberOfSpecialCars = 0;

    public double getAirPollution() {
        return airPollution;
    }

    public void setAirPollution(double airPollution) {
        this.airPollution = airPollution;
    }

    public CityEnvironmentalAgency(CityRoadNetworkDatabase cityRoadNetworkDatabase) {

        this.cityRoadNetworkDatabase = cityRoadNetworkDatabase;
    }


    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void addEngine(Car car) {
        carEngines.put(car, car.getEngine());

    }

    public long getNumberOfDieselAndPetrolEngines() {
        synchronized (this) {
            return carEngines.values().stream().filter(engine -> engine instanceof DieselEngine || engine instanceof PetrolEngine).count();
        }
    }


    public void addCityAirPollution(Engine engine, int numberOfStreetsDrivenSinceLastCheck) throws InterruptedException {
        synchronized(this) {
            System.out.println("City Air Pollution is: " + airPollution);
            airPollution += engine.getAirPollution() * numberOfStreetsDrivenSinceLastCheck;

        }
    }

    public void checkAirPollution(Car car) throws InterruptedException {

            if(airPollution > 400 && car.getEngine() instanceof DieselEngine) {
                synchronized (lock) {

                    lock.notify();
                }
                car.increaseNumberOfTimesCarHasHadToStop();
                synchronized (this) {
                    wait();
                }
            } else if(airPollution > 500 &&  car.getEngine() instanceof PetrolEngine) {
                synchronized (lock) {

                    lock.notify();
                }
                car.increaseNumberOfTimesCarHasHadToStop();
                synchronized (this) {
                    wait();
                }

            }
        }

    public void resetAirPollution() {
        synchronized (this) {
            System.out.println("aaa " + getNumberOfDieselAndPetrolEngines());
            if (getNumberOfDieselAndPetrolEngines() < 70) {

                airPollution = 0;
            } else {
                airPollution = airPollution * 0.4;
            }
            notifyAll();
        }
    }

    public void sendOutSpecialCar(Car car) {
        synchronized (this) {
            if (currentNumberOfSpecialCars < MAX_NUMBER_OF_SPECIAL_CARS) {
                System.out.println("SENT OUT SPECIAL CAR");
                currentNumberOfSpecialCars++;

                executorService.submit(new SpecialCar(cityRoadNetworkDatabase.getAllIntersections()
                        .stream()
                        .filter(intersection -> car.getCurrentIntersection() != intersection).findAny().get()
                        , new LemonadeEngine(),
                        this, new Tire()));


            }
        }
    }

    public void displayInfoAboutCity(DisplayInfoStrategy displayInfoStrategy) {
        displayInfoStrategy.displayInfoAboutCarsAndEngines(carEngines);
    }


    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    lock.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(airPollution > 400) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                displayInfoAboutCity(new DisplayInfoInJSON());
                resetAirPollution();
            }

        }
    }
}
