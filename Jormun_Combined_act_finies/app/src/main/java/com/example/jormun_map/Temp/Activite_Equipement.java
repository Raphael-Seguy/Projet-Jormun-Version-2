package com.example.jormun_map.Temp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jormun_map.R;
import com.example.jormun_map.model.data_classes.BonusEffect;
import com.example.jormun_map.model.data_classes.Equipement;
import com.example.jormun_map.model.data_classes.Hero;
import com.example.jormun_map.model.data_classes.MalusEffect;
import com.example.jormun_map.model.data_classes.Recompense;
import com.example.jormun_map.model.data_classes.Spell;
import com.example.jormun_map.model.data_classes.Utilitaire;

import java.util.ArrayList;

public class Activite_Equipement extends AppCompatActivity {
    private Handler mainHandler = new Handler();
    boolean Quit;

    private int activateSpel=3;
    private boolean activeObjet = false;
    private int selectedEmplacement=3;

    int Action;
    //bloc du haut
    private ImageView SpriteHero;
    private TextView Name;
    ArrayList btnItemsTotal = new ArrayList();
    //stats
    private TextView IconArmure;
    private TextView ValeurArmure;
    private TextView IconArmuremag;
    private TextView ValeurArmuremag;
    private TextView IconDegat;
    private TextView ValeurDegat;
    private TextView IconDegatmag;
    private TextView ValeurDegatmag;
    private TextView IconEsquive;
    private TextView ValeurEsquive;
    //classes
    private TextView Classes;
    private TextView PvText;

    //Personnage

    Hero actuHero = new Hero();


    //btn_retour
    private Button Retour;
    //btn menu
    private Button Skills;
    private Button Stuff;
    private Button inv;

    private ScrollView List_spels;
    private ScrollView List_objets;
    private Button Quit_liste;
    //spels choisis
    private Button Spel0;
    private Button Spel1;
    private Button Spel2;
    private Button Spel3;
    //equipement
    private Button Equip_tete;
    private String Equip_teteName;
    private Button Equip_torse;
    private String Equip_torseName;
    private Button Equip_jambe;
    private String Equip_jambeName;
    private Button Equip_armeDr;
    private String Equip_armeDrName;
    private Button Equip_armeGch;
    private String Equip_armeGchName;
    private Button Equip_accDr;
    private String Equip_accDrName;
    private Button Equip_accGch;
    private String Equip_accGchName;
    //objets choisis
    private Button Objet0;
    private String Objet0Name;
    private Button Objet1;
    private String Objet1Name;
    private Button Objet2;
    private String Objet2Name;
    private Button Objet3;
    private String Objet3Name;

