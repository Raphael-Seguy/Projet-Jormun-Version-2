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

import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.R;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;

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


        vwLauncher = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_launcher);
        if (getShrdprefSave().contains("username")) {
            setBtnLnchrChange(vwLauncher.findViewById(R.id.btnlnchrChange));
            setBtnLnchrPlay(vwLauncher.findViewById(R.id.btnLnchrPlay));

            getBtnLnchrChange().setOnClickListener(vwCurrent -> {
                Popup popupconConnexion;

                popupconConnexion = new ConnectionPopup(super.getExtdbmgrApiServer(), super.getIntdbmgrStorage(), super.getFrgmgrController(), super.getMpsactCarte(), super.getShrdprefSave());
                switchPopup("Start", getFrgmgrController(), popupconConnexion);
            });
        } else {
            popupconInitial = new InitialPopup(super.getExtdbmgrApiServer(), super.getIntdbmgrStorage(), super.getFrgmgrController(), super.getMpsactCarte(), super.getShrdprefSave());
            switchPopup("Restart", getFrgmgrController(), popupconInitial);
        }

        return vwLauncher;
    }


    public Button getBtnLnchrPlay() {
        return btnLnchrPlay;
    }

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
