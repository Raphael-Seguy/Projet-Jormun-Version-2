package com.example.jormun_map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.jormun_map.Config.SharedPrefKeys;
import com.example.jormun_map.Temp.Activite_Combat;
import com.example.jormun_map.Temp.MainActivity;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.FileManager;
import com.example.jormun_map.model.InternalDbManager;
import com.example.jormun_map.model.UIManager;
import com.example.jormun_map.model.UserPositionManager;
import com.example.jormun_map.model.data_classes.UserManager;
import com.example.jormun_map.popup.Popup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Static for Session

    private GoogleMap mMap;
    private SharedPreferences shrdprefSave;
    private FragmentManager fragmntmgrManager;

    private UserManager usrmgrCurrent;

    public MapsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserPositionManager usrposmgrCurrent;
        setContentView(R.layout.activity_maps);
        setShrdprefSave(getSharedPreferences(SharedPrefKeys.SESSION.toString(), Context.MODE_PRIVATE));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setFragmntmgrManager(getSupportFragmentManager());
        setUsrmgrCurrent(UserManager.getInstance());
        usrposmgrCurrent = UserPositionManager.getInstance();
        usrposmgrCurrent.setmpsactCurrent(this);
        usrposmgrCurrent.launch(getBaseContext());
        FileManager.getInstance().setCtxtMain(getBaseContext());

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
        Popup popupCurrent;

        UIManager uimgrUIUpdater;

        InternalDbManager.getInstance().CreateDatabase(getBaseContext());
        popupCurrent = Popup.initiateStartingPopup(ExternalDbManager.getInstance(), InternalDbManager.getInstance(), getFragmntmgrManager(), this, getShrdprefSave());
        Popup.switchPopup("Initial", getFragmntmgrManager(), popupCurrent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LocationManager locmgrCurrent = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
            }
        }
        setmMap(googleMap);
        getmMap().setMaxZoomPreference(18);
        getmMap().setMinZoomPreference(18);


        uimgrUIUpdater = UIManager.GetInstance();
        uimgrUIUpdater.setmpsactCurrent(this);
        uimgrUIUpdater.Launch(getBaseContext());
        boolean success = getmMap().setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.style_json));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng( getUsrmgrCurrent().getPntUserPos().getdLat(),getUsrmgrCurrent().getPntUserPos().getdLong());
        getmMap().addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        FileManager.getInstance().Initialize(getBaseContext());
    }
    public void GiveUpFocus(){
        ClearScreen();
    }
    public void SafeAppClosure() {
        int iCount;

        do{
            iCount = getSupportFragmentManager().getBackStackEntryCount();
        }while(iCount>0);
    }

    //Event Handling
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onPause() {
        Toast.makeText(MapsActivity.this, "Going to nap", Toast.LENGTH_LONG).show();
        SafeAppClosure();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(MapsActivity.this, "Going to bed", Toast.LENGTH_LONG).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Popup popupCurrent;
        switch (requestCode) {
            case 111:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MapsActivity.this, "You agreed to using the gps", Toast.LENGTH_LONG).show();
                    setShrdprefSave(getSharedPreferences(SharedPrefKeys.SESSION.toString(), MODE_PRIVATE));
                    popupCurrent = Popup.initiateStartingPopup(ExternalDbManager.getInstance(), InternalDbManager.getInstance(), getFragmntmgrManager(), this, getShrdprefSave());
                    Popup.switchPopup("Initial", getFragmntmgrManager(), popupCurrent);
                }
        }
    }

    public void ClearScreen() {
        getSupportFragmentManager().popBackStackImmediate("Initial", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public UserManager getUsrmgrCurrent() {
        return usrmgrCurrent;
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

    public void setUsrmgrCurrent(UserManager usrmgrCurrent) {
        this.usrmgrCurrent = usrmgrCurrent;
    }

    public void OpenBase(View view) {
        MainActivity mnactCurrent;

        ClearScreen();
        startActivity(new Intent(MapsActivity.this, Activite_Combat.class));
    }
}