package account;

public class LoyaltyPoints {
    private int numOfPoints;

    public LoyaltyPoints(int points) {
        this.numOfPoints = points;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public double getValueOfPointsInEuro() {
        return numOfPoints * .50;
    }

    public void updatePoints(int points){
        numOfPoints += points;
    }
}
