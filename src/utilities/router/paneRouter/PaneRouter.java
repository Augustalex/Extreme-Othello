package utilities.router.paneRouter;

import utilities.callbackLibrary.ArrayCallback;
import javafx.scene.layout.Pane;
import utilities.callbackLibrary.Callback;
import utilities.HistoryTracker.History;
import utilities.router.Router;
import utilities.router.ViewController;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public class PaneRouter extends Router {

    /**
     * Container object into which views are loaded.
     */
    private Pane container;

    public PaneRouter(Pane container){
        this.container = container;
    }

    @Override
    public void routeToView(String viewId, Map data) {
        System.out.println("Route was called with: " + viewId + ", and a map: " + data.toString());

        PaneViewController viewController = PaneViewController.create(this.container, viewId, data);
        viewController.loadView();
    }


}
