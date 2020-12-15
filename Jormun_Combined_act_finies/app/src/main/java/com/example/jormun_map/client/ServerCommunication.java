package com.example.jormun_map.client;

import android.content.SharedPreferences;

import com.example.jormun_map.Config.CommunicationDividor;
import com.example.jormun_map.Config.CommunicationVariables;
import com.example.jormun_map.Config.ConnectionState;
import com.example.jormun_map.Config.CornerId;
import com.example.jormun_map.Config.MessageType;
import com.example.jormun_map.Config.SharedPrefKeys;
import com.example.jormun_map.model.data_classes.Point;
import com.example.jormun_map.model.data_classes.UserManager;
import com.example.jormun_map.model.thread_special.ControledThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunication {
    Socket sktCurrent;

    ExecutorService execservSecretary;

    DataOutputStream dtoutstrmSender;
    DataInputStream dtinstrmReceiver;

    Thread thrdLauncher;

    Runnable rnbleReceiver;
    Runnable rnbleSender;
    Runnable rnbleCheckConnection;
    Runnable rnbleAnalyse;

    ConcurrentLinkedQueue<String> q_sResponse;

    boolean bListening;

    public ServerCommunication() {
        //System.out.println("Explain me what is going on");
        //System.out.println("We define the thread that will be in charge of the communication");
        //System.out.println("We define the thread that will create all the others.");
        setThrdLauncher(new Thread(){
            @Override
            public void run() {
                Socket sktTemp;

                int iPort;
                String sIP;

                //System.out.println("We grab the server information IP and Port");
                iPort = PositionServerClient.getiPort();
                sIP = PositionServerClient.getsIP();
                //System.out.println("We then try to connect to the server");
                try {
                    sktTemp = new Socket(sIP,iPort);
                    //System.out.println("We check if a connection has been established");
                    if(sktTemp.isBound()){
                        //System.out.println("We set up the input and output stream");
                        setDtinstrmReceiver(new DataInputStream(sktTemp.getInputStream()));
                        setDtoutstrmSender(new DataOutputStream(sktTemp.getOutputStream()));
                    }else{
                        //System.out.println("if we can't connect then by default make sure the values are null");
                        setDtoutstrmSender(null);
                        setDtinstrmReceiver(null);
                    }
                    //System.out.println("Save the socket anyway");
                    setSktCurrent(sktTemp);
                    //System.out.println("Put in place the executor");
                    setExecservSecretary(Executors.newCachedThreadPool());
                    //System.out.println("Check if the connection is correct");
                    if(getDtinstrmReceiver()!=null && getDtoutstrmSender()!=null){
                        //System.out.println("Set the basics settings");
                        setbListening(true);
                        //System.out.println("Set the receiver");
                        setRnbleReceiver(new Runnable() {
                            String sMessage;

                            @Override
                            public void run() {
                                try {
                                    do{
                                        sMessage = getDtinstrmReceiver().readUTF();
                                        getQ_sResponse().add(sMessage);
                                        try {
                                            Thread.sleep(200);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }while (true);
                                }catch (IOException e){
                                    ((ControledThread)(getRnbleCheckConnection())).setbCheck(true);
                                }
                            }
                        });
                        //System.out.println("Set the analyser");
                        setRnbleAnalyse(() -> {
                            do {
                                if(!getQ_sResponse().isEmpty()){
                                    Analyse(getQ_sResponse().element());
                                    getQ_sResponse().remove();
                                }
                            }while (isbListening());
                        });
                        //System.out.println("Set the checkConnection");
                        setRnbleCheckConnection(new ControledThread() {
                            Socket sktNewAttempt;
                            @Override
                            public void setbCheck(boolean bValue) {
                                super.bCheck = bValue;
                            }

                            @Override
                            public void run() {
                                do {
                                    if(isbCheck()){
                                        try {
                                            sktNewAttempt = new Socket(getSktCurrent().getInetAddress(),getSktCurrent().getPort());
                                            ResetConnection(sktNewAttempt);
                                        }catch (IOException e){}
                                    }else{
                                        try {
                                            SendMessage(getMessageString(MessageType.UPDATEPOS));
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }while (true);
                            }
                        });
                        //System.out.println("Set the checkConnection");
                        ((ControledThread)getRnbleCheckConnection()).setbCheck(false);
                    }
                    //System.out.println(getMessageString(ConnectionState.CONNECTION_REQUEST));
                    SendMessage(getMessageString(ConnectionState.CONNECTION_REQUEST));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        try{
            getThrdLauncher().start();
            while(getThrdLauncher().isAlive()){}
            //System.out.println("Launching the threads");
            //System.out.println("Launching Receiver");
            getExecservSecretary().submit(getRnbleReceiver());
            //System.out.println("Launching Analyse");
            getExecservSecretary().submit(getRnbleAnalyse());
            //System.out.println("Launching CheckCommunication");
            getExecservSecretary().submit(getRnbleCheckConnection());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ResetConnection(Socket sktCurrent){
        setSktCurrent(sktCurrent);
        try{
            setDtinstrmReceiver(new DataInputStream(getSktCurrent().getInputStream()));
            setDtoutstrmSender(new DataOutputStream(getSktCurrent().getOutputStream()));
            ((ControledThread)(getRnbleCheckConnection())).setbCheck(false);
            getExecservSecretary().submit(this.getRnbleReceiver());
            getExecservSecretary().submit(this.getRnbleReceiver());
            setbListening(true);
            SendMessage(getMessageString(ConnectionState.CONNECTION_REQUEST));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void SendMessage(String sMessage){
        //System.out.println("Send Message "+sMessage );
        setRnbleSender(new Runnable() {
            @Override
            public void run() {
                try{
                    getDtoutstrmSender().writeUTF(sMessage);
                    getDtoutstrmSender().flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        getExecservSecretary().submit(rnbleSender);
    }
    public String getMessageString(MessageType mestpNewMessage){
        switch (mestpNewMessage){
            case UPDATEPOS:
                UserManager usrmgrCurrent;

                Point pntCenter;
                Point pntFarLeft;
                Point pntFarRight;
                Point pntNearLeft;
                Point pntNearRight;

                usrmgrCurrent = UserManager.getInstance();
                pntCenter = usrmgrCurrent.getPntUserPos();
                pntFarLeft = usrmgrCurrent.getBndView().getPntCornerLeftFar();
                pntFarRight = usrmgrCurrent.getBndView().getPntCornerRightFar();
                pntNearLeft = usrmgrCurrent.getBndView().getPntCornerLeftNear();
                pntNearRight = usrmgrCurrent.getBndView().getPntCornerRightNear();
                return MessageType.UPDATEPOS.toString()+ CommunicationDividor.WRITE_PARAM+ CommunicationVariables.POS+"("+pntCenter.getdLat()+CommunicationDividor.COORD+pntCenter.getdLong()+")"+CommunicationDividor.PARAM_DIVIDOR+CommunicationVariables.BOUNDS+"("+pntFarRight.getdLat()+CommunicationDividor.COORD+pntFarRight.getdLong()+CommunicationDividor.COORD+ CornerId.FR+")"+CommunicationDividor.BOUND_DIVIDOR+"("+pntFarLeft.getdLat()+CommunicationDividor.COORD+pntFarLeft.getdLong()+CommunicationDividor.COORD+CornerId.FL+")"+CommunicationDividor.BOUND_DIVIDOR.toString()+"("+pntNearRight.getdLat()+CommunicationDividor.COORD+pntNearRight.getdLong()+CommunicationDividor.COORD+CornerId.NR+")"+CommunicationDividor.BOUND_DIVIDOR+"("+pntNearLeft.getdLat()+CommunicationDividor.COORD+pntNearLeft.getdLong()+CommunicationDividor.COORD+CornerId.NL+")";
            case CLOSE:
                return MessageType.CLOSE.toString();
            default:
                throw new RuntimeException("Bad MessageType => Possibly Reserved for Receiver end.");
        }
    }
    public String getMessageString(ConnectionState constteNewMessage){
        PositionServerClient posservcltCurrent;

        SharedPreferences shrdprefCurrent;

        String sUsername;
        String sTokenKey;
        String sTokenUser;

        posservcltCurrent = PositionServerClient.getInstance();

        switch (constteNewMessage){
            case CHECKCONNECTION:
                return ConnectionState.CHECKCONNECTION.toString();
            case CONNECTION_REQUEST:
                shrdprefCurrent = posservcltCurrent.getShrdprefSetting();

                sTokenKey = (String) shrdprefCurrent.getAll().get(SharedPrefKeys.POSITIONKEY.toString());
                sTokenUser = (String) shrdprefCurrent.getAll().get(SharedPrefKeys.POSITIONLOGIN.toString());
                sUsername = (String) shrdprefCurrent.getAll().get(SharedPrefKeys.USERNAMEKEY.toString());
                return ConnectionState.CONNECTION_REQUEST.toString()+CommunicationDividor.WRITE_PARAM+CommunicationVariables.USERNAME+sUsername+CommunicationDividor.PARAM_DIVIDOR+CommunicationVariables.TOKENKEY+sTokenKey+CommunicationDividor.PARAM_DIVIDOR+CommunicationVariables.TOKENUSER+sTokenUser;
            default:
                throw new RuntimeException("Bad ConnectionState => Possibly Reserved for Receiver end.");
        }
    }
    public void Analyse(String sMessage){
        //System.out.println(sMessage);
        if(sMessage.contains(MessageType.UPDATEOTHERCLIENT.toString())){
            UpdateOther(sMessage.split(CommunicationVariables.POS.toString())[0],sMessage.split(CommunicationVariables.POS.toString())[1]);
        }else if(sMessage.contains(ConnectionState.CONNECTION_REQUEST.toString())){
            System.out.println("Connection accepted");
        }else{
            Stop();
        }
    }
    public void Stop(){
        //System.exit(0);
    }
    public void UpdateOther(String sUsername,String sPosition){
        String[] a_sTemp;

        float fLat;
        float fLong;

        a_sTemp = sPosition.substring(1,sPosition.length()-1).split(";");

        try{
            fLat = Float.parseFloat(a_sTemp[0]);
            fLong = Float.parseFloat(a_sTemp[1]);
            sUsername = sUsername.substring(0,sUsername.length()-1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Socket getSktCurrent() {
        return sktCurrent;
    }

    public void setSktCurrent(Socket sktCurrent) {
        this.sktCurrent = sktCurrent;
    }

    public ExecutorService getExecservSecretary() {
        return execservSecretary;
    }

    public void setExecservSecretary(ExecutorService execservSecretary) {
        this.execservSecretary = execservSecretary;
    }

    public DataOutputStream getDtoutstrmSender() {
        return dtoutstrmSender;
    }

    public void setDtoutstrmSender(DataOutputStream dtoutstrmSender) {
        this.dtoutstrmSender = dtoutstrmSender;
    }

    public DataInputStream getDtinstrmReceiver() {
        return dtinstrmReceiver;
    }

    public void setDtinstrmReceiver(DataInputStream dtinstrmReceiver) {
        this.dtinstrmReceiver = dtinstrmReceiver;
    }

    public Thread getThrdLauncher() {
        return thrdLauncher;
    }

    public void setThrdLauncher(Thread thrdLauncher) {
        this.thrdLauncher = thrdLauncher;
    }

    public Runnable getRnbleReceiver() {
        return rnbleReceiver;
    }

    public void setRnbleReceiver(Runnable rnbleReceiver) {
        this.rnbleReceiver = rnbleReceiver;
    }

    public Runnable getRnbleSender() {
        return rnbleSender;
    }

    public void setRnbleSender(Runnable rnbleSender) {
        this.rnbleSender = rnbleSender;
    }

    public Runnable getRnbleCheckConnection() {
        return rnbleCheckConnection;
    }

    public void setRnbleCheckConnection(Runnable rnbleCheckConnection) {
        this.rnbleCheckConnection = rnbleCheckConnection;
    }

    public Runnable getRnbleAnalyse() {
        return rnbleAnalyse;
    }

    public void setRnbleAnalyse(Runnable rnbleAnalyse) {
        this.rnbleAnalyse = rnbleAnalyse;
    }

    public ConcurrentLinkedQueue<String> getQ_sResponse() {
        return q_sResponse;
    }

    public void setQ_sResponse(ConcurrentLinkedQueue<String> q_sResponse) {
        this.q_sResponse = q_sResponse;
    }

    public boolean isbListening() {
        return bListening;
    }

    public void setbListening(boolean bListening) {
        this.bListening = bListening;
    }
}
