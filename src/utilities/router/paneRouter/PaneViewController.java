package utilities.router.paneRouter;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.views.javaFxViews.gameView.GameViewController;
import boardGameLibrary.views.javaFxViews.mainMenu.MainViewController;
import boardGameLibrary.views.javaFxViews.newGame.NewGameViewController;
import javafx.scene.layout.Pane;
import utilities.router.ViewController;

import java.util.Map;

/**
 * Created by August on 2016-10-19.
 */
public abstract class PaneViewController implements ViewController {

    private Pane container = null;

    public static PaneViewController create(Pane container, String viewId, Map dependencies){
        if(dependencies == null)
            throw new IllegalArgumentException();

        if(dependencies.containsKey("GameMatch")) {
            if (viewId.equals("GameView"))
                return new GameViewController(container, (GameMatch) dependencies.get("GameMatch"));
        }

        if(viewId.equals("NewGameView"))
            return new NewGameViewController(container);
        else if(viewId.equals("MainView"))
            return new MainViewController(container);

        throw new IllegalArgumentException("Cannot create a view from incorrect view id and dependencies.");
    }

    public void setContainer(Pane container){
        this.container = container;
    }

    protected Pane getContainer(){
        return this.container;
    }
}
