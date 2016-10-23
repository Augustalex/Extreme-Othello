package tests;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.players.*;
import boardGamePlugins.othello.players.NaturalAI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.router.Router;
import utilities.router.paneRouter.PaneRouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-10-11.
 */
public class GameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane content = new StackPane();
        //content.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        content.minWidthProperty().bind(primaryStage.widthProperty());
        content.maxWidthProperty().bind(primaryStage.widthProperty());
        primaryStage.setScene(new Scene(content, 600, 600));

        Router router = new PaneRouter(content);
        //Router router = new ConsoleRouter();
        Router.setApplicationRouter(router);

        Map map = new HashMap();

        ArrayList<Player> players = new ArrayList<>();

        //Creating test players
        players.add(new LocalPlayer("August", Color.WHITE));
        players.add(new NaturalAI("Björn", Color.BLACK));
        players.add(new NaturalAI("Dipshit", Color.RED));
        players.add(new NaturalAI("Nick", Color.PURPLE));
        players.add(new NaturalAI("Superman", Color.BLUE));
        players.add(new NaturalAI("Aqua Dude", Color.BROWN));

        //Converting to regular array.
        Player[] actingPlayers = new Player[players.size()];
        actingPlayers = players.toArray(actingPlayers);

        //Creating Match object and initiating view through a Router.
        GameMatch match = GameMatch.createGameMatch("Othello", actingPlayers, false);
        map.put("GameMatch", match);
        router.route("MainView", map);

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
