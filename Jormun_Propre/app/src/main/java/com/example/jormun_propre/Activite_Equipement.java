package com.example.jormun_propre;

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
        Spel0.setText(actuHero.spel0.nom);
        Spel0 = findViewById(R.id.btn_Spel1);
        Spel0.setText(actuHero.spel1.nom);
        Spel0 = findViewById(R.id.btn_Spel2);
        Spel0.setText(actuHero.spel2.nom);
        Spel0 = findViewById(R.id.btn_Spel3);
        Spel0.setText(actuHero.spel3.nom);

        //stuff équipé
        Equip_tete = findViewById(R.id.btn_equip_tete);
        Equip_teteName = actuHero.Equipe.get(0).nom;
        Equip_tete.setText(Equip_teteName);

        Equip_torse = findViewById(R.id.btn_equip_torse);
        Equip_torseName = actuHero.Equipe.get(1).nom;
        Equip_torse.setText(Equip_torseName);

        Equip_jambe = findViewById(R.id.btn_equip_jambe);
        Equip_jambeName = actuHero.Equipe.get(2).nom;
        Equip_jambe.setText(Equip_jambeName);

        Equip_armeDr = findViewById(R.id.btn_equip_armedr);
        Equip_armeDrName = actuHero.Equipe.get(3).nom;
        Equip_armeDr.setText(Equip_armeDrName);

        Equip_armeGch= findViewById(R.id.btn_equip_armegch);
        Equip_armeGchName = actuHero.Equipe.get(4).nom;
        Equip_armeGch.setText(Equip_armeGchName);

        Equip_accDr = findViewById(R.id.btn_equip_accdr);
        Equip_accDrName = actuHero.Equipe.get(5).nom;
        Equip_accDr.setText(Equip_accDrName);

        Equip_accGch = findViewById(R.id.btn_equip_accgch);
        Equip_accGchName = actuHero.Equipe.get(6).nom;
        Equip_accGch.setText(Equip_accGchName);

        //objets équipés
        Objet0 = findViewById(R.id.btn_objet0);
        Objet0Name = actuHero.utilitaire0.nom;
        Objet0.setText(Objet0Name);

        Objet1 = findViewById(R.id.btn_objet1);
        Objet1Name = actuHero.utilitaire1.nom;
        Objet1.setText(Objet1Name);

        Objet2 = findViewById(R.id.btn_objet2);
        Objet2Name = actuHero.utilitaire2.nom;
        Objet2.setText(Objet2Name);

        Objet3 = findViewById(R.id.btn_objet3);
        Objet3Name = actuHero.utilitaire3.nom;
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
        for (int i=0; i<actuHero.ListdeSpells.size();i++)
        {
            actuSpelName= actuHero.ListdeSpells.get(i).nom;
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

        for (int i=0; i<actuHero.Inventaire.size();i++)
        {
            actuItemName= actuHero.Inventaire.get(i).nom;
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
                        actuHero.ChangeEquipement(selectedEmplacement, (Equipement) actuHero.Inventaire.get(finalI));
                    }
                    else
                    {
                        //si utilitaire
                        actuHero.changeUtilitaireChoisis(selectedEmplacement, (Utilitaire) actuHero.Inventaire.get(finalI));

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
            if(actuHero.Inventaire.get(compteur).categorie != TypeBase)
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
                Spel0.setText(actuHero.spel0.nom);
                Spel0.setText(actuHero.spel1.nom);
                Spel0.setText(actuHero.spel2.nom);
                Spel0.setText(actuHero.spel3.nom);
            }
        });
    }
    public void RefreshObjetChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Objet0.setText(actuHero.utilitaire0.nom);
                Objet1.setText(actuHero.utilitaire1.nom);
                Objet2.setText(actuHero.utilitaire2.nom);
                Objet3.setText(actuHero.utilitaire3.nom);
            }
        });
    }
    public void RefreshEquipChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Equip_tete.setText(actuHero.Equipe.get(0).nom);

                Equip_torse.setText(actuHero.Equipe.get(1).nom);

                Equip_jambe.setText(actuHero.Equipe.get(2).nom);

                Equip_armeDr.setText(actuHero.Equipe.get(3).nom);

                Equip_armeGch.setText(actuHero.Equipe.get(4).nom);

                Equip_accDr.setText(actuHero.Equipe.get(5).nom);

                Equip_accGch.setText(actuHero.Equipe.get(6).nom);

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
        System.out.println(Actu.actuHp);
        PvText.setText(Integer.toString(Actu.actuHp));
        Name.setText(Actu.name);
        SpriteHero.setImageResource(Actu.Image);
        Classes.setText(""+Actu.Classe0 + "  " + Actu.Classe1 );

        ValeurArmure.setText(Integer.toString(Actu.armure));
        ValeurArmuremag.setText(Integer.toString(Actu.armureMag));
        ValeurDegat.setText(Integer.toString(Actu.degat));
        ValeurDegatmag.setText(Integer.toString(Actu.degat));
        ValeurEsquive.setText(Integer.toString(Actu.esquive));

    }


    public void RefreshStat()
    {
        final int TotArmure = (int)((actuHero.armure + actuHero.armure_plus)*actuHero.armure_mult);
        final int TotArmuremag = (int)((actuHero.armureMag + actuHero.armuremag_plus)*actuHero.armuremag_mult);
        final int TotDegat = (int)((actuHero.degat + actuHero.degat_plus)*actuHero.degat_mult);
        final int TotDegatmag = (int)((actuHero.degat + actuHero.degatmag_plus)*actuHero.degatmag_mult);
        final int TotEsquive = (int)((actuHero.esquive + actuHero.esquive_plus)*actuHero.esquive_mult);

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



}