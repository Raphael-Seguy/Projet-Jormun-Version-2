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

public class ErrorPopup extends Popup {

    private Button btnErrorLeave;
    private Button btnErrorDemo;

    public ErrorPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        super(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwError;

        vwError = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_error);
        setBtnErrorDemo(vwError.findViewById(R.id.btnErrorDemo));
        setBtnErrorLeave(vwError.findViewById(R.id.btnErrorLeave));

        getBtnErrorDemo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Provide a demo");
            }
        });
        getBtnErrorLeave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        return vwError;
    }

    public Button getBtnErrorLeave() {
        return btnErrorLeave;
    }

    public void setBtnErrorLeave(Button btnErrorLeave) {
        this.btnErrorLeave = btnErrorLeave;
    }

    public Button getBtnErrorDemo() {
        return btnErrorDemo;
    }

    public void setBtnErrorDemo(Button btnErrorDemo) {
        this.btnErrorDemo = btnErrorDemo;
    }
}
