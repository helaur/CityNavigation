package car;

/**
 * Created by Door on 04-Jan-18.
 */
public class SoftTire extends Tire {
    @Override
    public boolean willPop() {
        return false;
    }
    @Override
    public String toString() {
        return "Soft Tire";
    }
}
