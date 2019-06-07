package city;

/**
 * Created by Door on 04-Jan-18.
 */
public class Bird implements Runnable {

    private CityEnvironmentalAgency cityEnvironmentalAgency;

    public Bird(CityEnvironmentalAgency cityEnvironmentalAgency) {

        this.cityEnvironmentalAgency = cityEnvironmentalAgency;
    }

    @Override
    public void run() {
        while (true) {
            if(cityEnvironmentalAgency.getAirPollution() < 400) {
                System.out.println("Puhas õhk on puhas õhk on" +
                        "rõõmus linnu elu!");
            } else {
                System.out.println("Inimene tark, inimene tark – saastet täis" +
                        "on linnapark");
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
