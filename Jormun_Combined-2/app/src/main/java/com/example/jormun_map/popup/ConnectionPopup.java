package com.example.jormun_map.popup;

import android.annotation.SuppressLint;
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
import com.example.jormun_map.Config.SharedPrefKeys;
import com.example.jormun_map.Config.TablesName;
import com.example.jormun_map.MapsActivity;
import com.example.jormun_map.R;
import com.example.jormun_map.client.PositionServerClient;
import com.example.jormun_map.model.ExternalDbManager;
import com.example.jormun_map.model.InternalDbManager;

import java.util.Calendar;
import java.util.Random;

public class ConnectionPopup extends Popup {

    private EditText edttxtUsername;
    private EditText edttxtPassword;

    private Button btnConfirm;
    private Button btnSubscribe;

    public ConnectionPopup(ExternalDbManager extdbmgrWebsiteAPI, InternalDbManager intdbmrgStorage, FragmentManager frgmgrLoader, MapsActivity mpsactCurrent, SharedPreferences shrdprefSession) {
        super(extdbmgrWebsiteAPI, intdbmrgStorage, frgmgrLoader, mpsactCurrent, shrdprefSession);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwConnection;

        vwConnection = InflateCorrespondingView(inflater, container, false, R.layout.admin_popup_connexion);

        setBtnConfirm(vwConnection.findViewById(R.id.btnConConfirm));
        setBtnSubscribe(vwConnection.findViewById(R.id.btnConSubscribe));
        setEdttxtPassword(vwConnection.findViewById(R.id.edttxtConPassword));
        setEdttxtUsername(vwConnection.findViewById(R.id.edttxtConUsername));

        getBtnConfirm().setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View vwCurrent) {
                int[] a_iInt;
                String sAnswer;
                String sTokenUser;
                String sTokenKey;
                SharedPreferences.Editor shrdprefeditScribble;

                if (VerifyError((a_iInt = CheckInput()))) {
                    sTokenUser = getRandomId();
                    sAnswer = getExtdbmgrApiServer().ConnectUser(getEdttxtUsername().getText().toString(), getEdttxtPassword().getText().toString(), sTokenUser);
                    if (!sAnswer.contains(ResponseTypeTag.FAILED.toString())) {
                        sTokenKey = sAnswer.split(ResponseTypeTag.SUCCESS.toString())[1];
                        shrdprefeditScribble = getShrdprefSave().edit();
                        shrdprefeditScribble.putString(SharedPrefKeys.USERNAMEKEY.toString(), getEdttxtUsername().getText().toString());
                        shrdprefeditScribble.putString(SharedPrefKeys.POSITIONKEY.toString(),sTokenKey);
                        shrdprefeditScribble.putString(SharedPrefKeys.POSITIONLOGIN.toString(),sTokenUser);
                        shrdprefeditScribble.apply();
                        PositionServerClient posservcltCurrent = PositionServerClient.getInstance();
                        posservcltCurrent.setShrdprefSetting(getShrdprefSave());
                        posservcltCurrent.connect();
                        getExtdbmgrApiServer().FetchSimpleFile(TablesName.TABLE_STRUCTURE,1);
                        getMpsactCarte().ClearScreen();
                    } else {
                        getEdttxtPassword().setText("");
                        getEdttxtUsername().setText("");
                    }
                } else {
                    UnderlineError(a_iInt);
                }
            }
        });
        getBtnSubscribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                Popup popupNext;

                popupNext = new SubscriptionPopup(getExtdbmgrApiServer(), getIntdbmgrStorage(), getFrgmgrController(), getMpsactCarte(), getShrdprefSave());
                Popup.switchPopup("Subscribe", getFrgmgrController(), popupNext);
            }
        });

        return vwConnection;
    }

    /**
     *
     */
    @Override
    public int[] CheckInput() {
        int[] a_iCode;

        a_iCode = new int[]{-1, -1};
        if (getEdttxtPassword().getText().length() <= 0) {
            a_iCode[0] = R.id.edttxtConPassword;
        }
        if (getEdttxtUsername().getText().length() <= 0) {
            a_iCode[1] = R.id.edttxtConUsername;
        }

        return a_iCode;
    }

    private boolean VerifyError(int[] a_iInt) {
        int iProduct;
        iProduct = 1;
        for (int iElement : a_iInt) {
            iProduct *= iElement;
        }
        return Math.abs(iProduct) == 1;
    }

    private void UnderlineError(int[] a_iResult) {
        ApplyRule(a_iResult[0], getEdttxtPassword());
        ApplyRule(a_iResult[1], getEdttxtUsername());
    }

    public void ApplyRule(int iResult, EditText edittxtCurrent) {
        if (iResult != -1) {
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.colorWrongInput));
        } else {
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.colorRightInput));
        }
    }

    /**
     * @return Generate a random
     * @Reference : Code found at https://stackoverflow.com/questions/12116092/android-random-string-generator#answer-12116327:~:text=There%20are%20a%20few%20things%20wrong,random%20string%20using%20the%20toString()%20method
     */
    public String getRandomId() {
        StringBuilder token;

        String sAlphabet;

        Random rndGenerator;

        rndGenerator = new Random();
        token = new StringBuilder(8);
        sAlphabet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";
        for (int i = 0; i < 8; i++) {
            token.append(sAlphabet.charAt(rndGenerator.nextInt(sAlphabet.length())));
        }
        String newRandomId = String.valueOf(Calendar.getInstance().getTimeInMillis());
        return newRandomId + "#" + token;
    }

    public EditText getEdttxtUsername() {
        return edttxtUsername;
    }

    public void setEdttxtUsername(EditText edttxtUsername) {
        this.edttxtUsername = edttxtUsername;
    }

    public EditText getEdttxtPassword() {
        return edttxtPassword;
    }

    public void setEdttxtPassword(EditText edttxtPassword) {
        this.edttxtPassword = edttxtPassword;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public void setBtnConfirm(Button btnConfirm) {
        this.btnConfirm = btnConfirm;
    }

    public Button getBtnSubscribe() {
        return btnSubscribe;
    }

    public void setBtnSubscribe(Button btnSubscribe) {
        this.btnSubscribe = btnSubscribe;
    }

}
