import java.util.ArrayList;

public class Car {

    Position currentPosition = new Position(0, 0);
    int currentTime = 0;
    int pointsEarned = 0;
    ArrayList<Ride> completedRides = new ArrayList<>(50);

    public Ride calcClosestRide(ArrayList<Ride> rides) {
        int closestDistance = 100000000;
        Ride closestRide = null;
        for (Ride ride: rides) {
            int currentDistance = currentPosition.calcPositionDistance(ride.start);
            if (currentDistance < closestDistance) {
                closestRide = ride;
                closestDistance = currentDistance;
            }
        }
        return closestRide;
    }

    public void completeRide(Ride ride) {
        currentPosition = ride.finish;
        currentTime += ride.minDistance;
        pointsEarned += ride.minDistance;
        ride.completed = true;
        completedRides.add(ride);
    }

    public void moveToNextRide(Ride ride) {
        currentTime += currentPosition.calcPositionDistance(ride.start);
        currentPosition = ride.start;
    }

    public int distanceToRideEnd(Ride ride) {
        return currentPosition.calcPositionDistance(ride.start) + ride.minDistance;
    }
}
