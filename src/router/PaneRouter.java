package router;

import javafx.scene.layout.Pane;
import HistoryTracker.HistoryCall;
import HistoryTracker.History;
import views.ViewController;

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

        Object context = this;

        Object[] arguments = new Object[2];
        arguments[0] = viewId;
        arguments[1] = data;

        this.history.store(new HistoryCall(method, context, arguments));
    }

    @Override
    public void previous() {
        this.history.revertTo(this.history.getCursorPosition()-1);
        HistoryCall call = this.history.get();

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
        HistoryCall call = this.history.get();

        try {
            call.invoke();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
