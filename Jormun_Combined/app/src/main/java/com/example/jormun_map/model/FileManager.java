package com.example.jormun_map.model;

import android.content.Context;
import android.os.Build;

import com.example.jormun_map.Config.TablesName;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FileManager {
    private static String[] a_sFilename = new String[]{"Jormun","Jormun/Boss","Jormun/Team","Jormun/Hero","Jormun/Monster","Jormun/Village","Jormun/Structure"};
    private Context ctxtMain;
    private FileManager(){//                                0       1               2              3            4               5               6
    }
    private static class FileManagerHolder{
        private static FileManager flmgrInstance = new FileManager();
    }
    public static FileManager getInstance(){
        return FileManagerHolder.flmgrInstance;
    }
    public String WriteJson(JSONObject jsnoTemp , TablesName tablesName,int iId) {
        System.out.println("Prepare to run");
        FutureTask<String> fttskWriteFile;
        ExecutorService exeservCurrent;

        String sPath;
        String sName;

        try {
            sName = jsnoTemp.getString("sName");
            sPath = getPath(sName, tablesName);
            System.out.println("Define the task and the executor");
            exeservCurrent = Executors.newSingleThreadExecutor();
            fttskWriteFile = new FutureTask<>(() -> {
                try {
                    System.out.println("Run the engine");
                    File flCurrent;

                    FileWriter flwrtrCurrent;
                    BufferedWriter bufwrtrCurrent;

                    flCurrent = new File(sPath);
                    flwrtrCurrent = new FileWriter(flCurrent);
                    bufwrtrCurrent = new BufferedWriter(flwrtrCurrent);

                    bufwrtrCurrent.write(jsnoTemp.toString());
                    bufwrtrCurrent.close();
                    return sPath;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            });
            exeservCurrent.submit(fttskWriteFile);
            return sPath;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public String getPath(String sName,TablesName tblnmCurrent){
        String sRoot;

        sRoot = getCtxtMain().getExternalFilesDir(null).getAbsolutePath();
        switch (tblnmCurrent){
            case TABLE_BOSS:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[1],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[1]+File.separatorChar+sName;
                }
            case TABLE_ITEM:
                break;
            case TABLE_HERO:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[3],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[3]+File.separatorChar+String.format("#%s#%s.json",tblnmCurrent.toString(),sName);
                }
            case TABLE_QUEST:
                break;
            case TABLE_MONSTER:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[4],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[4]+File.separatorChar+String.format("#%s#%s.json",tblnmCurrent.toString(),sName);
                }
            case TABLE_VILLAGE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[5],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[5]+File.separatorChar+String.format("#%s#%s.json",tblnmCurrent.toString(),sName);
                }
            case TABLE_BUILDING:
                break;
            case TABLE_INVENTORY:
                break;
            case TABLE_STRUCTURE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[6],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[6]+File.separatorChar+String.format("#%s#%s.json",tblnmCurrent.toString(),sName);
                }
            case TABLE_CURRENTTEAM:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return Paths.get(sRoot,a_sFilename[2],String.format("#%s#%s.json",tblnmCurrent.toString(),sName)).toString();
                }else{
                    return sRoot+File.separatorChar+a_sFilename[2]+File.separatorChar+String.format("#%s#%s.json",tblnmCurrent.toString(),sName);
                }
            default:
                break;
        }
        return "";
    }
    public void Initialize(Context ctxtCurrent){
        String sCurrentPath;
        String sRoot;

        File flCurrent;

        sRoot = ctxtCurrent.getExternalFilesDir(null).getAbsolutePath();
        for (String sFolderName : a_sFilename) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                sCurrentPath = Paths.get(sRoot,sFolderName).toString();
            }else{
                sCurrentPath = sRoot+File.separatorChar+sFolderName;
            }
            System.out.println(sCurrentPath);
            flCurrent = new File(sCurrentPath);
            if(!flCurrent.exists() || !flCurrent.isDirectory()){
                if(flCurrent.mkdir()){
                    System.out.println("Folder should have been made");
                }else{
                    System.out.println("Folder shouldn't have been made");
                }
            }
        }
    }

    public static String[] getA_sFilename() {
        return a_sFilename;
    }

    public Context getCtxtMain() {
        return ctxtMain;
    }

    public static void setA_sFilename(String[] a_sFilename) {
        FileManager.a_sFilename = a_sFilename;
    }

    public void setCtxtMain(Context ctxtMain) {
        this.ctxtMain = ctxtMain;
    }
}
