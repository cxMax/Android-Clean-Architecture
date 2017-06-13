import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/14.
 */

public class Foreground implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = Foreground.class.getName();

    public interface Listener {
        public void onBecameForeground();
        public void onBecameBackground();
    }

    public interface Binding {
        public void unbind();
    }

    private interface Callback {
        public void invoke(Listener listener);
    }

    private static class Listeners {

        private List<WeakReference<Listener>> listeners = new CopyOnWriteArrayList<>();

        public Binding add(Listener listener){
            final WeakReference<Listener> wr = new WeakReference<>(listener);
            listeners.add(wr);
            return new Binding(){
                public void unbind() {
                    listeners.remove(wr);
                }
            };
        }

        public void each(Callback callback){
            List<WeakReference<Listener>> toBeRemoved = new LinkedList<>();
            for (Iterator<WeakReference<Listener>> it = listeners.iterator(); it.hasNext();) {
                WeakReference<Listener> wr = it.next();
                Listener l = wr.get();
                if (l != null) {
                    callback.invoke(l);
                } else {
                    toBeRemoved.add(wr);
                }
            }

            if (toBeRemoved.size() > 0) {
                listeners.removeAll(toBeRemoved);
            }
        }
    }

    private static Callback becameForeground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameForeground();
        }
    };

    private static Callback becameBackground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameBackground();
        }
    };

    private static Foreground instance;

    private int counter = 0;
    private boolean counterFix = false;
    private Listeners listeners = new Listeners();

    private Activity foregroundActivity;

    public static Foreground init(Application application){
        if (instance == null) {
            instance = new Foreground();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static Foreground get(Application application){
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static Foreground get(){
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - first invocation must use parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground(){
        return counter > 0;
    }

    public boolean isBackground(){
        return counter <= 0;
    }

    public Binding addListener(Listener listener){
        return listeners.add(listener);
    }

    private void increaseCounter() {
        if (isBackground()) {
            Log.w(TAG, "became foreground");
            counter = 1;
            listeners.each(becameForeground);
        } else {
            Log.i(TAG, "still foreground");
            counter ++;
        }
    }

    private void decreaseCounter() {
        if (counter > 0) {
            counter --;
        }
        if (counter <= 0) {
            Log.w(TAG, "went background");
            listeners.each(becameBackground);
            foregroundActivity = null;
        }
    }

    public Activity activity() {
        return foregroundActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        // if configuration is changing, ignore this counter change.
        if (counterFix) {
            counterFix = false;
        } else {
            increaseCounter();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        foregroundActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity != null && !activity.isChangingConfigurations()) {
            decreaseCounter();
        } else {
            counterFix = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
