package boardGame.player;

import boardGame.Move;
import boardGame.PlayerAction;

import java.util.Scanner;

/**
 * Created by August on 2016-10-06.
 */
public class LocalConsolePlayer extends Player {
    @Override
    public void makeMove() {
        System.out.println("Enter coordinates to make a move: x y");
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        String[] coordinatesStringArray = choice.split(" ");
        if(coordinatesStringArray.length < 2)
            throw new ArithmeticException();
        int[] coordinates = {Integer.parseInt(coordinatesStringArray[0]), Integer.parseInt(coordinatesStringArray[1])};

        return new Move(new PlayerAction[]{new PlayerAction(coordinates[0], coordinates[1])});
    }
}
