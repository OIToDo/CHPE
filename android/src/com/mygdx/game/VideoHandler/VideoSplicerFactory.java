package com.mygdx.game.VideoHandler;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.webkit.URLUtil;

import com.mygdx.game.Exceptions.InvalidVideoSplicerType;

import java.util.HashMap;


public class VideoSplicerFactory {


    public static VideoSplicer getVideoSplicer(String m) throws InvalidVideoSplicerType {
        if (URLUtil.isValidUrl(m)) {

            if (android.os.Build.VERSION.SDK_INT > 28) {
                // Function call getFrameAtIndex based on frame count requires
                // only for Pie (28) and newer versions
                return new VideoSplicerUri(m);
            }
            return new VideoSplicerUriLegacy(m);
        }
        // Assuming we aren't talking about a Uri instance
        throw new InvalidVideoSplicerType("Factory is running on invalid string",
                new Throwable("Invalid splicer type: " + m));
    }

    public static VideoSplicer getVideoSplicer(Uri uri, Context context) throws InvalidVideoSplicerType {
        if (android.os.Build.VERSION.SDK_INT > 28) {
            // Function call getFrameAtIndex based on frame count requires
            // only for Pie (28) and newer versions
            return new VideoSplicerUri(uri, context);
        }

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            return new VideoSplicerUriLegacy(uri, context);
        }
        throw new InvalidVideoSplicerType("Factory is running on invalid configuration",
                new Throwable("Android SDK version too low, minimum of 24"));
    }

    public static VideoSplicer getVideoSplicer(String path, boolean t) {
        return new VideoSplicerUriLegacy(path, new HashMap<String, String>());

    }


}
