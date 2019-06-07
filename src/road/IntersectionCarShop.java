package road;

import car.Car;
import car.CarShop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Door on 23-Dec-17.
 */
public class IntersectionCarShop extends Intersection{
    private CarShop carShop;

    public CarShop getCarShop() {
        return carShop;
    }

    public void setCarShop() {
        this.carShop = new CarShop();
    }

    public IntersectionCarShop(List<Road> roads) {
        super(roads);
        this.carShop = new CarShop();
    }


    public void fixCarInCarShop(Car car) throws InterruptedException {
        carShop.fixCar(car);
    }






}
