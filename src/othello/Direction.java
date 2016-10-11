package othello;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-10-02.
 */
public enum Direction {
    NORTH (270), NORTHEAST(315), EAST(0), SOUTHEAST(45),
    SOUTH(90), SOUTHWEST(135), WEST(180), NORTHWEST(225);

    private final int angle;

    Direction(int angle){
        this.angle = angle;
    }

    private static Map<Integer, Direction> directionsMap = new HashMap<>();

    static{
        for(Direction direction : Direction.values())
            Direction.directionsMap.put(direction.getAngle(), direction);
    }

    public int getAngle(){
        return angle;
    }

    public double getRadianAngle(){
        return Math.toRadians(this.getAngle());
    }

    public Direction getOppositeDirection(){
        return Direction.directionsMap.get((this.getAngle() + 180)%360);
    }
}
