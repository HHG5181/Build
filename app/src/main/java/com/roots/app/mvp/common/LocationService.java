package com.roots.app.mvp.common;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.roots.app.mvp.utils.LocationUtils;

public class LocationService extends Service {

    private AMapLocationClient mClient = null;
    private AMapLocationClientOption mOption = null;

    public AMapLocationListener mListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    LocationUtils.getOnLocationListener().getData(aMapLocation);
                    mClient.stopLocation();
                } else {
                    Log.e("AmapError", "loc ation Error.ErrCode:"
                            + aMapLocation.getErrorCode() + ".errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    public void onCreate() {
        mClient = new AMapLocationClient(getApplicationContext());
        initLocation();
        mClient.setLocationListener(mListener);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mClient.startLocation();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mClient.stopLocation();
        super.onDestroy();
    }

    private void initLocation() {
        mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mOption.setNeedAddress(true);
        mOption.setInterval(10000);
        mOption.setKillProcess(true);
        mClient.setLocationOption(mOption);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

