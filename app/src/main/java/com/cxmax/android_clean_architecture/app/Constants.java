package com.cxmax.android_clean_architecture.app;

import android.os.Environment;

import java.io.File;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class Constants {
    //================= TYPE ====================
    public static final int TYPE_INDEX = 101;
    public static final int TYPE_SETTING = 102;
    public static final int TYPE_ABOUT = 103;

    //================= FILE NAME ====================
    public static final String NIGHT_MODE_FILE = "night.mode.file";
    public static final String SP_CURRENT_ITEM = "sp.current.item";

    //================= PREFERENCE ====================
    public static final String SP_NIGHT_MODE = "night_mode";

    //================= ACTION ====================
    public static final String ACTION_ALARM_MANAGER = "action_alarm_manager";
    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

}
