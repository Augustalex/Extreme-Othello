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

    /**
     * History object, part of the HistoryTracker package, is a memento like object
     * able to store and recall function calls. In part with this class the History
     * object is used to recall previous routes, thus enabling back and forward routing
     * functionality.
     */
    private History history;

    public PaneRouter(Pane container){
        this.history = new History();
        this.container = container;
    }

    @Override
    public void route(String viewId, Map data) {
        System.out.println("Route was called with: " + viewId + ", and a map: " + data.toString());
        PaneViewController viewController = PaneViewController.create(this.container, viewId, data);
        viewController.loadView();

        Method method = null;
        for(Method m : this.getClass().getDeclaredMethods()){
            if(m.getName().contains("route"))
                method = m;
        }

        this.history.store(new ArrayCallback<>(method, this, new Object[]{viewId, data}));
    }

    @Override
    public void previous() {
        this.history.revertTo(this.history.getCursorPosition()-1);
        Callback call = this.history.get();

        try{
            call.invoke();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void next() {
        this.history.revertTo(this.history.getCursorPosition()+1);
        Callback call = this.history.get();

        try {
            call.invoke();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
