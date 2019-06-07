package car;

/**
 * Created by Door on 25-Dec-17.
 */
public class DieselEngine implements Engine{

    @Override
    public double getAirPollution() {
        return 3;
    }
    @Override
    public String toString() {
        return "Diesel";
    }
}
