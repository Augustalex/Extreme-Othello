package utilities.router;

import utilities.router.exceptions.NoRouterSetException;
import utilities.router.routerState.RouterState;
import utilities.router.routerState.StateHistory;
import utilities.router.stateRestorer.StateLoader;
import utilities.router.stateRestorer.StateWriter;
import utilities.router.stateRestorer.exceptions.UnableToLoadState;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Router implements Serializable{

    private static Router applicationRouter = null;

    protected StateHistory<RouterState> stateHistory;

    public Router(){
        this.stateHistory = new StateHistory<>();
    }

    public void route(String viewId, Map data) {
        routeToView(viewId, data);

        this.stateHistory.store(new RouterState(viewId, data));
    }

    public void route(RouterState state){
        this.route(state.getViewId(), state.getDependencies());
    }

    public abstract void routeToView(String viewId, Map data);

    public void previous() {
        this.stateHistory.previous();
        this.route(this.stateHistory.getCurrent());

    }

    public void next() {
        this.stateHistory.next();
        this.route(this.stateHistory.getCurrent());
    }

    public void reload(){
        this.route(this.stateHistory.getCurrent());
    }

    public void save(){
        this.stateHistory.saveState();
    }

    public void load() throws UnableToLoadState {
        try{
            this.stateHistory = this.stateHistory.loadState();
            RouterState state = this.stateHistory.getCurrent();
            this.routeToView(state.getViewId(), state.getDependencies());
        }
        catch(Exception e){
            e.printStackTrace();
            throw new UnableToLoadState();
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

    public abstract void saveOnClose();

}
