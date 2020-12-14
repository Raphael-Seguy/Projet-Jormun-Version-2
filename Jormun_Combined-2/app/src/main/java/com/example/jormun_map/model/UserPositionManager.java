package com.example.jormun_map.model;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.model.data_classes.Point;
import com.example.jormun_map.model.data_classes.UserManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserPositionManager{

    public boolean bFlag;

    public LocationManager locmgrCurrent;

    public LocationListener loclstnCurrent;

    public ScheduledExecutorService schdldexesrvExecutor;

    public Runnable rnbleRefreshPosition;

    public MapsActivity mpsactCurrent;

    public Context ctxtReference;

    public ActivityCompat actcmptReference;

    private UserPositionManager() {
        setLoclstnCurrent(new MyLocationListener());
        setbFlag(false);
        setSchdldexesrvExecutor(Executors.newScheduledThreadPool(2));
        setRnbleRefreshPosition(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                getCoord();
                Looper.loop();
            }
        });
    }

    private static class UserPositionHolder {
        private static final UserPositionManager usrposmgrInstance = new UserPositionManager();
    }

    public static UserPositionManager getInstance() {
        return UserPositionHolder.usrposmgrInstance;
    }

    public void launch(Context ctxtCurrent) {
        setLocmgrCurrent((LocationManager)(ctxtCurrent.getSystemService(Context.LOCATION_SERVICE)));
        setCtxtReference(ctxtCurrent);
        getSchdldexesrvExecutor().scheduleAtFixedRate(getRnbleRefreshPosition(), 0, 33, TimeUnit.SECONDS);


    }

    public void getCoord() {

        setbFlag(displayGpsStatus());
        if (isbFlag()) {

            if (ActivityCompat.checkSelfPermission(getCtxtReference(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getCtxtReference(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            try{
                getLocmgrCurrent().requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 5000, 10, getLoclstnCurrent());
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getCtxtReference()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    public Context getCtxtReference() {
        return ctxtReference;
    }

    public LocationListener getLoclstnCurrent() {
        return loclstnCurrent;
    }

    public LocationManager getLocmgrCurrent() {
        return locmgrCurrent;
    }

    public boolean isbFlag() {
        return bFlag;
    }

    public MapsActivity getmpsactCurrent() {
        return mpsactCurrent;
    }

    public ActivityCompat getActcmptReference() {
        return actcmptReference;
    }

    public ScheduledExecutorService getSchdldexesrvExecutor() {
        return schdldexesrvExecutor;
    }

    public Runnable getRnbleRefreshPosition() {
        return rnbleRefreshPosition;
    }

    public void setmpsactCurrent(MapsActivity mpsactCurrent) {
        this.mpsactCurrent = mpsactCurrent;
    }

    public void setCtxtReference(Context ctxtReference) {
        this.ctxtReference = ctxtReference;
    }

    public void setSchdldexesrvExecutor(ScheduledExecutorService schdldexesrvExecutor) {
        this.schdldexesrvExecutor = schdldexesrvExecutor;
    }

    public void setRnbleRefreshPosition(Runnable rnbleRefreshPosition) {
        this.rnbleRefreshPosition = rnbleRefreshPosition;
    }

    public void setLocmgrCurrent(LocationManager locmgrCurrent) {
        this.locmgrCurrent = locmgrCurrent;
    }

    public void setActcmptReference(ActivityCompat actcmptReference) {
        this.actcmptReference = actcmptReference;
    }

    public void setbFlag(boolean bFlag) {
        this.bFlag = bFlag;
    }

    public void setLoclstnCurrent(LocationListener loclstnCurrent) {
        this.loclstnCurrent = loclstnCurrent;
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            System.out.println("Retrieve the User Manager");
            UserManager usrmgrCurrent = UserManager.getInstance();
            System.out.println("Set the new position of the User");
            usrmgrCurrent.setPntUserPos(new Point(loc.getLatitude(),loc.getLongitude()));
            UIManager.GetInstance().updateCorner();
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}
