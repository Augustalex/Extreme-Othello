package router;

import router.exceptions.NoRouterSetException;

import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Router {

    private static Router applicationRouter = null;

    public abstract void route(String viewId, Map data);

    public abstract void previous();

    public abstract void next();

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
