public class Ride {

    Position start;
    Position finish;
    int startTime;
    int endTime;
    int minDistance;
    boolean completed = false;
    int number;

    public Ride(int number, int a, int b, int x, int y, int startTime, int endTime) {
        this.number = number;
        start = new Position(a, b);
        finish = new Position(x, y);
        this.startTime = startTime;
        this.endTime = endTime;
        minDistance = calcMinDistance();
    }

    private int calcMinDistance() {
        return start.calcPositionDistance(finish);
    }

    @Override
    public String toString() {
        return "Ride{" +
            "start=" + start.toString() +
            ", finish=" + finish.toString() +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", minDistance=" + minDistance +
            '}';
    }
}
