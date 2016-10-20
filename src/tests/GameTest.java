package tests;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.boardGame.match.GameMatchFactory;
import boardGameLibrary.player.ComputerPlayer;
import boardGameLibrary.player.LocalPlayer;
import boardGameLibrary.player.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.router.Router;
import utilities.router.paneRouter.PaneRouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-10-11.
 */
public class GameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane content = new StackPane();
        content.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        content.minWidthProperty().bind(primaryStage.widthProperty());
        content.maxWidthProperty().bind(primaryStage.widthProperty());
        primaryStage.setScene(new Scene(content, 600, 600));

        Router router = new PaneRouter(content);
        //Router router = new ConsoleRouter();
        Router.setApplicationRouter(router);

        Map map = new HashMap();

        Player player1 = new LocalPlayer("August", Color.WHITE);
        Player player2 = new ComputerPlayer("Björn", Color.BLACK);

        GameMatch match = GameMatchFactory.createGameMatch("Othello", new Player[]{player1, player2}, false);
        map.put("GameMatch", match);
        router.route("GameView", map);

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
