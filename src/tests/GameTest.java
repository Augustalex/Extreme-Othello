package tests;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import router.PaneRouter;
import router.Router;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-10-11.
 */
public class GameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        StackPane content = new StackPane();
        pane.setCenter(content);
        pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        content.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setScene(new Scene(pane, 600, 600));

        Router router = new PaneRouter(content);
        Router.setApplicationRouter(router);
        Map map = new HashMap();
        map.put("GameMatch", null);
        router.route("GameView", map);

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
