package utilities.router;

import boardGameLibrary.boardGame.match.LocalGameMatch;
import javafx.scene.layout.Pane;
import boardGameLibrary.views.javaFxViews.newGame.NewGameViewController;
import boardGameLibrary.views.javaFxViews.gameView.GameViewController;
import boardGameLibrary.views.javaFxViews.mainMenu.MainViewController;

import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public interface ViewController {

    static ViewController createViewController(String viewId, Map dependencies){
        if(dependencies == null)
            throw new IllegalArgumentException();

        if(dependencies.containsKey("LocalGameMatch")) {
            if (viewId.equals("GameView"))
                return new GameViewController((LocalGameMatch) dependencies.get("LocalGameMatch"));
        }

        if(viewId.equals("NewGameView"))
            return new NewGameViewController();
        else if(viewId.equals("MainView"))
            return new MainViewController();


        throw new IllegalArgumentException("Cannot create a view from incorrect view id and dependencies.");
    }

    void loadViewInto(Pane container);
}
