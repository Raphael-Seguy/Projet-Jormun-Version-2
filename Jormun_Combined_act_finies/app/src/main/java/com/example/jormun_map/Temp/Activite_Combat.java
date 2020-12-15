package com.example.jormun_map.Temp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jormun_map.R;
import com.example.jormun_map.model.data_classes.BonusEffect;
import com.example.jormun_map.model.data_classes.Hero;
import com.example.jormun_map.model.data_classes.MalusEffect;
import com.example.jormun_map.model.data_classes.Mob;
import com.example.jormun_map.model.data_classes.Personnage;
import com.example.jormun_map.model.data_classes.Spell;
import com.example.jormun_map.model.data_classes.Utilitaire;

import java.util.Random;

public class Activite_Combat extends MainActivity
{
    private Handler mainHandler = new Handler();
    /////////////////////////////Début définir éléments visuels///////////

    //////////INTERFACE////////
    private Button attack;
    private Button sorts;
    private Button defendre;
    private Button fuite;
    private Button inventaire;
    private Button back;

    private Button esquive;
    private Button parer;

            //btn Sorts//
    private Button Spel0;

    private Button Spel1;

    private Button Spel2;

    private Button Spel3;


            //btn Objets//
    private Button Objet0;

    private Button Objet1;

    private Button Objet2;

    private Button Objet3;

    //Sprites
    private ImageView SpriteHero;
    private ImageView SpriteMob;

    //Fin de jeu
    private TextView FinText;
    private Button retour_carte;

    //Informations
    private ProgressBar HpHero;
    private ProgressBar hpMob;
    private TextView Degat_Icon;
    private TextView Degat_valeur_actu;
    private TextView Degatmag_Icon;
    private TextView Degatmag_valeur_actu;
    private TextView Armure_Icon;
    private TextView Armure_valeur_actu;
    private TextView Armuremag_Icon;
    private TextView Armuremag_valeur_actu;
    private TextView Esquive_Icon;
    private TextView Esquive_valeur_actu;

    private TextView message;
    //////////////////////////Fin définir éléments visuels///////////////

    Random rand = new Random();
    boolean PlayerStop = false;
    // Gameplay
    int Tourdef =0;
    int MobTourdef=0;
    boolean seDefend = false;
    boolean Mobsefend = false;
    boolean Hdefesquive = false;
    boolean fuitereussite = false;

    boolean Mobdefesquive = false;

    /// variables du héros///
    int Hsort_duree_0 = 0;
    int Hsort_duree_1 = 0;
    int Hsort_duree_2 = 0;
    int Hsort_duree_3 = 0;

    int objet_duree_0 = 0;
    int objet_duree_1 = 0;
    int objet_duree_2 = 0;
    int objet_duree_3 = 0;


    /// variables du mob ///
    int Msort_duree_0 = 0;
    int Msort_duree_1 = 0;
    int Msort_duree_2 = 0;
    int Msort_duree_3 = 0;

    ///////acteurs du jeu////
    Hero hero = new Hero();
    Mob mob = new Mob();


    //////////: a la creation /////////////////////

    protected void onCreate(Bundle savedInstanceState)
    {
                ///simulation des valeurs//
            Simulation();


        //phase d'initialisation des données
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_combat);
        // set des Pv
        this.HpHero = findViewById(R.id.PvHero);
        this.hpMob = findViewById(R.id.PvMob);

        this.Degat_Icon = findViewById(R.id.textdegats);
        this.Degat_valeur_actu =findViewById(R.id.textdegats_valeur);
        this.Degatmag_Icon = findViewById(R.id.textdegatmag);
        this.Degatmag_valeur_actu = findViewById(R.id.textdegatmag_valeur);
        this.Armure_Icon = findViewById(R.id.textarmure);
        this.Armure_valeur_actu = findViewById(R.id.textarmure_valeur);
        this.Armuremag_Icon = findViewById(R.id.textarmuremag);
        this.Armuremag_valeur_actu = findViewById(R.id.textarmuremag_valeur);
        this.Esquive_Icon = findViewById(R.id.textesquive);
        this.Esquive_valeur_actu = findViewById(R.id.textesquive_valeur);

        //préparation des boutons
        this.attack = findViewById(R.id.btn_atk);
        this.defendre = findViewById(R.id.btn_def);
        this.sorts = findViewById(R.id.btn_sorts);
        this.fuite = findViewById(R.id.btn_fuite);
        this.inventaire = findViewById(R.id.btn_inv);    // faire le cas de l inventaire
        this.back = findViewById(R.id.btn_retour);
        this.back.setVisibility(View.GONE);

        this.FinText = findViewById(R.id.textEnd);
        this.FinText.setVisibility(View.GONE);
        this.retour_carte = findViewById(R.id.Text_return_map);
        this.retour_carte.setVisibility(View.GONE);


        //préparation des spels choisis             Les effets et nom sont tiré de la db
        this.Spel0 = findViewById(R.id.btn_spel0);
        this.Spel0.setVisibility(View.GONE);
        this.Spel0.setText(""+ hero.getSpel0().getNom());

        this.Spel1 = findViewById(R.id.btn_spel1);
        this.Spel1.setVisibility(View.GONE);
        this.Spel1.setText(""+ hero.getSpel1().getNom());

        this.Spel2 = findViewById(R.id.btn_spel2);
        this.Spel2.setVisibility(View.GONE);
        this.Spel2.setText(""+ hero.getSpel2().getNom());

        this.Spel3 = findViewById(R.id.btn_spel3);
        this.Spel3.setVisibility(View.GONE);
        this.Spel3.setText(""+ hero.getSpel3().getNom());

        ///préparation des objets choisis
        this.Objet0 = findViewById(R.id.btn_objet0);
        this.Objet0.setVisibility(View.GONE);
        this.Objet0.setText(""+ hero.getUtilitaire0().getNom());

        this.Objet1 = findViewById(R.id.btn_objet1);
        this.Objet1.setVisibility(View.GONE);
        this.Objet1.setText(""+ hero.getUtilitaire1().getNom());

        this.Objet2 = findViewById(R.id.btn_objet2);
        this.Objet2.setVisibility(View.GONE);
        this.Objet2.setText(""+ hero.getUtilitaire2().getNom());

        this.Objet3 = findViewById(R.id.btn_objet3);
        this.Objet3.setVisibility(View.GONE);
        this.Objet3.setText(""+ hero.getUtilitaire3().getNom());


