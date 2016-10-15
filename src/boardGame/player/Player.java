package boardGame.player;

import events.callbackLibrary.Callback;
import events.callbackLibrary.CallbackMethod;
import events.callbackLibrary.MapCallback;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player implements CallbackMethod<MapCallback>{

    protected MapCallback callback;

    public abstract void makeMove();

    @Override
    public void setCallback(MapCallback callback){
        this.callback = callback;
    }

}
