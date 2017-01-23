package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utilities.router.Router;
import utilities.router.paneRouter.PaneRouter;

import java.util.HashMap;

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

        PaneRouter router = new PaneRouter(content);
        //router.saveOnClose();
        Router.setApplicationRouter(router);

        //router.load();
        router.route("MainView", new HashMap());

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
