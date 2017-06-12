import com.cxmax.android_clean_architecture.BuildConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/13.
 */

public class ColdStart {

    static final String APP_COLD_START = "ColdStart";
    static final List<String> loggedKey = new LinkedList<>();

    static ColdStartLogger timingLogger = new ColdStartLogger(APP_COLD_START, APP_COLD_START);;

    public static void appStart() {
        if(BuildConfig.DEBUG) {
//            Debug.startMethodTracing(APP_COLD_START);
            timingLogger.addSplit("App Start");
        }
    }

    public static void logColdStart(String key) {
        synchronized (loggedKey) {
            if(BuildConfig.DEBUG) {
                if (!loggedKey.contains(key)) {
                    loggedKey.add(key);
                    timingLogger.addSplit(key);
                }
            }
        }
    }

    private static volatile boolean dumped = false;
    public static void dump() {
        if(BuildConfig.DEBUG && !dumped) {
            dumped = true;
//            Debug.stopMethodTracing();
            timingLogger.dumpToLog();
        }
    }
    
}
