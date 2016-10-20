package utilities.router;

import utilities.HistoryTracker.History;
import utilities.callbackLibrary.ArrayCallback;
import utilities.callbackLibrary.Callback;
import utilities.router.exceptions.NoRouterSetException;
import utilities.router.paneRouter.PaneViewController;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Router {

    private static Router applicationRouter = null;

    /**
     * History object, part of the HistoryTracker package, is a memento like object
     * able to store and recall function calls. In part with this class the History
     * object is used to recall previous routes, thus enabling back and forward routing
     * functionality.
     */
    protected History history;

    public Router(){
        this.history = new History();
    }

    public void route(String viewId, Map data) {
        routeToView(viewId, data);

        Method method = null;
        for(Method m : this.getClass().getDeclaredMethods()){
            if(m.getName().contains("route"))
                method = m;
        }

        this.history.store(new ArrayCallback<>(method, this, new Object[]{viewId, data}));
    }

    public abstract void routeToView(String viewId, Map data);

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

    public static void setApplicationRouter(Router router) {
        Router.applicationRouter = router;
    }

    public static Router getApplicationRouter(){
        if(Router.applicationRouter == null)
            throw new NoRouterSetException();

        System.out.println("Router was not null.");
        System.out.println(Router.applicationRouter);

        return Router.applicationRouter;
    }
}
