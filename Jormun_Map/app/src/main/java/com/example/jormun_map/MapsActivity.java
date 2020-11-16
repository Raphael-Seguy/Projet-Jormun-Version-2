package com.example.jormun_map;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;
import com.example.jormun_map.popup.Popup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Static for Session
    public static final String sSESSION = "session";
    public static final String sUSERNAMEKEY = "username";
    public static final String sPOSITIONKEY = "token";
    private GoogleMap mMap;
    private SharedPreferences shrdprefSave;
    private FragmentManager fragmntmgrManager;

    public MapsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Popup popupCurrent;

        setContentView(R.layout.activity_maps);
        setShrdprefSave(getSharedPreferences(sSESSION, Context.MODE_PRIVATE));
        if (getShrdprefSave().contains(sUSERNAMEKEY)) {
            System.out.println();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setFragmntmgrManager(getSupportFragmentManager());
        popupCurrent = Popup.initiateStartingPopup(ExternalDbManager.getInstance(), new InternalDbManager(), getFragmntmgrManager(), this, getShrdprefSave());
        Popup.switchPopup("Initial", getFragmntmgrManager(), popupCurrent);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        setmMap(googleMap);
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.style_json));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        getmMap().addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        getmMap().moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void SafeAppClosure() {
        ClearScreen();
    }

    //Event Handling
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onPause() {
        SafeAppClosure();
        super.onPause();
    }

    @Override
    protected void onStop() {
        SafeAppClosure();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void ClearScreen() {
        getSupportFragmentManager().popBackStackImmediate("Initial", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public SharedPreferences getShrdprefSave() {
        return shrdprefSave;
    }

    public void setShrdprefSave(SharedPreferences shrdprefSave) {
        this.shrdprefSave = shrdprefSave;
    }

    public FragmentManager getFragmntmgrManager() {
        return fragmntmgrManager;
    }

    public void setFragmntmgrManager(FragmentManager fragmntmgrManager) {
        this.fragmntmgrManager = fragmntmgrManager;
    }
}