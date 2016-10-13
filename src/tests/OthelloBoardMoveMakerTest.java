package tests;

import boardGame.Move;
import boardGame.PlayerAction;
import boardGame.player.LocalConsolePlayer;
import boardGame.player.Player;
import javafx.scene.paint.Color;
import othello.OthelloBoardMoveMaker;
import othello.OthelloMove;
import othello.OthelloPawn;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloBoardMoveMakerTest extends OthelloBoardMoveMaker {

    private final int width = 8;
    private final int height = 8;

    public OthelloBoardMoveMakerTest() {

    }

    public void runTest(){
        Player white = new TestPlayer();
        Player black = new TestPlayer();

        this.board[1][3] = new OthelloPawn(black, Color.BLACK);
        this.board[2][2] = new OthelloPawn(black, Color.BLACK);
        this.board[3][1] = new OthelloPawn(black, Color.BLACK);
        this.board[2][1] = new OthelloPawn(black, Color.BLACK);

        this.board[4][0] = new OthelloPawn(white, Color.WHITE);
        this.board[2][0] = new OthelloPawn(white, Color.WHITE);

        printBoard(white, black);
        System.out.println("\n");
        Move testMove = new OthelloMove(new PlayerAction(0, 4));
        this.makeMove(white, testMove);

        printBoard(white, black);
    }

    public void runMatchTest(){
        LocalConsolePlayer player1 = new LocalConsolePlayer();
        LocalConsolePlayer player2 = new LocalConsolePlayer();

        Player[] players = {player1, player2};

        printBoard(player1, player2);
        while(true){
            for(Player player : players){
                printBoard(player1, player2);
            }
        }
    }

    private void printBoard(Player white, Player black){
        for(int y = 0; y <= this.width; y++){
            for(int x = 0; x <= this.height; x++) {
                if (x == 0 && y > 0)
                    System.out.print(" " + (y - 1) + " |");
                else if (y == 0 && x > 0)
                    System.out.print(" " + (x - 1) + " |");
                else if (y > 0 && x > 0) {
                    if (this.board[x-1][y-1].getOwner().equals(white))
                        System.out.print(" W |");
                    else if (this.board[x-1][y-1].getOwner().equals(black))
                        System.out.print(" B |");
                    else
                        System.out.print(" O |");
                }
                else
                    System.out.print("    ");
            }
            System.out.print("\n");
        }
    }
/*
    public static void main(String[] args){
        OthelloBoardMoveMakerTest test = new OthelloBoardMoveMakerTest();
        //test.runTest();
        test.runMatchTest();
    }*/
}
