package car;

/**
 * Created by Door on 25-Dec-17.
 */
public class PetrolEngine implements Engine {
    @Override
    public double getAirPollution() {
        return 2;
    }
    @Override
    public String toString() {
        return "Petrol";
    }
}
