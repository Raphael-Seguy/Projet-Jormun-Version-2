package com.example.jormun_map.popup;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.R;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;

public class Popup extends Fragment {
    private ExternalDbManager extdbmgrApiServer;
    private InternalDbManager intdbmgrStorage;
    private FragmentManager frgmgrController;
    private MapsActivity mpsactCarte;
    private SharedPreferences shrdprefSave;

    public Popup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        setExtdbmgrApiServer(extdbmgrWebsiteAPI);
        setIntdbmgrStorage(intdbmrgStorage);
        setFrgmgrController(frgmgrLoader);
        setMpsactCarte(mpsactCurrent);
        setShrdprefSave(shrdprefSession);
    }

    public static void switchPopup(String sSaveStateName, FragmentManager fragmntCurrent, Popup popupNextState) {
        FragmentTransaction frgmttransManager = fragmntCurrent.beginTransaction();
        frgmttransManager.addToBackStack(sSaveStateName);
        frgmttransManager.replace(R.id.map, popupNextState, null);
        frgmttransManager.commit();
    }

    public static Popup initiateStartingPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        if (extdbmgrWebsiteAPI.bIsAccessible) {

            if (shrdprefSession.contains(MapsActivity.sUSERNAMEKEY)) {
                return new LauncherPopup(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
            } else {
                return new InitialPopup(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
            }
        } else {
            return new ErrorPopup(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
        }
    }

    public View InflateCorrespondingView(LayoutInflater Inflater, ViewGroup container, Boolean bLinkToRoot, @LayoutRes int lyoutresPopup) {
        return Inflater.inflate(lyoutresPopup, container, bLinkToRoot);
    }

    public int[] CheckInput() {
        return null;
    }

    public ExternalDbManager getExtdbmgrApiServer() {
        return extdbmgrApiServer;
    }

    public void setExtdbmgrApiServer(ExternalDbManager extdbmgrApiServer) {
        this.extdbmgrApiServer = extdbmgrApiServer;
    }

    public InternalDbManager getIntdbmgrStorage() {
        return intdbmgrStorage;
    }

    public void setIntdbmgrStorage(InternalDbManager intdbmgrStorage) {
        this.intdbmgrStorage = intdbmgrStorage;
    }

    public FragmentManager getFrgmgrController() {
        return frgmgrController;
    }

    public void setFrgmgrController(FragmentManager frgmgrController) {
        this.frgmgrController = frgmgrController;
    }

    public MapsActivity getMpsactCarte() {
        return mpsactCarte;
    }

    public void setMpsactCarte(MapsActivity mpsactCarte) {
        this.mpsactCarte = mpsactCarte;
    }

    public SharedPreferences getShrdprefSave() {
        return shrdprefSave;
    }

    public void setShrdprefSave(SharedPreferences shrdprefSave) {
        this.shrdprefSave = shrdprefSave;
    }
}
