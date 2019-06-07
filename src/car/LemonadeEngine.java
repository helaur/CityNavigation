package car;

/**
 * Created by Door on 25-Dec-17.
 */
public class LemonadeEngine implements Engine {
    @Override
    public double getAirPollution() {
        return 0.5;
    }
    @Override
    public String toString() {
        return "Lemonade";
    }
}
