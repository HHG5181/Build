package com.roots.app.mvp.utils;

import android.content.Context;
import android.content.Intent;

import com.amap.api.location.AMapLocation;
import com.roots.app.mvp.common.LocationService;

/**
 * 封装定位工具类
 */
public class LocationUtils {

    private static onLocationListener onLocationListener;

    public static void start(Context context, onLocationListener listener) {
        context.startService(new Intent(context, LocationService.class));
        onLocationListener = listener;
    }

    public static LocationUtils.onLocationListener getOnLocationListener() {
        return onLocationListener;
    }

    public static void setOnLocationListener(LocationUtils.onLocationListener onLocationListener) {
        LocationUtils.onLocationListener = onLocationListener;
    }

    public interface onLocationListener {
        void getData(AMapLocation aMapLocation);
    }
}
