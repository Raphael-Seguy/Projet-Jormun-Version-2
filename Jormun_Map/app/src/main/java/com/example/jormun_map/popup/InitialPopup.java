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

public class InitialPopup extends Popup {

    private Button btnInitPlay;
    private Button btnInitConnect;
    private Button btnInitSubsribe;
    private Button btnInitLeave;

    public InitialPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        super(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwInitialPopup;

        vwInitialPopup = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_initial);

        setBtnInitConnect(vwInitialPopup.findViewById(R.id.btnInitConnect));
        setBtnInitLeave(vwInitialPopup.findViewById(R.id.btnInitLeave));
        setBtnInitPlay(vwInitialPopup.findViewById(R.id.btnInitPlay));
        setBtnInitSubsribe(vwInitialPopup.findViewById(R.id.btnInitSubscribe));

        getBtnInitConnect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialConnectClicked(vwCurrent);
            }
        });
        getBtnInitLeave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                System.exit(0);
            }
        });
        getBtnInitPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialPlayClicked(vwCurrent);
            }
        });
        getBtnInitSubsribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialSubscribeClicked(vwCurrent);
            }
        });
        return vwInitialPopup;
    }

    public void InitialSubscribeClicked(View vwCurrent) {
        Popup popupNext;

        popupNext = new SubscriptionPopup(getExtdbmgrApiServer(), getIntdbmgrStorage(), getFrgmgrController(), getMpsactCarte(), getShrdprefSave());
        Popup.switchPopup("Subscribe", getFrgmgrController(), popupNext);
    }

    public void InitialPlayClicked(View vwCurrent) {

    }

    public void InitialConnectClicked(View vwCurrent) {
        Popup popupNext;

        popupNext = new ConnectionPopup(getExtdbmgrApiServer(), getIntdbmgrStorage(), getFrgmgrController(), getMpsactCarte(), getShrdprefSave());
        Popup.switchPopup("Connect", getFrgmgrController(), popupNext);
    }

    public Button getBtnInitPlay() {
        return btnInitPlay;
    }

    public void setBtnInitPlay(Button btnInitPlay) {
        this.btnInitPlay = btnInitPlay;
    }

    public Button getBtnInitConnect() {
        return btnInitConnect;
    }

    public void setBtnInitConnect(Button btnInitConnect) {
        this.btnInitConnect = btnInitConnect;
    }

    public Button getBtnInitSubsribe() {
        return btnInitSubsribe;
    }

    public void setBtnInitSubsribe(Button btnInitSubsribe) {
        this.btnInitSubsribe = btnInitSubsribe;
    }

    public Button getBtnInitLeave() {
        return btnInitLeave;
    }

    public void setBtnInitLeave(Button btnInitLeave) {
        this.btnInitLeave = btnInitLeave;
    }

}
