package com.example.jormun_map.popup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.jormun_map.Config.SharedPrefKeys;
import com.example.jormun_map.Config.TablesName;
import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.R;
import com.example.jormun_map.client.PositionServerClient;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;

import java.util.concurrent.atomic.AtomicReference;

public class LauncherPopup extends Popup {
    Button btnLnchrPlay;

    Button btnLnchrChange;

    public LauncherPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        super(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwLauncher;

        Popup popupconInitial;

        AtomicReference<SharedPreferences.Editor> shrdprefeditScribble = null;

        vwLauncher = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_launcher);
        if (getShrdprefSave().contains(SharedPrefKeys.USERNAMEKEY.toString())) {
            setBtnLnchrChange(vwLauncher.findViewById(R.id.btnlnchrChange));
            setBtnLnchrPlay(vwLauncher.findViewById(R.id.btnLnchrPlay));

            getBtnLnchrChange().setOnClickListener(vwCurrent -> {
                Popup popupconConnexion;

                popupconConnexion = new ConnectionPopup(super.getExtdbmgrApiServer(), super.getIntdbmgrStorage(), super.getFrgmgrController(), super.getMpsactCarte(), super.getShrdprefSave());
                DropPreviousRecord();
                switchPopup("Start", getFrgmgrController(), popupconConnexion);
            });
            getBtnLnchrPlay().setOnClickListener(vwCurrent -> {
                PositionServerClient posservcltCurrent;
                getExtdbmgrApiServer().FetchComplexFile(TablesName.TABLE_HERO,getShrdprefSave().getAll().get(SharedPrefKeys.USERNAMEKEY.toString()).toString());
                posservcltCurrent = PositionServerClient.getInstance();
                posservcltCurrent.setShrdprefSetting(getShrdprefSave());
                posservcltCurrent.connect();
                getIntdbmgrStorage().InsertIntoUserInfo(getShrdprefSave().getAll().get(SharedPrefKeys.USERNAMEKEY.toString()).toString(),getShrdprefSave().getAll().get(SharedPrefKeys.POSITIONLOGIN.toString()).toString());
                get
                getMpsactCarte().ClearScreen();
            });
        } else {
            popupconInitial = new InitialPopup(super.getExtdbmgrApiServer(), super.getIntdbmgrStorage(), super.getFrgmgrController(), super.getMpsactCarte(), super.getShrdprefSave());
            switchPopup("Restart", getFrgmgrController(), popupconInitial);
        }

        return vwLauncher;
    }
    public void DropPreviousRecord(){
        SharedPreferences.Editor shrdprefeditScribble;

        shrdprefeditScribble = getShrdprefSave().edit();
        shrdprefeditScribble.remove(SharedPrefKeys.USERNAMEKEY.toString());
        shrdprefeditScribble.remove(SharedPrefKeys.POSITIONKEY.toString());
        shrdprefeditScribble.remove(SharedPrefKeys.POSITIONLOGIN.toString());
        shrdprefeditScribble.apply();
    }

    public Button getBtnLnchrPlay() {return btnLnchrPlay;    }

    public void setBtnLnchrPlay(Button btnLnchrPlay) {
        this.btnLnchrPlay = btnLnchrPlay;
    }

    public Button getBtnLnchrChange() {
        return btnLnchrChange;
    }

    public void setBtnLnchrChange(Button btnLnchrChange) {
        this.btnLnchrChange = btnLnchrChange;
    }

}