        //récupération des spells choisis du héro
        //changer nom btn etc image etc
        //préparation des btns de défense
        this.esquive = findViewById(R.id.btn_def_esq);
        this.esquive.setVisibility(View.GONE);
        this.parer = findViewById(R.id.btn_def_par);
        this.parer.setVisibility(View.GONE);
        //récupération des stats de défense
        //récupérer mais cacher les stats de défense
        //récupération des Sprites du héro et du mob
        this.SpriteHero = findViewById(R.id.SpriteHr);
        this.SpriteMob = findViewById(R.id.SpriteMb);
        //set les sprites

        SpriteHero.setImageResource(hero.getImage());
        SpriteMob.setImageResource(mob.getImage());

        RefreshAllHp();
        refreshStats();
        //fin de la phase de préparation début phase de combat
        StartThreadPlayer();


    }


    //////////////////////////FONCTIONS/////////////////////////////////////////

    //Gestion du thread utilisateur
    public void StartThreadPlayer()
    {
        PlayerRunnable runnable = new PlayerRunnable(true);
        new Thread(runnable).start();
    }
    public void StopThreadPlayer(Runnable runnable)
    {
        new Thread(runnable).stop();
    }



    //Pendant tour du joueur
    class PlayerRunnable implements Runnable
    {
        boolean Playerturn = true;
        PlayerRunnable(boolean state)
        {
            this.Playerturn = state;
        }
        @Override
        public void run()
        {
            message = findViewById(R.id.Text_cmb);

            //on vérifie si des sorts sont toujours en action
            //on vérifie si des objets sont toujours en action
            HEffetsPassif();

            if(Tourdef!=0)
            {
                Tourdef=Tourdef-1;
            }
            // on enlève esquive
            Hdefesquive = false;



            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("tour du joueur");
                }
            });

            //tour durant lequel le joueur peut agir
            while(Playerturn==true)
            {

                //Joueur attaque
                attack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(Mobdefesquive==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Le joueur attaque mais le mob esquive");
                                    }
                                });

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                AttaqueReussit(hero,mob);
                            }
                            Playerturn=false;
                        }
                    }
                });
                //joueur se défend
                defendre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            //affichage des boutons de la défense et on cache les autres
                            attack.setVisibility(View.GONE);
                            defendre.setVisibility(View.GONE);
                            sorts.setVisibility(View.GONE);
                            fuite.setVisibility(View.GONE);
                            inventaire.setVisibility(View.GONE);

                            back.setVisibility(View.VISIBLE);
                            esquive.setVisibility(View.VISIBLE);
                            parer.setVisibility(View.VISIBLE);
                        }
                    }
                });
                //bouton de défense//////////////
                esquive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Mobdefesquive = esquive(hero, mob);

                            Playerturn = false;
                        }
                    }
                });

                parer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Tourdef=1;
                            parade(hero,seDefend);
                            seDefend =true;
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Playerturn = false;
                        }
                    }
                });

                //Joueur choisis un sort ////////////////////////////////////////////////:
                sorts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        attack.setVisibility(View.GONE);
                        defendre.setVisibility(View.GONE);
                        sorts.setVisibility(View.GONE);
                        fuite.setVisibility(View.GONE);
                        inventaire.setVisibility(View.GONE);

                        back.setVisibility(View.VISIBLE);
                        Spel0.setVisibility(View.VISIBLE);
                        Spel1.setVisibility(View.VISIBLE);
                        Spel2.setVisibility(View.VISIBLE);
                        Spel3.setVisibility(View.VISIBLE);

                    }
                });
                ///Sorts de La liste choisie
                Spel0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(Hsort_duree_0 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("ce sort est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {

                                HLance_spell(hero.getSpel0());
                                Hsort_duree_0= hero.getSpel0().getDuree();

                                Playerturn=false;
                            }
                        }
                    }
                });
                Spel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(Hsort_duree_1 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("ce sort est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                HLance_spell(hero.getSpel1());
                                Hsort_duree_1= hero.getSpel1().getDuree();

                                Playerturn=false;
                            }
                        }
                    }
                });
                Spel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(Hsort_duree_2 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("ce sort est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                HLance_spell(hero.getSpel2());
                                Hsort_duree_2= hero.getSpel2().getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
                Spel3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(Hsort_duree_3 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("ce sort est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                HLance_spell(hero.getSpel3());
                                Hsort_duree_3= hero.getSpel3().getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
                ///////////////////////////fin liste sort///////////////////////////
                //fuite//////////////////
                fuite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Random rand = new Random();
                            int tentefuite = rand.nextInt(100);
                            int Diffmoblvl =0;
                            int Diffherolvl =0;
                            if(hero.getLvl() > mob.getLvl())
                            {
                                Diffherolvl = hero.getLvl() - mob.getLvl();
                            }
                            else
                            {
                                Diffmoblvl = mob.getLvl() - hero.getLvl();
                            }
                            if(tentefuite< 50+Diffherolvl-Diffmoblvl)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur parvient à fuir");
                                    }
                                });
                                fuitereussite=true;
                            }
                            else
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur ne parvient pas à fuir");
                                    }
                                });
                            }
                            Playerturn=false;

                        }
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            ecran_de_base();
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("Tour du joueur");
                                }
                            });
                        }
                    }
                });
                //affichage des objets  de l inventaire
                inventaire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        attack.setVisibility(View.GONE);
                        defendre.setVisibility(View.GONE);
                        sorts.setVisibility(View.GONE);
                        fuite.setVisibility(View.GONE);
                        inventaire.setVisibility(View.GONE);

                        back.setVisibility(View.VISIBLE);
                        Objet0.setVisibility(View.VISIBLE);
                        Objet1.setVisibility(View.VISIBLE);
                        Objet2.setVisibility(View.VISIBLE);
                        Objet3.setVisibility(View.VISIBLE);
                    }
                });
                ////////objets équipés //////
                Objet0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(objet_duree_0 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("cet objet est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                hero.setUtilitaire0(Hutilise_objet(hero.getUtilitaire0()));
                                objet_duree_0= hero.getUtilitaire0().sortillege.getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
                Objet1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(objet_duree_1 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("cet objet est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                hero.setUtilitaire1(Hutilise_objet(hero.getUtilitaire1()));
                                objet_duree_1= hero.getUtilitaire1().sortillege.getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
                Objet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(objet_duree_2 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("cet objet est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                hero.setUtilitaire2(Hutilise_objet(hero.getUtilitaire2()));
                                objet_duree_2= hero.getUtilitaire2().sortillege.getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
                Objet3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(objet_duree_3 !=0)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("cet objet est toujours en action !");
                                    }
                                });
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                hero.setUtilitaire3(Hutilise_objet(hero.getUtilitaire3()));
                                objet_duree_3= hero.getUtilitaire3().sortillege.getDuree();
                                Playerturn=false;
                            }
                        }
                    }
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            VerifVictoireHero(mob.getActuHp());
        }
    }

    //Tour du Mob
    class MobRunnable implements Runnable
    {
        public boolean MobTurn;

        MobRunnable (boolean state)
        {
            this.MobTurn=state;
        }

        @Override
        public void run()
        {
            message = findViewById(R.id.Text_cmb);
            MEffetsPassif();

            MobTurn=true;
            if(hero.getActuHp() <0)
            {
                hero.setActuHp(0);
                MobTurn =false;
            }

            while (MobTurn==true)
            {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("tour du mob");
                    }
                });

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /////////choix du mobs//////////

                int prefMagie = rand.nextInt(100);
                prefMagie = prefMagie + mob.getPrefMag();
                int prefDef = rand.nextInt(100);
                prefDef = prefDef + mob.getPrefDef();
                int prefAttack = rand.nextInt(100);
                prefAttack = prefAttack + mob.getPrefAttack();

                if(prefAttack > prefDef && prefAttack > prefMagie) //mob attaque
                {
                    MobTurn=false;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("Le mob attaque");
                        }
                    });
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(Hdefesquive==true)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mais le joueur esquive!");
                            }
                        });
                    }
                    else
                    {
                        AttaqueReussit(mob,hero);
                    }
                }

                else if(prefDef>prefAttack && prefDef > prefMagie) //mob se defend
                {
                    MobTurn=false;
                    int prefpar = rand.nextInt(100);
                    prefpar = prefpar + mob.getPrefPar();
                    int prefEsq = rand.nextInt(100);
                    prefEsq = prefEsq + mob.getPrefEsq();

                    if(prefpar>prefEsq) //mob veut blocker
                    {
                        MobTourdef=1;
                        parade(mob,Mobsefend);
                        Mobsefend =true;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else// mob veut esquiver
                    {
                        Mobdefesquive = esquive(mob, hero);
                    }
                }

                else // Mob lance un sort
                {
                    MobTurn=false;
                    int choixSort = rand.nextInt(4);

                    if(choixSort==3)    //Sort3
                    {
                        MLance_spell(mob.getSpel3());
                        Msort_duree_3= mob.getSpel3().getDuree();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(choixSort==2) //Sort2
                    {
                        MLance_spell(mob.getSpel2());
                        Msort_duree_2= mob.getSpel2().getDuree();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(choixSort==1) //Sort1
                    {
                        MLance_spell(mob.getSpel1());
                        Msort_duree_1= mob.getSpel1().getDuree();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else //sort0
                    {
                        MLance_spell(mob.getSpel0());
                        Msort_duree_0= mob.getSpel0().getDuree();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                MobTurn = false;
                VerifVictoireMob(hero.getActuHp());
            }

        }
    }



    ///////////////fonctions des personnages/////////////////:
    public void refreshStats()
    {
        final int totalDegat = (int)((hero.getDegat() + hero.getDegat_plus())* hero.getDegat_mult());
        final int totalDegatmag = (int)(((hero.getDegat() + hero.getDegatmag_plus())* hero.getDegatmag_mult())*1.5);
        final int totalArmure = (int)((hero.getArmure() + hero.getArmure_plus())* hero.getArmure_mult());
        final int totalArmuremag = (int)((hero.getArmureMag() + hero.getArmuremag_plus())* hero.getArmuremag_mult());
        final int totalEsquive = (int)((hero.getEsquive() + hero.getEsquive_plus())* hero.getEsquive_mult());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Degat_valeur_actu.setText(""+totalDegat);
                Degatmag_valeur_actu.setText(""+totalDegatmag);
                Armure_valeur_actu.setText(""+totalArmure);
                Armuremag_valeur_actu.setText(""+totalArmuremag);
                Esquive_valeur_actu.setText(""+totalEsquive);
            }
        });
    }


    public void HLance_spell(final Spell spellchoisis)
    {

        //Type de sort ?
        if(spellchoisis.getType() %2==0) //offensif
        {
            if(spellchoisis.isMagique() ==true) //sort de nature magique ?
            {
                int damage = (int)(((((hero.getDegat() + hero.getDegatmag_plus())+ spellchoisis.getDegats())* hero.getDegatmag_mult())*1.5) - ((mob.getArmureMag() + mob.getArmuremag_plus())* mob.getArmuremag_mult()));
                if(damage <0)
                {
                    damage =0;
                }

                final int finalDamage = damage;
                if(Mobdefesquive==true)
                {
                    damage = damage/2;
                    int damagereduce = damage;

                    final int finaldamagereduce = damagereduce;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("Le mob tente une esquive mais " + spellchoisis.getNom() + " inflige " + finaldamagereduce +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                        }
                    });

                    mob.setActuHp(mob.getActuHp() - damagereduce);
                }
                else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(spellchoisis.getNom() + " inflige " + finalDamage +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                        }
                    });
                    mob.setActuHp(mob.getActuHp() - damage);
                }
                hero.setActuHp(hero.getActuHp() - (int) (damage / 10));
            }
            else
            {
                if(Mobdefesquive ==true)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("Hero lance " + spellchoisis.getNom() + " mais Mob esquive");
                        }
                    });
                }
                else
                {
                    int damage = (int) ((((hero.getDegat() + hero.getDegat_plus())* hero.getDegat_mult())+ spellchoisis.getDegats()) -  ((mob.getArmure() + mob.getArmure_plus())* mob.getArmure_mult()));
                    if(damage <0)
                    {
                        damage =0;
                    }
                    mob.setActuHp(mob.getActuHp() - damage);
                    final int finalDamage = damage;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(spellchoisis.getNom() + " inflige " + finalDamage +" dégats ");
                        }
                    });
                }
            }
            RefreshMobPv();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellchoisis.getType() %5==0) //heal
        {
            int recup = (int)((hero.getDegat() + spellchoisis.getDegats())*1.3);
            hero.setActuHp(hero.getActuHp() + recup);
            if(hero.getActuHp() > hero.getMaxHP())
            {
                recup = recup - (hero.getActuHp() - hero.getMaxHP());
                hero.setActuHp(hero.getMaxHP());
            }
            final int finalRecup = recup;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("le joueur réucpère " + finalRecup + " pv");
                }
            });
            RefreshHeroPv();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellchoisis.getType() %3==0) //bonus
        {
            String strmess = "le joueur gagne";
            if(spellchoisis.getEffet_Bonus().getArmure_plus() !=0)
            {
                hero.setArmure_plus(hero.getArmure_plus() + spellchoisis.getEffet_Bonus().getArmure_plus());
                strmess= strmess + " +"+ spellchoisis.getEffet_Bonus().getArmure_plus() + " points d'armure!";
            }
            if(spellchoisis.getEffet_Bonus().getArmure_mult() !=0)
            {
                hero.setArmure_mult(hero.getArmure_mult() + spellchoisis.getEffet_Bonus().getArmure_mult());
                strmess= strmess + " +"+ (spellchoisis.getEffet_Bonus().getArmure_mult() *100) + "% d'armure!";
            }
            if(spellchoisis.getEffet_Bonus().getArmureMag_plus() !=0)
            {
                hero.setArmuremag_plus(hero.getArmuremag_plus() + spellchoisis.getEffet_Bonus().getArmureMag_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getArmureMag_plus() + " points d'armure magique!";
            }
            if(spellchoisis.getEffet_Bonus().getArmureMag_mult() !=0)
            {
                hero.setArmuremag_mult(hero.getArmuremag_mult() + spellchoisis.getEffet_Bonus().getArmureMag_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getArmureMag_mult() *100) + "% d'armure magique!";
            }
            if(spellchoisis.getEffet_Bonus().getDegat_plus() !=0)
            {
                hero.setDegat_plus(hero.getDegat_plus() + spellchoisis.getEffet_Bonus().getDegat_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegat_plus() + " points de dégats!";
            }
            if(spellchoisis.getEffet_Bonus().getDegat_mult() !=0)
            {
                hero.setDegat_mult(hero.getDegat_mult() + spellchoisis.getEffet_Bonus().getDegat_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegat_mult() *100) + "% de dégats!";
            }
            if(spellchoisis.getEffet_Bonus().getDegatMag_plus() !=0)
            {
                hero.setDegatmag_plus(hero.getDegatmag_plus() + spellchoisis.getEffet_Bonus().getDegatMag_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegatMag_plus() + " points de dégats magique!";
            }
            if(spellchoisis.getEffet_Bonus().getDegatMag_mult() !=0)
            {
                hero.setDegatmag_mult(hero.getDegatmag_mult() + spellchoisis.getEffet_Bonus().getDegatMag_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegatMag_mult() *100) + "% de dégats magique!";
            }
            if(spellchoisis.getEffet_Bonus().getEsquive_plus() !=0)
            {
                hero.setEsquive_plus(hero.getEsquive_plus() + spellchoisis.getEffet_Bonus().getEsquive_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getEsquive_plus() + "% de chance d'esquive suplémentaire!";
            }
            if(spellchoisis.getEffet_Bonus().getEsquive_mult() !=0)
            {
                hero.setEsquive_mult(hero.getEsquive_mult() + spellchoisis.getEffet_Bonus().getEsquive_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getEsquive_mult() *100) + "% ses chances d'esquiver!";
            }
            final String finalStrmess = strmess;
            message.setText(strmess);
            refreshStats();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellchoisis.getType() %7==0) //malus
        {
            if(spellchoisis.getEffet_Malus().getArmure_moins() !=0)
            {
                mob.setArmure_plus(mob.getArmure_plus() - spellchoisis.getEffet_Malus().getArmure_moins());
            }
            if(spellchoisis.getEffet_Malus().getArmure_div() !=0)
            {
                mob.setArmure_mult(mob.getArmure_mult() - spellchoisis.getEffet_Malus().getArmure_div());
            }
            if(spellchoisis.getEffet_Malus().getArmureMag_moins() !=0)
            {
                mob.setArmuremag_plus(mob.getArmuremag_plus() - spellchoisis.getEffet_Malus().getArmureMag_moins());
            }
            if(spellchoisis.getEffet_Malus().getArmureMag_div() !=0)
            {
                mob.setArmuremag_mult(mob.getArmuremag_mult() - spellchoisis.getEffet_Malus().getArmureMag_div());
            }
            if(spellchoisis.getEffet_Malus().getEsquive_moins() !=0)
            {
                mob.setEsquive_plus(mob.getEsquive_plus() - spellchoisis.getEffet_Malus().getEsquive_moins());
            }
            if(spellchoisis.getEffet_Malus().getEsquive_div() !=0)
            {
                mob.setEsquive_mult(mob.getEsquive_mult() - spellchoisis.getEffet_Malus().getEsquive_div());
            }
            if(spellchoisis.getEffet_Malus().getDegat_moins() !=0)
            {
                mob.setDegat_plus(mob.getDegat_plus() - spellchoisis.getEffet_Malus().getDegat_moins());
            }
            if(spellchoisis.getEffet_Malus().getDegat_div() !=0)
            {
                mob.setDegat_mult(mob.getDegat_mult() - spellchoisis.getEffet_Malus().getDegat_div());
            }
            if(spellchoisis.getEffet_Malus().getDegatMag_moins() !=0)
            {
                mob.setDegatmag_plus(mob.getDegatmag_plus() - spellchoisis.getEffet_Malus().getDegatMag_moins());
            }
            if(spellchoisis.getEffet_Malus().getDegatMag_div() !=0)
            {
                mob.setDegatmag_mult(mob.getDegatmag_mult() - spellchoisis.getEffet_Malus().getDegatMag_div());
            }
        }
    }

    public Utilitaire Hutilise_objet (final Utilitaire objetchoisis)
    {
        Utilitaire objet_final = objetchoisis;

        if(objetchoisis.getQuantite() > 0)
        {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("Joueur utilise " + objetchoisis.getNom() + " !");
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HLance_spell(objetchoisis.sortillege);
            objet_final.setQuantite(objet_final.getQuantite() - 1);
        }
        else
        {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(objetchoisis.getNom() + " n'a plus de charges !");
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return objet_final;
    }

    public void HEffetsPassif()
    {
        //Verif si sort du mobs sur hero toujours en action
        Msort_duree_0 =VerifsortAttaqSurCible(Msort_duree_0, mob.getSpel0(), mob, hero);
        Msort_duree_1 =VerifsortAttaqSurCible(Msort_duree_1, mob.getSpel1(), mob, hero);
        Msort_duree_2 =VerifsortAttaqSurCible(Msort_duree_2, mob.getSpel2(), mob, hero);
        Msort_duree_3 =VerifsortAttaqSurCible(Msort_duree_3, mob.getSpel3(), mob, hero);
        //Verif si sort du hero sur lui-meme toujours en action
        Hsort_duree_0 =VerifSelfBoost(Hsort_duree_0, hero.getSpel0(), hero);
        Hsort_duree_1 =VerifSelfBoost(Hsort_duree_1, hero.getSpel1(), hero);
        Hsort_duree_2 =VerifSelfBoost(Hsort_duree_2, hero.getSpel2(), hero);
        Hsort_duree_3 =VerifSelfBoost(Hsort_duree_3, hero.getSpel3(), hero);
        //Verif si objet du hero sur lui-meme toujours en action
        objet_duree_0 =VerifSelfBoost(objet_duree_0, hero.getUtilitaire0().sortillege, hero);
        objet_duree_1 =VerifSelfBoost(objet_duree_1, hero.getUtilitaire1().sortillege, hero);
        objet_duree_2 =VerifSelfBoost(objet_duree_2, hero.getUtilitaire2().sortillege, hero);
        objet_duree_3 =VerifSelfBoost(objet_duree_3, hero.getUtilitaire3().sortillege, hero);
    }

    public void MEffetsPassif()
    {
        //Verif si sort du mobs sur mobs toujours en action
        Msort_duree_0 =VerifSelfBoost(Msort_duree_0, mob.getSpel0(), mob);
        Msort_duree_1 =VerifSelfBoost(Msort_duree_1, mob.getSpel1(), mob);
        Msort_duree_2 =VerifSelfBoost(Msort_duree_2, mob.getSpel2(), mob);
        Msort_duree_3 =VerifSelfBoost(Msort_duree_3, mob.getSpel3(), mob);
        //Verif si sort du hero sur Mob toujours en action
        Hsort_duree_0 =VerifsortAttaqSurCible(Hsort_duree_0, hero.getSpel0(), hero, mob);
        Hsort_duree_1 =VerifsortAttaqSurCible(Hsort_duree_1, hero.getSpel1(), hero, mob);
        Hsort_duree_2 =VerifsortAttaqSurCible(Hsort_duree_2, hero.getSpel2(), hero, mob);
        Hsort_duree_3 =VerifsortAttaqSurCible(Hsort_duree_3, hero.getSpel3(), hero, mob);
    }

    public int VerifsortAttaqSurCible (int duree, Spell sort, Personnage Attaquant, Personnage Cible)
    {
        int duree_finale = duree;

        if(duree_finale!=0)
        {
            duree_finale-=1;
            if(sort.getType() %2 ==0)
            {
                HMalusPEffectdmg(sort, Attaquant, Cible);
            }
            if(duree_finale ==0 && sort.getType() % 7 ==0)
            {
                HFinEffectMalus(sort, Cible);
            }
        }

        return duree_finale;
    }

    public int VerifSelfBoost(int duree, Spell sort,Personnage beneficiaire)
    {
        int duree_finale = duree;
        if(duree_finale !=0)
        {
            duree_finale -=1;
            if(duree_finale ==0 && sort.getType() %3 ==0)
            {
                HFinEffectBonus(sort, beneficiaire);
            }
        }
        return duree_finale;
    }

    public void HFinEffectBonus(Spell ancienSpellBonus, Personnage beneficiaire)
    {
        String strmess = ancienSpellBonus.getNom() + " cesse de faire effet";

        if(ancienSpellBonus.getEffet_Bonus().getArmure_plus() !=0)
        {
            beneficiaire.setArmure_plus(beneficiaire.getArmure_plus() - ancienSpellBonus.getEffet_Bonus().getArmure_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmure_mult() !=0)
        {
            beneficiaire.setArmure_mult(beneficiaire.getArmure_mult() - ancienSpellBonus.getEffet_Bonus().getArmure_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmureMag_plus() !=0)
        {
            beneficiaire.setArmuremag_plus(beneficiaire.getArmuremag_plus() - ancienSpellBonus.getEffet_Bonus().getArmureMag_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmureMag_mult() !=0)
        {
            beneficiaire.setArmuremag_mult(beneficiaire.getArmuremag_mult() - ancienSpellBonus.getEffet_Bonus().getArmureMag_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegat_plus() !=0)
        {
            beneficiaire.setDegat_plus(beneficiaire.getDegat_plus() - ancienSpellBonus.getEffet_Bonus().getDegat_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegat_mult() !=0)
        {
            beneficiaire.setDegat_mult(beneficiaire.getDegat_mult() - ancienSpellBonus.getEffet_Bonus().getDegat_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegatMag_plus() !=0)
        {
            beneficiaire.setDegatmag_plus(beneficiaire.getDegatmag_plus() - ancienSpellBonus.getEffet_Bonus().getDegatMag_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegatMag_mult() !=0)
        {
            beneficiaire.setDegatmag_mult(beneficiaire.getDegatmag_mult() - ancienSpellBonus.getEffet_Bonus().getDegatMag_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getEsquive_plus() !=0)
        {
            beneficiaire.setEsquive_plus(beneficiaire.getEsquive_plus() - ancienSpellBonus.getEffet_Bonus().getEsquive_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getEsquive_mult() !=0)
        {
            beneficiaire.setEsquive_mult(beneficiaire.getEsquive_mult() - ancienSpellBonus.getEffet_Bonus().getEsquive_mult());
        }
        final String finalStrmess = strmess;
        message.setText(strmess);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void HFinEffectMalus(Spell ancienSpellMalus, Personnage beneficaire)
    {
        String strmess = ancienSpellMalus.getNom() + " cesse de faire effet";

        if(ancienSpellMalus.getEffet_Malus().getArmure_moins() !=0)
        {
            beneficaire.setArmure_plus(beneficaire.getArmure_plus() + ancienSpellMalus.getEffet_Malus().getArmure_moins());
        }
        if(ancienSpellMalus.getEffet_Malus().getArmure_div() !=0)
        {
            beneficaire.setArmure_mult(beneficaire.getArmure_mult() + ancienSpellMalus.getEffet_Malus().getArmure_div());
        }
        if(ancienSpellMalus.getEffet_Malus().getArmureMag_moins() !=0)
        {
            beneficaire.setArmuremag_plus(beneficaire.getArmuremag_plus() + ancienSpellMalus.getEffet_Malus().getArmureMag_moins());
        }
        if(ancienSpellMalus.getEffet_Malus().getArmureMag_div() !=0)
        {
            beneficaire.setArmuremag_mult(beneficaire.getArmuremag_mult() + ancienSpellMalus.getEffet_Malus().getArmureMag_div());
        }
        if(ancienSpellMalus.getEffet_Malus().getEsquive_moins() !=0)
        {
            beneficaire.setEsquive_plus(beneficaire.getEsquive_plus() + ancienSpellMalus.getEffet_Malus().getEsquive_moins());
        }
        if(ancienSpellMalus.getEffet_Malus().getEsquive_div() !=0)
        {
            beneficaire.setEsquive_mult(beneficaire.getEsquive_mult() + ancienSpellMalus.getEffet_Malus().getEsquive_div());
        }
        if(ancienSpellMalus.getEffet_Malus().getDegat_moins() !=0)
        {
            beneficaire.setDegat_plus(beneficaire.getDegat_plus() + ancienSpellMalus.getEffet_Malus().getDegat_moins());
        }
        if(ancienSpellMalus.getEffet_Malus().getDegat_div() !=0)
        {
            beneficaire.setDegat_mult(beneficaire.getDegat_mult() + ancienSpellMalus.getEffet_Malus().getDegat_div());
        }
        if(ancienSpellMalus.getEffet_Malus().getDegatMag_moins() !=0)
        {
            beneficaire.setDegatmag_plus(beneficaire.getDegatmag_plus() + ancienSpellMalus.getEffet_Malus().getDegatMag_moins());
        }
        if(ancienSpellMalus.getEffet_Malus().getDegatMag_div() !=0)
        {
            beneficaire.setDegatmag_mult(beneficaire.getDegatmag_mult() + ancienSpellMalus.getEffet_Malus().getDegatMag_div());
        }

        message.setText(strmess);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void HMalusPEffectdmg(final Spell spellActif, Personnage attaquant, Personnage cible)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                message.setText(spellActif.getNom() + " continue a faire effet");
            }
        });
        //Type de sort ?

        if(spellActif.isMagique() ==true) //sort de nature magique ?
        {
            int damage = (int)(((((attaquant.getDegat() + attaquant.getDegatmag_plus())+ spellActif.getDegats())* attaquant.getDegatmag_mult())*1.5) - ((cible.getArmureMag() + cible.getArmuremag_plus())* cible.getArmuremag_mult()));
            if(damage <0)
            {
                damage =0;
            }

            final int finalDamage = damage;

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(spellActif.getNom() + " inflige " + finalDamage +" dégats! ");
                }
            });
            cible.setActuHp(cible.getActuHp() - damage);
        }
        else
        {
            int damage = (int) ((((attaquant.getDegat() + attaquant.getDegat_plus())* attaquant.getDegat_mult())+ spellActif.getDegats()) -  ((cible.getArmure() + cible.getArmure_plus())* cible.getArmure_mult()));
            if(damage <0)
            {
                damage =0;
            }
            cible.setActuHp(cible.getActuHp() - damage);
            final int finalDamage = damage;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(spellActif.getNom() + " inflige " + finalDamage +" dégats ");
                }
            });

        }
        RefreshAllHp(); // refresh both
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public int VerifPv(int actuHp)
    {
        int final_actuHp = actuHp;
        if(final_actuHp<0)
        {
            final_actuHp = 0;
        }
        return  final_actuHp;
    }

    // fonctions programmes//
    public void ecran_de_base()
    {
        //remettre visible btn_de_base
        attack.setVisibility(View.VISIBLE);
        defendre.setVisibility(View.VISIBLE);
        sorts.setVisibility(View.VISIBLE);
        fuite.setVisibility(View.VISIBLE);
        inventaire.setVisibility(View.VISIBLE);
        //btnretour
        this.back.setVisibility(View.GONE);
        //liste de sorts
        this.Spel0.setVisibility(View.GONE);
        this.Spel1.setVisibility(View.GONE);
        this.Spel2.setVisibility(View.GONE);
        this.Spel3.setVisibility(View.GONE);
        //liste des objets
        this.Objet0.setVisibility((View.GONE));
        this.Objet1.setVisibility(View.GONE);
        this.Objet2.setVisibility(View.GONE);
        this.Objet3.setVisibility(View.GONE);
        //bouton de defense
        this.esquive.setVisibility(View.GONE);
        this.parer.setVisibility(View.GONE);
    }

    public void RefreshHeroPv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                HpHero.setProgress(hero.getActuHp());
            }
        });
    }
    public void RefreshMobPv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hpMob.setProgress(mob.getActuHp());
            }
        });

    }

    public void RefreshAllHp()
    {
        RefreshHeroPv();
        RefreshMobPv();
    }

    ////actions possibles ////

    ///attaque
    public void AttaqueReussit( Personnage attaquant,  Personnage cible)
    {
        final String AttaqName = attaquant.getName();

        int damage = (int)(((attaquant.getDegat() + attaquant.getDegat_plus())* attaquant.getDegat_mult()) - ((cible.getArmure() + cible.getArmure_plus())* cible.getArmure_mult()));
        if(damage <0)
        {
            damage =0;
        }
        cible.setActuHp(cible.getActuHp() - damage);
        cible.setActuHp(VerifPv(cible.getActuHp()));

        final int finalDamage = damage;
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                message.setText(AttaqName +" attaque et inflige " + finalDamage +" dégats");

            }
        });

        RefreshAllHp();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //parer
    public void parade(Personnage cible, boolean result)
    {
        final String CiblName = cible.getName();
        if(result ==false)
        {
            cible.setArmure_mult(cible.getArmure_mult() + 1);
            cible.setArmuremag_mult(cible.getArmuremag_mult() + 1);
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                message.setText(CiblName +" se defend");
            }
        });
    }

    //esquiver
    public boolean esquive(Personnage attaquant, Personnage cible)
    {
        boolean result=false;
        final String AttaqName = attaquant.getName();

        int tentative = rand.nextInt(100);
        if(tentative < ((attaquant.getEsquive() + attaquant.getEsquive_plus())* attaquant.getEsquive_mult()))
        {
            result =true;
        }
        int damage = (int)((((attaquant.getDegat() + attaquant.getDegat_plus())* attaquant.getDegat_mult())/2) - ((cible.getArmure() + cible.getArmure_plus())* cible.getArmure_mult()));
        if(damage <0)
        {
            damage =0;
        }
        cible.setActuHp(cible.getActuHp() - damage);
        cible.setActuHp(VerifPv(cible.getActuHp()));
        final int finalDamage = damage;
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                message.setText(AttaqName +" prépare à esquiver et inflige " + finalDamage + " dégats ");
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RefreshAllHp();

        return result;
    }

    //////sorts mobs
    public void MLance_spell(final Spell spellchoisis)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                message.setText("Mob lance " + spellchoisis.getNom() + " !");
            }
        });
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(spellchoisis.getType() %2==0) //offensif
        {
            if(spellchoisis.isMagique() ==true) //sort de nature magique ?
            {
                int damage = (int)(((((mob.getDegat_mult() + mob.getDegatmag_plus())+ spellchoisis.getDegats())* mob.getDegatmag_mult())*1.5) - ((hero.getArmureMag() + hero.getArmuremag_plus())* hero.getArmuremag_mult()));

                if(damage <0)
                {
                    damage =0;
                }
                hero.setActuHp(hero.getActuHp() - damage);
                hero.setActuHp(VerifPv(hero.getActuHp()));
                final int finalDamage = damage;
                if(Hdefesquive==true)
                {
                    damage = damage/2;

                    hero.setActuHp(hero.getActuHp() - damage);
                    hero.setActuHp(VerifPv(hero.getActuHp()));
                    final int finaldamagereduce = damage;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("hero tente une esquive mais " + spellchoisis.getNom() + " lui inflige " + finaldamagereduce +" dégats ");
                        }
                    });
                }
                else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(spellchoisis.getNom() + " vous inflige " + finalDamage +" dégats ");
                        }
                    });
                    hero.setActuHp(hero.getActuHp() - damage);
                    hero.setActuHp(VerifPv(hero.getActuHp()));
                }
            }
            else
            {
                if(Hdefesquive ==true)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("mob lance " + spellchoisis.getNom() + " mais héro esquive");
                        }
                    });
                }
                else
                {
                    int damage = (int) ((((mob.getDegat() + mob.getDegat_plus())* mob.getDegat_mult())+ spellchoisis.getDegats()) -  ((hero.getArmure() + hero.getArmure_plus())* hero.getArmure_mult()));
                    if(damage <0)
                    {
                        damage =0;
                    }
                    hero.setActuHp(hero.getActuHp() - damage);
                    hero.setActuHp(VerifPv(hero.getActuHp()));
                    final int finalDamage = damage;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(spellchoisis.getNom() + " inflige à héro " + finalDamage +" dégats ");
                        }
                    });
                }
            }
            RefreshHeroPv();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellchoisis.getType() %5==0) //heal
        {
            int recup = (int)((mob.getDegat() + spellchoisis.getDegats())*1.3);
            mob.setActuHp(mob.getActuHp() + recup);
            if(mob.getActuHp() > mob.getMaxHP())
            {
                recup -= (mob.getActuHp() - mob.getMaxHP());
                mob.setActuHp(mob.getMaxHP());
            }
            final int finalRecup = recup;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("le mob réucpère " + finalRecup + " pv");
                }
            });
            RefreshMobPv();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellchoisis.getType() %3==0) //bonus
        {
            String strmess = "le mob gagne";
            if(spellchoisis.getEffet_Bonus().getArmure_plus() !=0)
            {
                mob.setArmure_plus(mob.getArmure_plus() + spellchoisis.getEffet_Bonus().getArmure_plus());
                strmess= strmess + " +"+ spellchoisis.getEffet_Bonus().getArmure_plus() + " points d'armure!";
            }
            if(spellchoisis.getEffet_Bonus().getArmure_mult() !=0)
            {
                mob.setArmure_mult(mob.getArmure_mult() + spellchoisis.getEffet_Bonus().getArmure_mult());
                strmess= strmess + " +"+ (spellchoisis.getEffet_Bonus().getArmure_mult() *100) + "% d'armure!";
            }
            if(spellchoisis.getEffet_Bonus().getArmureMag_plus() !=0)
            {
                mob.setArmuremag_plus(mob.getArmuremag_plus() + spellchoisis.getEffet_Bonus().getArmureMag_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getArmureMag_plus() + " points d'armure magique!";
            }
            if(spellchoisis.getEffet_Bonus().getArmureMag_mult() !=0)
            {
                mob.setArmuremag_mult(mob.getArmuremag_mult() + spellchoisis.getEffet_Bonus().getArmureMag_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getArmureMag_mult() *100) + "% d'armure magique!";
            }
            if(spellchoisis.getEffet_Bonus().getDegat_plus() !=0)
            {
                mob.setDegat_plus(mob.getDegat_plus() + spellchoisis.getEffet_Bonus().getDegat_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegat_plus() + " points de dégats!";
            }
            if(spellchoisis.getEffet_Bonus().getDegat_mult() !=0)
            {
                mob.setDegat_mult(mob.getDegat_mult() + spellchoisis.getEffet_Bonus().getDegat_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegat_mult() *100) + "% de dégats!";
            }
            if(spellchoisis.getEffet_Bonus().getDegatMag_plus() !=0)
            {
                mob.setDegatmag_plus(mob.getDegatmag_plus() + spellchoisis.getEffet_Bonus().getDegatMag_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegatMag_plus() + " points de dégats magique!";
            }
            if(spellchoisis.getEffet_Bonus().getDegatMag_mult() !=0)
            {
                mob.setDegatmag_mult(mob.getDegatmag_mult() + spellchoisis.getEffet_Bonus().getDegatMag_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegatMag_mult() *100) + "% de dégats magique!";
            }
            if(spellchoisis.getEffet_Bonus().getEsquive_plus() !=0)
            {
                mob.setEsquive_plus(mob.getEsquive_plus() + spellchoisis.getEffet_Bonus().getEsquive_plus());
                strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getEsquive_plus() + "% de chance d'esquive suplémentaire!";
            }
            if(spellchoisis.getEffet_Bonus().getEsquive_mult() !=0)
            {
                mob.setEsquive_mult(mob.getEsquive_mult() + spellchoisis.getEffet_Bonus().getEsquive_mult());
                strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getEsquive_mult() *100) + "% ses chances d'esquiver!";
            }
            message.setText(strmess);

        }
        if(spellchoisis.getType() %7==0) //malus
        {
            if(spellchoisis.getEffet_Malus().getArmure_moins() !=0)
            {
                hero.setArmure_plus(hero.getArmure_plus() - spellchoisis.getEffet_Malus().getArmure_moins());
            }
            if(spellchoisis.getEffet_Malus().getArmure_div() !=0)
            {
                hero.setArmure_mult(hero.getArmure_mult() - spellchoisis.getEffet_Malus().getArmure_div());
            }
            if(spellchoisis.getEffet_Malus().getArmureMag_moins() !=0)
            {
                hero.setArmuremag_plus(hero.getArmuremag_plus() - spellchoisis.getEffet_Malus().getArmureMag_moins());
            }
            if(spellchoisis.getEffet_Malus().getArmureMag_div() !=0)
            {
                hero.setArmuremag_mult(hero.getArmuremag_mult() - spellchoisis.getEffet_Malus().getArmureMag_div());
            }
            if(spellchoisis.getEffet_Malus().getEsquive_moins() !=0)
            {
                hero.setEsquive_plus(hero.getEsquive_plus() - spellchoisis.getEffet_Malus().getEsquive_moins());
            }
            if(spellchoisis.getEffet_Malus().getEsquive_div() !=0)
            {
                hero.setEsquive_mult(hero.getEsquive_mult() - spellchoisis.getEffet_Malus().getEsquive_div());
            }
            if(spellchoisis.getEffet_Malus().getDegat_moins() !=0)
            {
                hero.setDegat_plus(hero.getDegat_plus() - spellchoisis.getEffet_Malus().getDegat_moins());
            }
            if(spellchoisis.getEffet_Malus().getDegat_div() !=0)
            {
                hero.setDegat_mult(hero.getDegat_mult() - spellchoisis.getEffet_Malus().getDegat_div());
            }
            if(spellchoisis.getEffet_Malus().getDegatMag_moins() !=0)
            {
                hero.setDegatmag_plus(hero.getDegatmag_plus() - spellchoisis.getEffet_Malus().getDegatMag_moins());
            }
            if(spellchoisis.getEffet_Malus().getDegatMag_div() !=0)
            {
                hero.setDegatmag_mult(hero.getDegatmag_mult() - spellchoisis.getEffet_Malus().getDegatMag_div());
            }
            refreshStats();
        }

    }


    //////fonctions de fin ///////
    public void VerifVictoireHero(int Pv)
    {
        if(Pv<=0)
        {
            EndScreen();
            VictoryScreen();
        }
        else if(fuitereussite==true)
        {
            EndScreen();
            fuiteScreen();
        }
        else
        {
            StartThreadMob();
        }
    }

    public void StartThreadMob()
    {
        MobRunnable runnable = new MobRunnable(true);
        new Thread(runnable).start();
    }

    public void VerifVictoireMob(int Pv)
    {
        if(Pv<=0)
        {
            EndScreen();
            DefetScreen();
        }
        else
        {
            StartThreadPlayer();
        }
    }

    public void DefetScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Defete");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }
    public void EndScreen()
    {
        mainHandler.post(new Runnable() {
            private TextView message;

            @Override
            public void run() {
                HpHero.setVisibility(View.GONE);
                hpMob.setVisibility(View.GONE);
                Degat_Icon.setVisibility(View.GONE);
                Degat_valeur_actu.setVisibility(View.GONE);
                Degatmag_Icon.setVisibility(View.GONE);
                Degatmag_valeur_actu.setVisibility(View.GONE);
                Armure_Icon.setVisibility(View.GONE);
                Armure_valeur_actu.setVisibility(View.GONE);
                Armuremag_Icon.setVisibility(View.GONE);
                Armuremag_valeur_actu.setVisibility(View.GONE);
                Esquive_Icon.setVisibility(View.GONE);
                Esquive_valeur_actu.setVisibility(View.GONE);

                attack.setVisibility(View.GONE);
                sorts.setVisibility(View.GONE);
                defendre.setVisibility(View.GONE);
                fuite.setVisibility(View.GONE);
                inventaire.setVisibility(View.GONE);
                back.setVisibility(View.GONE);

                esquive.setVisibility(View.GONE);
                parer.setVisibility(View.GONE);

                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);

                SpriteHero.setVisibility(View.GONE);
                SpriteMob.setVisibility(View.GONE);
                this.message = findViewById(R.id.Text_cmb);
                this.message.setVisibility(View.GONE);

            }
        });

    }

    public void VictoryScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Victoire");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }

    public void fuiteScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Vous avez fuit le combat");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }

    class CloseCombat implements Runnable
    {
        @Override
        public void run() {
            final boolean[] Out = {false};
            while(Out[0] ==false)
            {
                retour_carte.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Out[0] =true;
                        BackToMap();
                    }
                });
            }

        }
    }
    public void BackToMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


            ///SIMULATION///

    public void Simulation()
    {
            /// HERO
        Hero bob = new Hero(R.drawable.hero_image_test,60, 60 , 10, 15, 20, 25, 5, 5, 1.2, 7, 1.1, 5, 1.0, 7, 1.3, 8, 1.3, "barbare", 5, "mage", 5, "BoB");
        Spell hspel0 = new Spell( "lame de feu", 0,  10,  2,  true, null, null ,false);

        BonusEffect hbonus1 = new BonusEffect( 0,  0,  0,  0.3,  0,  0,  8,  0,  17, 0 );
        Spell hspel1 = new Spell( "aura de feu", 3,  7,  15,  true, hbonus1, null ,false);

        BonusEffect hbonus2 = new BonusEffect( 15,  0.5,  0,  0,  0,  0,  0,  0,  0, 0 );
        Spell hspel2 = new Spell( "potion de feu", 2,  0,  3,  true, hbonus2, null ,false);

        MalusEffect hbonus3 = new MalusEffect( 15,  0.2,  15,  0.2,  0,  0,  0,  0,  0, 0 );
        Spell hspel3 = new Spell( "vomit de feu", 2,  0,  7,  true, null, hbonus3 ,false);

        bob.addSpell(hspel0);
        bob.addinListSpell(hspel0);
        bob.addSpell(hspel1);
        bob.addinListSpell(hspel1);
        bob.addSpell(hspel2);
        bob.addinListSpell(hspel2);
        bob.addSpell(hspel3);
        bob.addinListSpell(hspel3);

        Utilitaire utilitaire0 = new Utilitaire( 2,"dague de feu", 0,  10,  2,  true, null, null,false,"C'est un utilitaire");
        Utilitaire utilitaire1 = new Utilitaire( 1,"cape de feu", 3,  7,  15,  true, hbonus1, null,false,"C'est un utilitaire");
        Utilitaire utilitaire2 = new Utilitaire( 3,"potion de feu", 2,  0,  3,  true, hbonus2, null ,false,"C'est un utilitaire");
        Utilitaire utilitaire3 = new Utilitaire( 1,"bile de dragon", 2,  0,  7,  true, null, hbonus3,false,"C'est un utilitaire");

        bob.addUtilitaire(utilitaire0);
        bob.addUtilitaire(utilitaire1);
        bob.addUtilitaire(utilitaire2);
        bob.addUtilitaire(utilitaire3);

        hero = bob;
            //MOB//

        Mob Franck = new Mob(R.drawable.loup,60, 60 , 10, 15, 20, 25, 5, 5, 1.2, 7, 1.1, 5, 1.0, 7, 1.3, 8, 1.3, 30, 20, 15, 35, 20, "Loup");

        Franck.addSpell(hspel0);
        Franck.addSpell(hspel1);
        Franck.addSpell(hspel2);
        Franck.addSpell(hspel3);

        mob = Franck;
    }
}
