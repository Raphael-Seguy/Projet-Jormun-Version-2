package com.example.jormun_map.popup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.jormun_map.Config.ResponseTypeTag;
import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.R;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;

public class SubscriptionPopup extends Popup {

    Button btnSubscribe;

    EditText edttxtSubPassword;
    EditText edttxtSubUsername;
    EditText edttxtSubMail;

    public SubscriptionPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        super(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwSubscribe;

        vwSubscribe = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_subscription);

        setBtnSubscribe(vwSubscribe.findViewById(R.id.btnSubConfirm));
        setEdttxtSubMail(vwSubscribe.findViewById(R.id.edttxtSubMail));
        setEdttxtSubPassword(vwSubscribe.findViewById(R.id.edttxtSubPassword));
        setEdttxtSubUsername(vwSubscribe.findViewById(R.id.edttxtSubUsername));

        getBtnSubscribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                SubscribeUser(vwCurrent);
            }
        });

        return vwSubscribe;
    }

    public void SubscribeUser(View vwCurrent) {
        String sReturn;
        sReturn = getExtdbmgrApiServer().RegisterUser(getEdttxtSubUsername().getText().toString(), getEdttxtSubMail().getText().toString(), getEdttxtSubPassword().getText().toString());
        if (sReturn.contains(ResponseTypeTag.SUCCESS.toString())) {
            ConnectionPopup popupconLogIn;

            popupconLogIn = new ConnectionPopup(getExtdbmgrApiServer(), getIntdbmgrStorage(), getFragmentManager(), getMpsactCarte(), getShrdprefSave());
            Popup.switchPopup("Start", getFrgmgrController(), popupconLogIn);
        }
    }

    public Button getBtnSubscribe() {
        return btnSubscribe;
    }

    public void setBtnSubscribe(Button btnSubscribe) {
        this.btnSubscribe = btnSubscribe;
    }

    public EditText getEdttxtSubPassword() {
        return edttxtSubPassword;
    }

    public void setEdttxtSubPassword(EditText edttxtSubPassword) {
        this.edttxtSubPassword = edttxtSubPassword;
    }

    public EditText getEdttxtSubUsername() {
        return edttxtSubUsername;
    }

    public void setEdttxtSubUsername(EditText edttxtSubUsername) {
        this.edttxtSubUsername = edttxtSubUsername;
    }

    public EditText getEdttxtSubMail() {
        return edttxtSubMail;
    }

    public void setEdttxtSubMail(EditText edttxtSubMail) {
        this.edttxtSubMail = edttxtSubMail;
    }

}
