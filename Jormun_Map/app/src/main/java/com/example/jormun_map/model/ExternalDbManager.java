package com.example.jormun_map.model;

import com.example.jormun_map.Config.DatabaseActions;
import com.example.jormun_map.Config.HttpRequestType;
import com.example.jormun_map.Config.ResponseTypeTag;
import com.example.jormun_map.Config.ServerTags;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class ExternalDbManager {

    public String sWebsiteUrl;

    public boolean bIsAccessible;

    private HostnameVerifier hstnmverfHostNameManager;

    private OkHttpClient okhttp3Client;

    private ExternalDbManager() {
        ConCheckTest conchcktestConnectionVerif;

        SetupHostCertificate();
        setbIsAccessible(false);
        conchcktestConnectionVerif = new ConCheckTest(this);
        conchcktestConnectionVerif.start();
        AwaitEndOfTask(conchcktestConnectionVerif);
    }

    public static ExternalDbManager getInstance() {
        return DbHolder.extdbmgrInstance;
    }

    public void AwaitEndOfTask(Thread thrdCurrent) {
        while (thrdCurrent.isAlive()) {
        }
    }

    public void SetupHostCertificate() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        // Install the all-trusting trust manager
        try {
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        setHstnmverfHostNameManager(allHostsValid);
    }

    public String generateURLString(DatabaseActions dbActions) {
        String sURL;
        System.out.println("<DEBUG LOG TO BE CLEARED>++++++=Definition of the request depending on the action required");
        sURL = "https://Casteu.ddns.net/API/JormunApi/";

        return sURL + dbActions.toString();
    }

    public String RequestManager(String sMethod, DatabaseActions dbActions) {
        URL urlCurrentRequest;

        HttpsURLConnection httpsurlconCurrent;

        final Reader rdrLecteur;

        final BufferedReader bufrdrLecteur;

        String sLine;
        String sResponse;

        HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());
        sResponse = "";
        try {
            urlCurrentRequest = new URL(generateURLString(dbActions));
            httpsurlconCurrent = (HttpsURLConnection) urlCurrentRequest.openConnection();
            httpsurlconCurrent.setRequestMethod(sMethod);
            httpsurlconCurrent.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpsurlconCurrent.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            rdrLecteur = new InputStreamReader(httpsurlconCurrent.getInputStream());
            bufrdrLecteur = new BufferedReader(rdrLecteur);
            while ((sLine = bufrdrLecteur.readLine()) != null) {
                sResponse += sLine;
            }
            bufrdrLecteur.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sResponse;
    }

    public String GenerateRequest(Map<String, String> mps_sParams) {
        String sRequest;
        sRequest = "";
        for (String sKey : mps_sParams.keySet()) {
            sRequest += sKey + "=" + mps_sParams.get(sKey) + "&";
        }
        sRequest = sRequest.substring(0, sRequest.length() - 1);
        return sRequest;
    }

    public String RequestManager(String sMethod, DatabaseActions dbActions, String sRequestInput) {
        Thread thrdCurrent;
        URL urlCurrentRequest;

        HttpsURLConnection httpsurlconCurrent;

        final Reader rdrLecteur;

        final BufferedReader bufrdrLecteur;

        String sLine;
        StringBuilder sResponse;
        String sRequest;

        HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());
        sResponse = new StringBuilder();
        try {
            urlCurrentRequest = new URL(generateURLString(dbActions));
            httpsurlconCurrent = (HttpsURLConnection) urlCurrentRequest.openConnection();
            httpsurlconCurrent.setRequestMethod(sMethod);
            httpsurlconCurrent.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpsurlconCurrent.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            sRequest = sRequestInput;
            // Send post request
            httpsurlconCurrent.setDoOutput(true);
            try (DataOutputStream dtoutstrmWriter = new DataOutputStream(httpsurlconCurrent.getOutputStream())) {
                dtoutstrmWriter.writeBytes(sRequest);
                dtoutstrmWriter.flush();
            }
            rdrLecteur = new InputStreamReader(httpsurlconCurrent.getInputStream());
            bufrdrLecteur = new BufferedReader(rdrLecteur);
            while ((sLine = bufrdrLecteur.readLine()) != null) {
                sResponse.append(sLine);
            }
            bufrdrLecteur.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sResponse.toString();
    }

    public Object[] RequestManagerWrapper(String sRequestType, DatabaseActions dbActions, HashMap<String, String> hshmps_sParams) {
        final String[] a_sResult;

        a_sResult = new String[1];

        Thread thrdLoginRequest;

        thrdLoginRequest = new Thread(new Runnable() {
            @Override
            public void run() {
                a_sResult[0] = RequestManager(HttpRequestType.POST_REQUEST.toString(), dbActions, GenerateRequest(hshmps_sParams));
            }
        });
        thrdLoginRequest.start();
        while (thrdLoginRequest.isAlive()) {
        }

        return a_sResult;
    }

    public String RegisterUser(String sUsername, String sMail, String sPassword) {
        HashMap<String, String> hshmps_sSet;

        String sReceivedAnswer;

        hshmps_sSet = new HashMap<>();
        hshmps_sSet.put("sName", sUsername);
        hshmps_sSet.put("sMail", sMail);
        hshmps_sSet.put("sPass", sPassword);

        sReceivedAnswer = (String) RequestManagerWrapper(HttpRequestType.POST_REQUEST.toString(), DatabaseActions.ACTION_REGISTER, hshmps_sSet)[0];
        if (sReceivedAnswer != null && sReceivedAnswer.contains(ServerTags.DONE.toString())) {
            return ResponseTypeTag.SUCCESS.toString() + sReceivedAnswer;
        } else {
            return ResponseTypeTag.FAILED.toString() + sReceivedAnswer;
        }
    }

    public String ConnectUser(String sUsername, String sPassword, String sToken) {
        HashMap<String, String> hshmps_sSet;

        String sRecievedAnswer;

        hshmps_sSet = new HashMap<>();
        hshmps_sSet.put("sLogin", sUsername);
        hshmps_sSet.put("sPass", sPassword);
        hshmps_sSet.put("sMacAddress", sToken);

        sRecievedAnswer = (String) RequestManagerWrapper(HttpRequestType.POST_REQUEST.toString(), DatabaseActions.ACTION_LOGIN, hshmps_sSet)[0];
        if (sRecievedAnswer != null && sRecievedAnswer.contains(ServerTags.DONE.toString())) {
            return ResponseTypeTag.SUCCESS.toString() + sRecievedAnswer.split("#")[1];
        } else {
            return ResponseTypeTag.FAILED.toString() + sRecievedAnswer;
        }
    }

    public void checkConnection() {

        String sResponse;

        sResponse = RequestManager(HttpRequestType.GET_REQUEST.toString(), DatabaseActions.ACTION_CONVERIF);

        if (sResponse.contains("true")) {
            setbIsAccessible(true);
        }
    }

    public HostnameVerifier getHstnmverfHostNameManager() {
        return hstnmverfHostNameManager;
    }

    public void setHstnmverfHostNameManager(HostnameVerifier hstnmverfHostNameManager) {
        this.hstnmverfHostNameManager = hstnmverfHostNameManager;
    }

    //Getter & Setter

    public OkHttpClient getOkhttp3Client() {
        return okhttp3Client;
    }

    public void setOkhttp3Client(OkHttpClient okhttp3Client) {
        this.okhttp3Client = okhttp3Client;
    }

    public String getsWebsiteUrl() {
        return sWebsiteUrl;
    }

    public void setsWebsiteUrl(String sWebsiteUrl) {
        this.sWebsiteUrl = sWebsiteUrl;
    }

    public boolean isbIsAccessible() {
        return bIsAccessible;
    }

    public void setbIsAccessible(boolean bIsAccessible) {
        this.bIsAccessible = bIsAccessible;
    }

    private static class DbHolder {
        private final static ExternalDbManager extdbmgrInstance = new ExternalDbManager();
    }

    private class ConCheckTest extends Thread {

        ExternalDbManager extdbmgrCurrent;

        public ConCheckTest(ExternalDbManager extdbmgrCurrent) {
            setExtdbmgrCurrent(extdbmgrCurrent);
        }

        @Override
        public void run() {
            checkConnection();
        }

        public ExternalDbManager getExtdbmgrCurrent() {
            return extdbmgrCurrent;
        }

        public void setExtdbmgrCurrent(ExternalDbManager extdbmgrCurrent) {
            this.extdbmgrCurrent = extdbmgrCurrent;
        }
    }

}

