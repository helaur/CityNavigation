package city;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Door on 23-Dec-17.
 */
public class CityRoadNames {
    private String[] roadNames;
    private int currentRoadNameIndex = -1;
    private int totalNames = -1;

    public int getTotalNames() {
        return totalNames;
    }

    public void setTotalNames(int totalNames) {
        this.totalNames = totalNames;
    }

    public int getCurrentRoadNameIndex() {
        return currentRoadNameIndex;
    }

    public void setCurrentRoadNameIndex(int currentRoadNameIndex) {
        this.currentRoadNameIndex = currentRoadNameIndex;
    }

    public String[] getRoadNames() {
        return roadNames;
    }

    public void setRoadNames(String[] roadNames) {
        this.roadNames = roadNames;
    }

    public CityRoadNames() {
        String names = "Franklin Avenue\n" +
                "Harrison Avenue\n" +
                "5th Street North\n" +
                "Tanglewood Drive\n" +
                "Durham Court\n" +
                "Mill Road\n" +
                "Marshall Street\n" +
                "Front Street South\n" +
                "East Avenue\n" +
                "Lawrence Street\n" +
                "John Street\n" +
                "School Street\n" +
                "Country Club Drive\n" +
                "Durham Road\n" +
                "Vine Street\n" +
                "Bayberry Drive\n" +
                "Elizabeth Street\n" +
                "Roberts Road\n" +
                "West Street\n" +
                "Division Street\n" +
                "Fieldstone Drive\n" +
                "Ridge Road\n" +
                "Oxford Court\n" +
                "Clark Street\n" +
                "Grove Street\n" +
                "Route 6\n" +
                "Route 1\n" +
                "North Street\n" +
                "Roosevelt Avenue\n" +
                "Lexington Drive\n" +
                "George Street\n" +
                "Arch Street\n" +
                "Main Street West\n" +
                "Deerfield Drive\n" +
                "York Street\n" +
                "Briarwood Drive\n" +
                "Route 29\n" +
                "Route 11\n" +
                "Elmwood Avenue\n" +
                "Liberty Street\n" +
                "Essex Court\n" +
                "Poplar Street\n" +
                "Arlington Avenue\n" +
                "Chestnut Street\n" +
                "5th Street South\n" +
                "12th Street East\n" +
                "Virginia Avenue\n" +
                "Grand Avenue\n" +
                "Evergreen Drive\n" +
                "Warren Street\n" +
                "Maiden Lane\n" +
                "Magnolia Court\n" +
                "Pearl Street\n" +
                "Bay Street\n" +
                "Route 9\n" +
                "Eagle Street\n" +
                "Redwood Drive\n" +
                "Sycamore Drive\n" +
                "Locust Lane\n" +
                "Belmont Avenue\n" +
                "Aspen Court\n" +
                "5th Street East\n" +
                "Old York Road\n" +
                "B Street\n" +
                "Madison Avenue\n" +
                "Andover Court\n" +
                "Lincoln Avenue\n" +
                "Depot Street\n" +
                "Warren Avenue\n" +
                "Briarwood Court\n" +
                "Washington Avenue\n" +
                "Lexington Court\n" +
                "Jackson Avenue\n" +
                "Amherst Street\n" +
                "Garden Street\n" +
                "King Street\n" +
                "Summit Avenue\n" +
                "Canal Street\n" +
                "2nd Street North\n" +
                "3rd Street West\n" +
                "Linden Street\n" +
                "Heather Court\n" +
                "Church Road\n" +
                "11th Street\n" +
                "Holly Drive\n" +
                "South Street\n" +
                "Manor Drive\n" +
                "Edgewood Drive\n" +
                "Windsor Drive\n" +
                "Myrtle Street\n" +
                "Colonial Drive\n" +
                "Church Street\n" +
                "Edgewood Road\n" +
                "Penn Street\n" +
                "Schoolhouse Lane\n" +
                "Virginia Street\n" +
                "Madison Street\n" +
                "Cottage Street\n" +
                "Delaware Avenue\n" +
                "Ridge Avenue";
        roadNames = names.split("\n");
        totalNames = roadNames.length;
    }

    public Optional<String> getNextRoadName() {
        if(canGetNextRoadName()) {
            currentRoadNameIndex++;

            return Optional.of(roadNames[currentRoadNameIndex]);

        } else {
            return Optional.empty();
        }
    }

    public boolean canGetNextRoadName() {
        return currentRoadNameIndex < totalNames - 1;

    }
}
