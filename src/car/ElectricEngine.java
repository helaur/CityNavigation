package car;

/**
 * Created by Door on 25-Dec-17.
 */
public class ElectricEngine implements Engine {
    @Override
    public double getAirPollution() {
        return 0.1;
    }
    @Override
    public String toString() {
        return "Electric";
    }
}