    private TextView Titre_spels;
    private TextView Titre_equipement;
    private TextView Titre_objet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_equipement);
        Simulation();
        Intent intent = getIntent();

        //Generation du hero
        //Liste d'items
        //Liste equipements

        SpriteHero = findViewById(R.id.Sprite_hero);
        Name = findViewById(R.id.NameText);
        PvText = findViewById(R.id.TextPv);


        Titre_spels = findViewById(R.id.titre_liste_skils);
        Titre_equipement=findViewById(R.id.titre_liste_equip);
        Titre_objet=findViewById(R.id.titre_liste_objets);
        //Stats
        IconArmure = findViewById(R.id.armure_icon);
        ValeurArmure = findViewById(R.id.Valeur_armure);
        IconArmuremag = findViewById(R.id.armuremag_icon);
        ValeurArmuremag = findViewById(R.id.Valeur_armuremag);
        IconDegat = findViewById(R.id.degat_icon);
        ValeurDegat = findViewById(R.id.Valeur_degat);
        IconDegatmag = findViewById(R.id.degatmag_icon);
        ValeurDegatmag = findViewById(R.id.Valeur_degatmag);
        IconEsquive = findViewById(R.id.esquive_icon);
        ValeurEsquive = findViewById(R.id.Valeur_esquive);
        //classes
        Classes = findViewById(R.id.Text_classes);

        changeVue(actuHero);
        //btn_retour
        Retour = findViewById(R.id.btn_back);
        //btn_titre
        Skills = findViewById(R.id.btn_open_skill);
        Stuff = findViewById(R.id.btn_open_stuff);
        inv = findViewById(R.id.btn_open_inv);


        Titre_spels.setVisibility(View.GONE);
        List_objets = findViewById(R.id.Liste_objet);
        List_objets.setVisibility(View.GONE);
        Titre_equipement.setVisibility(View.GONE);
        Quit_liste = findViewById(R.id.btn_quit_scroll);
        Quit_liste.setVisibility(View.GONE);
        Titre_objet.setVisibility(View.GONE);
        List_spels = findViewById(R.id.Liste_spel);
        List_spels.setVisibility(View.GONE);

        //Sorts sélectionnés
        Spel0 = findViewById(R.id.btn_Spel0);
        Spel0.setText(actuHero.getSpel0().getNom());
        Spel1 = findViewById(R.id.btn_Spel1);
        Spel1.setText(actuHero.getSpel1().getNom());
        Spel2 = findViewById(R.id.btn_Spel2);
        Spel2.setText(actuHero.getSpel2().getNom());
        Spel3 = findViewById(R.id.btn_Spel3);
        Spel3.setText(actuHero.getSpel3().getNom());

        //stuff équipé
        Equip_tete = findViewById(R.id.btn_equip_tete);
        Equip_teteName = actuHero.getEquipe().get(0).getNom();
        Equip_tete.setText(Equip_teteName);

        Equip_torse = findViewById(R.id.btn_equip_torse);
        Equip_torseName = actuHero.getEquipe().get(1).getNom();
        Equip_torse.setText(Equip_torseName);

        Equip_jambe = findViewById(R.id.btn_equip_jambe);
        Equip_jambeName = actuHero.getEquipe().get(2).getNom();
        Equip_jambe.setText(Equip_jambeName);

        Equip_armeDr = findViewById(R.id.btn_equip_armedr);
        Equip_armeDrName = actuHero.getEquipe().get(3).getNom();
        Equip_armeDr.setText(Equip_armeDrName);

        Equip_armeGch= findViewById(R.id.btn_equip_armegch);
        Equip_armeGchName = actuHero.getEquipe().get(4).getNom();
        Equip_armeGch.setText(Equip_armeGchName);

        Equip_accDr = findViewById(R.id.btn_equip_accdr);
        Equip_accDrName = actuHero.getEquipe().get(5).getNom();
        Equip_accDr.setText(Equip_accDrName);

        Equip_accGch = findViewById(R.id.btn_equip_accgch);
        Equip_accGchName = actuHero.getEquipe().get(6).getNom();
        Equip_accGch.setText(Equip_accGchName);

        //objets équipés
        Objet0 = findViewById(R.id.btn_objet0);
        Objet0Name = actuHero.getUtilitaire0().getNom();
        Objet0.setText(Objet0Name);

        Objet1 = findViewById(R.id.btn_objet1);
        Objet1Name = actuHero.getUtilitaire1().getNom();
        Objet1.setText(Objet1Name);

        Objet2 = findViewById(R.id.btn_objet2);
        Objet2Name = actuHero.getUtilitaire2().getNom();
        Objet2.setText(Objet2Name);

        Objet3 = findViewById(R.id.btn_objet3);
        Objet3Name = actuHero.getUtilitaire3().getNom();
        Objet3.setText(Objet3Name);

        Objet0.setVisibility(View.GONE);
        Objet1.setVisibility(View.GONE);
        Objet2.setVisibility(View.GONE);
        Objet3.setVisibility(View.GONE);

        Equip_tete.setVisibility(View.GONE);
        Equip_torse.setVisibility(View.GONE);
        Equip_jambe.setVisibility(View.GONE);
        Equip_armeDr.setVisibility(View.GONE);
        Equip_armeGch.setVisibility(View.GONE);
        Equip_accDr.setVisibility(View.GONE);
        Equip_accGch.setVisibility(View.GONE);

        RefreshStat();
        StartGestion();
    }

    public void StartGestion()
    {
        Gestion runnable = new Gestion();
        GenerateSkils();
        GeneratebtnItem();
        new Thread(runnable).start();
    }

    class Gestion implements Runnable
    {
        @Override
        public void run()
        {
            Quit=false;
            while(Quit==false)
            {
                Skills.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_skills();
                    }
                });
                //choixSpel
                Spel0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=0;
                        Open_Scroll(Action);
                    }
                });
                Spel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=1;
                        Open_Scroll(Action);
                    }
                });
                Spel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=2;
                        Open_Scroll(Action);
                    }
                });
                Spel3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=3;
                        Open_Scroll(Action);
                    }
                });

                Stuff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_stuff();
                    }
                });
                //choix equipement
                Equip_tete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=0;
                        Open_Scroll(Action);
                        Affichitem(0);
                    }
                });
                Equip_torse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=1;
                        Open_Scroll(Action);
                        Affichitem(1);
                    }
                });
                Equip_jambe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=2;
                        Open_Scroll(Action);
                        Affichitem(2);
                    }
                });
                Equip_armeDr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=3;
                        Open_Scroll(Action);
                        Affichitem(3);
                    }
                });
                Equip_armeGch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=4;
                        Open_Scroll(Action);
                        Affichitem(4);
                    }
                });
                Equip_accDr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=5;
                        Open_Scroll(Action);
                        Affichitem(4);
                    }
                });
                Equip_accGch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        selectedEmplacement=6;
                        Open_Scroll(Action);
                        Affichitem(5);
                    }
                });
                inv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_inv();
                    }
                });
                //choix objet
                Objet0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        selectedEmplacement=0;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Action=3;
                        activeObjet=true;
                        selectedEmplacement=1;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        selectedEmplacement=2;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        selectedEmplacement=3;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });

                Quit_liste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Close_Scroll(Action);
                    }
                });

                Retour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuitThisPlace();
                    }
                });
            }

        }
    }
    public void QuitThisPlace() {
        Intent intent = new Intent(Activite_Equipement.this,
                Activite_Base.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void GenerateSkils()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String actuSpelName="";
        for (int i = 0; i< actuHero.getListdeSpells().size(); i++)
        {
            actuSpelName= actuHero.getListdeSpells().get(i).getNom();
            final Button newBtn = new Button(this);
            newBtn.setWidth(100);
            newBtn.setHeight(80);
            newBtn.setPadding(30,40,0,0);
            newBtn.setText(actuSpelName);
            newBtn.setId(i);
            newBtn.setVisibility(View.VISIBLE);
            final int positionSelect = i;
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    actuHero.changeSpellChoisis(activateSpel, positionSelect);
                    RefreshSpelChoisis();
                    Close_Scroll(Action);
                }
            });
            linearLayout.addView(newBtn);
        }
        List_spels.addView(linearLayout);
    }
    public void GeneratebtnItem()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        String actuItemName="";

        for (int i = 0; i< actuHero.getInventaire().size(); i++)
        {
            actuItemName= actuHero.getInventaire().get(i).getNom();
            final Button newBtn = new Button(this);
            newBtn.setWidth(100);
            newBtn.setHeight(80);
            newBtn.setPadding(30,40,0,0);
            newBtn.setText(actuItemName);
            newBtn.setId(i);
            newBtn.setVisibility(View.VISIBLE);
            final int finalI = i;
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(activeObjet==false)
                    {
                        //Si equipement
                        actuHero.ChangeEquipement(selectedEmplacement, (Equipement) actuHero.getInventaire().get(finalI));
                    }
                    else
                    {
                        //si utilitaire
                        actuHero.changeUtilitaireChoisis(selectedEmplacement, (Utilitaire) actuHero.getInventaire().get(finalI));

                    }
                    Close_Scroll(Action);
                }
            });
            btnItemsTotal.add(newBtn);
            linearLayout.addView(newBtn);
        }
        List_objets.addView(linearLayout);
    }


    public void Open_skills()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.VISIBLE);
                Spel1.setVisibility(View.VISIBLE);
                Spel2.setVisibility(View.VISIBLE);
                Spel3.setVisibility(View.VISIBLE);

                Equip_tete.setVisibility(View.GONE);
                Equip_torse.setVisibility(View.GONE);
                Equip_jambe.setVisibility(View.GONE);
                Equip_armeDr.setVisibility(View.GONE);
                Equip_armeGch.setVisibility(View.GONE);
                Equip_accDr.setVisibility(View.GONE);
                Equip_accGch.setVisibility(View.GONE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);
            }
        });

    }
    public void Open_stuff()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Equip_tete.setVisibility(View.VISIBLE);
                Equip_torse.setVisibility(View.VISIBLE);
                Equip_jambe.setVisibility(View.VISIBLE);
                Equip_armeDr.setVisibility(View.VISIBLE);
                Equip_armeGch.setVisibility(View.VISIBLE);
                Equip_accDr.setVisibility(View.VISIBLE);
                Equip_accGch.setVisibility(View.VISIBLE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);
            }
        });

    }
    public void Open_inv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Equip_tete.setVisibility(View.GONE);
                Equip_torse.setVisibility(View.GONE);
                Equip_jambe.setVisibility(View.GONE);
                Equip_armeDr.setVisibility(View.GONE);
                Equip_armeGch.setVisibility(View.GONE);
                Equip_accDr.setVisibility(View.GONE);
                Equip_accGch.setVisibility(View.GONE);

                Objet0.setVisibility(View.VISIBLE);
                Objet1.setVisibility(View.VISIBLE);
                Objet2.setVisibility(View.VISIBLE);
                Objet3.setVisibility(View.VISIBLE);
            }
        });

    }
    public void Open_Scroll(final int Action)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {

                Quit_liste.setVisibility(View.VISIBLE);
                Retour.setVisibility(View.GONE);
                Skills.setVisibility(View.GONE);
                Stuff.setVisibility(View.GONE);
                inv.setVisibility(View.GONE);
                Name.setVisibility(View.GONE);
                PvText.setVisibility(View.GONE);
                if(Action==1)
                {
                    Spel0.setVisibility(View.GONE);
                    Spel1.setVisibility(View.GONE);
                    Spel2.setVisibility(View.GONE);
                    Spel3.setVisibility(View.GONE);
                    List_spels.setVisibility(View.VISIBLE);
                    Titre_spels.setVisibility(View.VISIBLE);
                }
                else if(Action==2)
                {
                    Equip_tete.setVisibility(View.GONE);
                    Equip_torse.setVisibility(View.GONE);
                    Equip_jambe.setVisibility(View.GONE);
                    Equip_armeDr.setVisibility(View.GONE);
                    Equip_armeGch.setVisibility(View.GONE);
                    Equip_accDr.setVisibility(View.GONE);
                    Equip_accGch.setVisibility(View.GONE);
                    List_objets.setVisibility(View.VISIBLE);
                    Titre_equipement.setVisibility(View.VISIBLE);
                }
                else
                {
                    Objet0.setVisibility(View.GONE);
                    Objet1.setVisibility(View.GONE);
                    Objet2.setVisibility(View.GONE);
                    Objet3.setVisibility(View.GONE);
                    List_objets.setVisibility(View.VISIBLE);
                    Titre_objet.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void Affichitem(int TypeBase)
    {
        for(int compteur=0; compteur < btnItemsTotal.size(); compteur++)
        {
            Button BtnItem = (Button) btnItemsTotal.get(compteur);
            if(actuHero.getInventaire().get(compteur).getCategorie() != TypeBase)
            {
                BtnItem.setVisibility(View.GONE);
            }
            else
            {
                BtnItem.setVisibility(View.VISIBLE);
            }
        }
    }
    public void Close_Scroll(final int Action)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {

                Quit_liste.setVisibility(View.GONE);
                Retour.setVisibility(View.VISIBLE);
                Skills.setVisibility(View.VISIBLE);
                Stuff.setVisibility(View.VISIBLE);
                inv.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                PvText.setVisibility(View.VISIBLE);
                if(Action==1)
                {
                    Spel0.setVisibility(View.VISIBLE);
                    Spel1.setVisibility(View.VISIBLE);
                    Spel2.setVisibility(View.VISIBLE);
                    Spel3.setVisibility(View.VISIBLE);
                    List_spels.setVisibility(View.GONE);
                    Titre_spels.setVisibility(View.GONE);
                }
                else if(Action==2)
                {
                    Equip_tete.setVisibility(View.VISIBLE);
                    Equip_torse.setVisibility(View.VISIBLE);
                    Equip_jambe.setVisibility(View.VISIBLE);
                    Equip_armeDr.setVisibility(View.VISIBLE);
                    Equip_armeGch.setVisibility(View.VISIBLE);
                    Equip_accDr.setVisibility(View.VISIBLE);
                    Equip_accGch.setVisibility(View.VISIBLE);
                    List_objets.setVisibility(View.GONE);
                    Titre_equipement.setVisibility(View.GONE);
                }
                else
                {
                    List_objets.setVisibility(View.GONE);
                    Objet0.setVisibility(View.VISIBLE);
                    Objet1.setVisibility(View.VISIBLE);
                    Objet2.setVisibility(View.VISIBLE);
                    Objet3.setVisibility(View.VISIBLE);
                    Titre_objet.setVisibility(View.GONE);
                }
            }
        });
    }

    public void RefreshSpelChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setText(actuHero.getSpel0().getNom());
                Spel1.setText(actuHero.getSpel1().getNom());
                Spel2.setText(actuHero.getSpel2().getNom());
                Spel3.setText(actuHero.getSpel3().getNom());
            }
        });
    }
    public void RefreshObjetChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Objet0.setText(actuHero.getUtilitaire0().getNom());
                Objet1.setText(actuHero.getUtilitaire1().getNom());
                Objet2.setText(actuHero.getUtilitaire2().getNom());
                Objet3.setText(actuHero.getUtilitaire3().getNom());
            }
        });
    }
    public void RefreshEquipChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Equip_tete.setText(actuHero.getEquipe().get(0).getNom());

                Equip_torse.setText(actuHero.getEquipe().get(1).getNom());

                Equip_jambe.setText(actuHero.getEquipe().get(2).getNom());

                Equip_armeDr.setText(actuHero.getEquipe().get(3).getNom());

                Equip_armeGch.setText(actuHero.getEquipe().get(4).getNom());

                Equip_accDr.setText(actuHero.getEquipe().get(5).getNom());

                Equip_accGch.setText(actuHero.getEquipe().get(6).getNom());

            }
        });
    }
    public void RefreshbtnItem(int place,String oldName)
    {
        Button ItemPris = (Button) btnItemsTotal.get(place);

        ItemPris.setText(oldName);
    }

    //Generation pour simuler db

    public void changeVue(Hero Actu) //Sans incidence de l'inventaire
    {
        PvText.setText(Integer.toString(Actu.getActuHp()));
        Name.setText(Actu.getName());
        SpriteHero.setImageResource(Actu.getImage());
        Classes.setText(""+ Actu.getClasse0() + "  " + Actu.getClasse1());

        ValeurArmure.setText(Integer.toString(Actu.getArmure()));
        ValeurArmuremag.setText(Integer.toString(Actu.getArmureMag()));
        ValeurDegat.setText(Integer.toString(Actu.getDegat()));
        ValeurDegatmag.setText(Integer.toString(Actu.getDegat()));
        ValeurEsquive.setText(Integer.toString(Actu.getEsquive()));

    }


    public void RefreshStat()
    {
        final int TotArmure = (int)((actuHero.getArmure() + actuHero.getArmure_plus())* actuHero.getArmure_mult());
        final int TotArmuremag = (int)((actuHero.getArmureMag() + actuHero.getArmuremag_plus())* actuHero.getArmuremag_mult());
        final int TotDegat = (int)((actuHero.getDegat() + actuHero.getDegat_plus())* actuHero.getDegat_mult());
        final int TotDegatmag = (int)((actuHero.getDegat() + actuHero.getDegatmag_plus())* actuHero.getDegatmag_mult());
        final int TotEsquive = (int)((actuHero.getEsquive() + actuHero.getEsquive_plus())* actuHero.getEsquive_mult());

        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                ValeurArmure.setText("" + TotArmure);
                ValeurArmuremag.setText(""+TotArmuremag);
                ValeurDegat.setText(""+TotDegat);
                ValeurDegatmag.setText(""+TotDegatmag);
                ValeurEsquive.setText(""+TotEsquive);
            }
        });
    }

    public void Simulation()
    {
        Hero bob = new Hero(R.drawable.hero_image_test,60, 60 , 10, 15, 20, 25, 5, 5, 1.2, 7, 1.1, 5, 1.0, 7, 1.3, 8, 1.3, "barbare", 5, "mage", 5, "BoB");

        //GENERATION SPELL
        Spell hspel0 = new Spell( "lame de feu", 0,  10,  2,  true, null, null,false );

        BonusEffect hbonus1 = new BonusEffect( 0,  0,  0,  0.3,  0,  0,  8,  0,  17, 0 );
        Spell hspel1 = new Spell( "aura de feu", 3,  7,  15,  true, hbonus1, null ,false);

        BonusEffect hbonus2 = new BonusEffect( 15,  0.5,  0,  0,  0,  0,  0,  0,  0, 0 );
        Spell hspel2 = new Spell( "potion de feu", 2,  0,  3,  true, hbonus2, null ,false);

        MalusEffect hbonus3 = new MalusEffect( 15,  0.2,  15,  0.2,  0,  0,  0,  0,  0, 0 );
        Spell hspel3 = new Spell( "vomit de feu", 2,  0,  7,  true, null, hbonus3 ,false);

        MalusEffect hbonus4 = new MalusEffect( 15,  0.2,  15,  0.2,  0,  0,  0,  0,  0, 0 );
        Spell hspel4 = new Spell( "arg de feu", 2,  0,  7,  true, null, hbonus3 ,false);

        //ajout spell a hero0
        bob.addSpell(hspel0);
        bob.addinListSpell(hspel0);
        bob.addSpell(hspel1);
        bob.addinListSpell(hspel1);
        bob.addSpell(hspel2);
        bob.addinListSpell(hspel2);
        bob.addSpell(hspel3);
        bob.addinListSpell(hspel3);

        //Creation utilitaire
        Utilitaire utilitaire0 = new Utilitaire( 2,"dague de feu", 0,  10,  2,  true, null, null,false,"C'est un utilitaire");
        Utilitaire utilitaire1 = new Utilitaire( 1,"cape de feu", 3,  7,  15,  true, hbonus1, null,false,"C'est un utilitaire");
        Utilitaire utilitaire2 = new Utilitaire( 3,"potion de feu", 2,  0,  3,  true, hbonus2, null ,false,"C'est un utilitaire");
        Utilitaire utilitaire3 = new Utilitaire( 1,"bile de dragon", 2,  0,  7,  true, null, hbonus3,false,"C'est un utilitaire");

        //ajout utilitaire à hero0
        bob.addUtilitaire(utilitaire0);
        bob.addUtilitaire(utilitaire1);
        bob.addUtilitaire(utilitaire2);
        bob.addUtilitaire(utilitaire3);


        actuHero = bob;
    }

}