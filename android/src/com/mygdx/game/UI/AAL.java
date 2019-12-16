package com.mygdx.game.UI;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DebugLog;

import java.util.ArrayList;

class AAL {

    static void setTitleBar(Window window) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(182, 255, 255));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0);
        }
    }

    static void requestPermissions(Context context, Activity activity, String[] permissions) {
        ArrayList<String> deniedPermissions = new ArrayList<>();
        for(String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                DebugLog.log("logger: " + permission + " granted");
            } else {
                deniedPermissions.add(permission);
                DebugLog.log("logger: " + permission + " denied");
            }
        }
        String[] arr = new String[deniedPermissions.size()];
        arr = deniedPermissions.toArray(arr);
        DebugLog.log("logger: requesting permissions for: ");
        for(String pm : deniedPermissions) DebugLog.log("logger: " + pm);
        ActivityCompat.requestPermissions(activity, arr, 1);
    }
}
