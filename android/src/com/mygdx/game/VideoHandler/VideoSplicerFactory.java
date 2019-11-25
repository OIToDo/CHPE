package com.mygdx.game.VideoHandler;

import android.content.Context;
import android.net.Uri;
import android.webkit.URLUtil;

import com.mygdx.game.Exceptions.InvalidVideoSplicerType;

import java.io.InputStream;

public class VideoSplicerFactory {

    public static VideoSplicer getVideoSplicer(String m) throws InvalidVideoSplicerType {
        if (URLUtil.isValidUrl(m)) {
            if (android.os.Build.VERSION.SDK_INT >= 28) {
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
            if (android.os.Build.VERSION.SDK_INT >= 28) {
                // Function call getFrameAtIndex based on frame count requires
                // only for Pie (28) and newer versions
                return new VideoSplicerUri(uri, context);
            }
            return new VideoSplicerUriLegacy(uri, context);
    }

    public static VideoSplicer getVideoSplicer(InputStream inputStream) {
        return new VideoSplicerInputStream(inputStream);
    }


}
