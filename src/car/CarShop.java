package car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Door on 25-Dec-17.
 */
public class CarShop {
    private List<Car> fixedCars = new ArrayList<>();
    public Car currentlyFixing = null;
    public List<Engine> environmentallyFriendlyEngines = Arrays.asList(new ElectricEngine(), new LemonadeEngine());

    public void fixCar(Car car) throws InterruptedException {
        synchronized (this) {
            checkLine();
            currentlyFixing = car;
            Thread.sleep(50);
            System.out.println("Fixed car " + car);
            if(car.checkIfCarWantsToChangeEngine()) {
                car.setEngine(environmentallyFriendlyEngines.get(new Random().nextInt(environmentallyFriendlyEngines.size())));
            }
            currentlyFixing = null;
            notify();
        }
    }

    public void checkLine() throws InterruptedException {
        synchronized (this) {
            while (currentlyFixing != null) {
                wait();
            }
        }
    }

    public void getFixedCars(Consumer<List<Car>> lambda) {
        lambda.accept(fixedCars);
    }
}
