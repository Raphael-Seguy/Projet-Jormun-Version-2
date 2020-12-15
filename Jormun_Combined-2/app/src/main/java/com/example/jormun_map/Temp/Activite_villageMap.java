package com.example.jormun_map.Temp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jormun_map.R;
import com.example.jormun_map.model.data_classes.Village;

import java.util.ArrayList;

public class Activite_villageMap extends MainActivity
{
    private Handler mainHandler = new Handler();
    private TextView title_village;

    private ImageButton btn_bat0;
    private ImageButton btn_bat1;
    private ImageButton btn_bat2;
    private ImageButton btn_bat3;
    private ImageButton btn_bat4;
    private ImageButton btn_bat5;
    private ImageButton btn_bat6;
    private ImageButton btn_bat7;
    private ImageButton btn_hotel_vill;

    private ImageButton back_btn;

    private Village actu_village;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map_village);
        simulation();
        LiasionCode_affichage();
        generationVillage();
    }
    private void LaunchBase()
    {
        Base runnable = new Base();
        new Thread(runnable).start();
    }
    class Base implements Runnable
    {
        boolean Quit=false;
        @Override
        public void run() {
            while (Quit==false)
            {
                back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuitThisPlace();
                    }
                });
            }
        }
    }

    public void LiasionCode_affichage()
    {
        title_village=findViewById(R.id.Titlevillage);
        btn_bat0=findViewById(R.id.batiment0);
        btn_bat1=findViewById(R.id.batiment1);
        btn_bat2=findViewById(R.id.batiment2);
        btn_bat3=findViewById(R.id.batiment3);
        btn_bat4=findViewById(R.id.batiment4);
        btn_bat5=findViewById(R.id.batiment5);
        btn_bat6=findViewById(R.id.batiment6);
        btn_bat7=findViewById(R.id.batiment7);

        this.btn_hotel_vill=findViewById(R.id.hotel_de_ville);

        back_btn=findViewById(R.id.back_btn);
    }
    public void generationVillage()
    {
        if(actu_village.getLvl()>1 && actu_village.getLvl()<=3)
        {
            btn_hotel_vill.setImageResource(R.drawable.hotel_village);
        }
        else if(actu_village.getLvl()>3 && actu_village.getLvl() <=6)
        {
            btn_hotel_vill.setImageResource(R.drawable.hotel_ville);
        }
        else
        {
            this.btn_hotel_vill.setImageResource(R.drawable.hotel_cite);
        }

        ArrayList<ImageButton> ListBtn= new ArrayList<ImageButton>();
        ListBtn.add(btn_bat0);
        ListBtn.add(btn_bat1);
        ListBtn.add(btn_bat2);
        ListBtn.add(btn_bat3);
        ListBtn.add(btn_bat4);
        ListBtn.add(btn_bat5);
        ListBtn.add(btn_bat6);
        ListBtn.add(btn_bat7);
        for(int iCompteur=0;iCompteur <actu_village.getListBat().size();iCompteur++)
        {
            ListBtn.get(iCompteur).setImageResource(actu_village.getListBat().get(iCompteur).getImage());
        }
    };

    public void QuitThisPlace() {
        Intent intent = new Intent(com.example.jormun_map.Temp.Activite_villageMap.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    ////SIMULATION

    public void simulation()
    {
        actu_village=new Village("Greg",45,50,1,12);
        actu_village.addBatiment(1,2);
        actu_village.addBatiment(3,4);
        actu_village.addBatiment(4,6);
        actu_village.addBatiment(5,2);
        actu_village.addBatiment(1,5);
    }
}
