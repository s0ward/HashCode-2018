public class Position {

    int row;
    int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int calcPositionDistance(Position nextPosition) {
        return Math.abs(this.row - nextPosition.row) + Math.abs(this.column - nextPosition.column);
    }

//    @Override
//    public String toString() {
//        return "Position{" +
//            "row=" + row +
//            ", column=" + column +
//            '}';
//    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}