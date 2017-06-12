import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/13.
 */

public class ColdStartLogger {

    private String tag;

    private String label;

    ArrayList<Long> splits;

    ArrayList<String> splitLabels;

    public ColdStartLogger(String tag, String label) {
        reset(tag, label);
    }

    public void reset(String tag, String label) {
        this.tag = tag;
        this.label = label;
        reset();
    }

    public void reset() {
        if (splits == null) {
            splits = new ArrayList<Long>();
            splitLabels = new ArrayList<String>();
        } else {
            splits.clear();
            splitLabels.clear();
        }
        addSplit(null);
    }

    public void addSplit(String splitLabel) {
        long now = SystemClock.elapsedRealtime();
        splits.add(now);
        splitLabels.add(splitLabel);
    }

    public void dumpToLog() {
        Log.d(tag, label + ": begin");
        final long first = splits.get(0);
        long now = first;
        for (int i = 1; i < splits.size(); i++) {
            now = splits.get(i);
            final String splitLabel = splitLabels.get(i);
            final long prev = splits.get(i - 1);

            Log.d(tag, label + ":      " + (now - prev) + " ms, " + splitLabel);
        }
        Log.d(tag, label + ": end, " + (now - first) + " ms");
    }
}
