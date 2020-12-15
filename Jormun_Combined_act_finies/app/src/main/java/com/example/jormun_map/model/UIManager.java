package com.example.jormun_map.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jormun_map.Config.CornerId;
import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.model.data_classes.UserManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UIManager {

    private Boolean bKeepUIUpadated;

    private MapsActivity mpsactCurrent;

    private Runnable rnbleUserPosition;
    private Runnable rnbleOtherUserPosition;
    private Runnable rnbleUIRefresher;

    private UIManager() {
        setRnbleUserPosition(new Runnable(){
            @Override
            public void run() {
                UpdateUserPos();
            }
        });
        setRnbleOtherUserPosition(new Runnable() {
            @Override
            public void run() {
                UpdateOtherUserPos();
            }
        });
    }

    private static class UIHolder {
        private static final UIManager uimgrInstance = new UIManager();
    }

    public static UIManager GetInstance(){
        return UIHolder.uimgrInstance;
    }
    public void Launch(Context cntCurrent){
        ScheduledExecutorService schdlexeservCurrent;

        SwitchSystem(true);
        schdlexeservCurrent = Executors.newScheduledThreadPool(2);
        setRnbleUIRefresher(new Runnable() {
            @Override
            public void run() {
                try{
                    Handler hndlrMainthread = new Handler(Looper.getMainLooper());
                    hndlrMainthread.post(getRnbleUserPosition());
                    hndlrMainthread.post(getRnbleOtherUserPosition());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        schdlexeservCurrent.scheduleAtFixedRate(getRnbleUIRefresher(),0,20, TimeUnit.SECONDS);
    }
    public void updateCorner(){

        CornerId[] a_crnidCorner = {CornerId.FL,CornerId.FR,CornerId.NL,CornerId.NR};

        UserManager usrmgrCurrent;

        LatLng[] a_ltlngCorners;

        a_ltlngCorners=GrabCorners();
        usrmgrCurrent=UserManager.getInstance();
        if(getmpsactCurrent().getmMap()!=null){
            for(int iIndex = 0 ; iIndex<a_ltlngCorners.length;iIndex++){
                grabCorners(a_crnidCorner[iIndex],a_ltlngCorners[iIndex],usrmgrCurrent);
            }
        }
    }
    public LatLng[] GrabCorners(){
        LatLng[] a_ltlngCorners = {getmpsactCurrent().getmMap().getProjection().getVisibleRegion().farLeft,getmpsactCurrent().getmMap().getProjection().getVisibleRegion().farRight,getmpsactCurrent().getmMap().getProjection().getVisibleRegion().nearLeft,getmpsactCurrent().getmMap().getProjection().getVisibleRegion().nearRight};

        return a_ltlngCorners;
    }
    public void grabCorners(CornerId crnidCurrent, LatLng ltlngCurrent,UserManager usrmgrCurrent){
        usrmgrCurrent.getBndView().setCornerPoint(CornerId.FL,ltlngCurrent.latitude,ltlngCurrent.longitude);
    }
    public void UpdateOtherUserPos(){

    }
    private void UpdateUserPos(){

        try {
            LatLng ltlngCurrent = new LatLng(UserManager.getInstance().getPntUserPos().getdLat(), UserManager.getInstance().getPntUserPos().getdLong());
            getmpsactCurrent().getmMap().moveCamera(CameraUpdateFactory.newLatLng(ltlngCurrent));
            getmpsactCurrent().getmMap().setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(ltlngCurrent.latitude-0.05,ltlngCurrent.longitude-0.05),new LatLng(ltlngCurrent.latitude+0.05,ltlngCurrent.longitude+0.05)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SwitchSystem(boolean bValue){
        setbKeepUIUpadated(bValue);
    }
    public MapsActivity getmpsactCurrent() {
        return mpsactCurrent;
    }

    public Runnable getRnbleUserPosition() {
        return rnbleUserPosition;
    }

    public Runnable getRnbleOtherUserPosition() {
        return rnbleOtherUserPosition;
    }

    public Runnable getRnbleUIRefresher() {
        return rnbleUIRefresher;
    }

    public Boolean getbKeepUIUpadated() {
        return bKeepUIUpadated;
    }

    public void setmpsactCurrent(MapsActivity mMap) {
        this.mpsactCurrent = mMap;
    }

    public void setRnbleUserPosition(Runnable rnbleUserPosition) {
        this.rnbleUserPosition = rnbleUserPosition;
    }

    public void setRnbleUIRefresher(Runnable rnbleUIRefresher) {
        this.rnbleUIRefresher = rnbleUIRefresher;
    }

    public void setRnbleOtherUserPosition(Runnable rnbleOtherUserPosition) {
        this.rnbleOtherUserPosition = rnbleOtherUserPosition;
    }

    public void setbKeepUIUpadated(Boolean bKeepUIUpadated) {
        this.bKeepUIUpadated = bKeepUIUpadated;
    }

}
