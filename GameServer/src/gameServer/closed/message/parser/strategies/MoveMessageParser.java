package gameServer.closed.message.parser.strategies;

import gameServer.gameEntities.Move;
import gameServer.message.parser.IMessageParser;

import java.util.List;

/**
 * Parses a set of Strings to a Move object.
 *
 * A valid move message is formatted as follows:
 * MOVE, 1, 2, 5, 6
 * Where the second string is the number of coordinates in the message
 * and the third is the number of string per coordinate-set.
 *
 * So the message above contains one set of coordinates that
 * contains two strings (x and y). So parsing it would result
 * in a Move object with one actions of coordinates x:5 and y:6
 */
public class MoveMessageParser implements IMessageParser<Move> {

    private final int MOVE_LABEL_INDEX = 0;
    private final int NUMBER_OF_ACTIONS_INDEX = 1;
    //Each coordinate is arranged after index of numberOfActions.
    //i.e. If coordinatesPerAction = 2 -> x1: 3, y1: 4, x2: 5, y2: 6 and so on.
    private final int COORDINATES_PER_ACTION_INDEX = 2;
    private final int ACTIONS_START_INDEX = 3;

    private List<String> input;

    @Override
    public List<String> getInput() {
        return this.input;
    }

    @Override
    public IMessageParser setInput(List<String> input) {
        this.input = input;
        return this;
    }

    @Override
    public boolean isValid() {
        return(getInput().size() > 2
                && getInput().get(MOVE_LABEL_INDEX).equals("MOVE")
        );
    }

    @Override
    public Move parse() {
        int numberOfPoints = getInput().size()-2 % getNumberOfActionsInMove();

        int coordinatesPerAction = getNumberOfCoordinatesPerAction();
        int inputIndexCounter = ACTIONS_START_INDEX;

        int[][] actions = new int[numberOfPoints][];
        for(int i = 0; i < actions.length; i++){
            int[] coordinates = new int[coordinatesPerAction];
            for(int a = 0; a < coordinates.length; a++)
                coordinates[a] = Integer.parseInt(getInput().get(inputIndexCounter++));
            actions[i] = coordinates;
        }

        return new Move(actions);
    }

    public int getNumberOfActionsInMove(){
        return Integer.parseInt(getInput().get(NUMBER_OF_ACTIONS_INDEX));
    }

    public int getNumberOfCoordinatesPerAction(){
        return Integer.parseInt(getInput().get(COORDINATES_PER_ACTION_INDEX));
    }


}
