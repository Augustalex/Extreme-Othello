package utilities.router;

import utilities.callbackLibrary.ArrayCallback;
import javafx.scene.layout.Pane;
import utilities.callbackLibrary.Callback;
import utilities.HistoryTracker.History;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public class PaneRouter extends Router{

    private Pane container;

    private History history;

    public PaneRouter(Pane container){
        this.history = new History();
        this.container = container;
    }

    @Override
    public void route(String viewId, Map data) {
        System.out.println("Route was called with: " + viewId + ", and a map: " + data.toString());
        ViewController viewController = ViewController.createViewController(viewId, data);
        viewController.loadViewInto(this.container);

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
