package com.example.jormun_map.Temp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.jormun_map.model.data_classes.Recompense;

import java.util.ArrayList;
import java.util.Random;

public class Activite_Combat_boss extends MainActivity
{
    private Handler mainHandler = new Handler();
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
    private ImageButton SpriteMob;

    private ImageButton hero0;
    private ImageButton hero1;
    private ImageButton hero2;
    private ImageButton hero3;
    private ImageButton hero4;
    private ImageButton hero5;

    //Fin de jeu
    private TextView FinText;
    private Button retour_carte;

    //Informations
    private ProgressBar HpHero_actu;
    private ProgressBar HpHero0;
    private ProgressBar HpHero1;
    private ProgressBar HpHero2;
    private ProgressBar HpHero3;
    private ProgressBar HpHero4;
    private ProgressBar HpHero5;

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
    private View combatLayout;
    private View VictoryLayout;

    private hero_fight actu_hero;
    private ArrayList<hero_fight> List_hero = new ArrayList<hero_fight>();

    private Mob Boss;
    private Boss_fight actu_Boss;

    int iTour=0;
    boolean fuitereussite=false;
    Random rand = new Random();
    Spell spellSelectionne;

    protected void onCreate(Bundle savedInstanceState)
    {
        //phase d'initialisation des données
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_combat_boss);
        Liaison_code_interface();
        Simulation();
        actu_hero=List_hero.get(0);
        SpriteHero.setImageResource(actu_hero.identite.getImage());
        actu_Boss=new Boss_fight(Boss);
        SpriteMob.setImageResource(actu_Boss.identite.getImage());
        hpMob.setMax(actu_Boss.identite.getMaxHP());
        RefreshAllHp();
        refreshStats();
        //fin de la phase de préparation début phase de combat
        StartThreadPlayer();
    }

    //Gestion du thread hero
    public void StartThreadPlayer()
    {
        com.example.jormun_map.Temp.Activite_Combat_boss.PlayerRunnable runnable = new com.example.jormun_map.Temp.Activite_Combat_boss.PlayerRunnable(true);
        new Thread(runnable).start();
    }
    public void StopThreadPlayer(Runnable runnable)
    {
        new Thread(runnable).stop();
    }
    //Generation du thread Boss
    public void StartThreadMob()
    {
        com.example.jormun_map.Temp.Activite_Combat_boss.MobRunnable runnable = new com.example.jormun_map.Temp.Activite_Combat_boss.MobRunnable(true);
        new Thread(runnable).start();
    }


    //Hero
    class PlayerRunnable implements Runnable
    {
        boolean Playerturn = true;
        boolean dejaEnActionSurCible=false;
        PlayerRunnable(boolean state)
        {
            this.Playerturn = state;
        }
        @Override
        public void run()
        {
            Playerturn =endAction(true);
            while (Playerturn==true)
            {
                //Joueur attaque
                attack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            if(actu_Boss.esquive==true) //le mob tente d'esquiver
                            {
                                esquive(actu_hero.identite,Boss,1);

                            }
                            else
                            {
                                AttaqueReussit(actu_hero.identite,Boss,1);
                            }
                            Playerturn =endAction(false);
                        }
                    }
                });

                //joueur veut choisir une option défensive
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

                ///bouton de défense///
                    //esquive
                esquive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            actu_hero.esquive=true;

                            message.setText(actu_hero.identite.getName() +" tente d'esquiveer");
                            message.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            if(actu_Boss.esquive==true) //le mob tente d'esquiver
                            {
                                esquive(actu_hero.identite,Boss,2);

                            }
                            else
                            {
                                AttaqueReussit(actu_hero.identite,Boss,2);
                            }
                            Playerturn =endAction(false);
                        }
                    }
                });
                    //parade
                parer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            actu_hero.Tourdef=1;
                            parade(actu_hero.identite,actu_hero.seDefend);
                            actu_hero.seDefend =true;
                            Playerturn =endAction(false);
                        }
                    }
                });
                ///Fin bouton de défense///

                ///Bouton de sort///
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
                            Cible_selectionnable();
                            spellSelectionne=actu_hero.identite.getSpel0();
                            message.setText(" Choisissez une cible");
                            message.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                Spel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Cible_selectionnable();
                            spellSelectionne=actu_hero.identite.getSpel1();
                            message.setText(" Choisissez une cible");
                            message.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                Spel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Cible_selectionnable();
                            spellSelectionne=actu_hero.identite.getSpel2();
                            message.setText(" Choisissez une cible");
                            message.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                Spel3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Cible_selectionnable();
                            spellSelectionne=actu_hero.identite.getSpel3();
                            message.setText(" Choisissez une cible");
                            message.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                ///Fin liste sort///

                //fuite
                fuite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            Random rand = new Random();
                            int tentefuite = rand.nextInt(100);
                            int Diffmoblvl =0;
                            int Diffherolvl =0;
                            if(actu_hero.identite.getLvl() > actu_Boss.identite.getLvl())
                            {
                                Diffherolvl = actu_hero.identite.getLvl() - actu_Boss.identite.getLvl();
                            }
                            else
                            {
                                Diffmoblvl = actu_Boss.identite.getLvl() - actu_hero.identite.getLvl();
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
                                    message.setText("Tour de " + actu_hero.identite.getName());
                                }
                            });
                            Cible_non_selectionnable();
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
                            actu_hero.identite.setUtilitaire0(Hutilise_objet(actu_hero.identite.getUtilitaire0(),actu_hero));
                        }
                    }
                });
                Objet1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            actu_hero.identite.setUtilitaire1( Hutilise_objet(actu_hero.identite.getUtilitaire1(),actu_hero));
                        }
                    }
                });
                Objet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            actu_hero.identite.setUtilitaire2(Hutilise_objet(actu_hero.identite.getUtilitaire2(),actu_hero));
                        }
                    }
                });
                Objet3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            actu_hero.identite.setUtilitaire3(Hutilise_objet(actu_hero.identite.getUtilitaire3(),actu_hero));
                        }
                    }
                });

                ////Cible des sorts ou objets///
                hero0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(0),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(0),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(0).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }
                    }
                });
                hero1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(1),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(1),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(1).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }

                    }
                });
                hero2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(2),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(2),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(2).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }
                    }
                });
                hero3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(3),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(3),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(3).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }
                    }
                });
                hero4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(4),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(4),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(4).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }
                    }
                });
                hero5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(5),spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,List_hero.get(5),actu_hero);
                                EffetSpellSur(spellSelectionne,List_hero.get(5).identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
                            }
                        }
                    }
                });

                SpriteMob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Playerturn==true)
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(actu_Boss,spellSelectionne);
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParHero(spellSelectionne,actu_Boss,actu_hero);
                                EffetSpellSur(spellSelectionne,actu_Boss.identite,actu_hero.identite);
                                Playerturn =endAction(false);
                            }
                            else
                            {
                                message.setText("Ce sort est déja actif sur la cible");
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
            VerifVictoireHero(actu_Boss.identite.getActuHp());
        }
    }

    public boolean endAction(boolean firstTurn)
    {
        boolean heroTurn=true;
        boolean hero_chosen=false;
        if(firstTurn==false)
        {
            List_hero.set(iTour,actu_hero);
            iTour++;
        }
        while (iTour<List_hero.size()&&hero_chosen==false)
        {
            if(List_hero.get(iTour).identite.getActuHp()<=0)
            {
                iTour++;
            }
            else
            {
                hero_chosen=true;
                actu_hero=List_hero.get(iTour);
            }
        }
        if(iTour>=List_hero.size() || actu_Boss.identite.getActuHp()<=0)
        {
            iTour=0;
            heroTurn=false;
        }
        else
        {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("tour de" +List_hero.get(iTour).identite.getName());
                }
            });
            VerifEffetSpellActifonHero(actu_hero);
            if(actu_hero.Tourdef!=0)
            {
                actu_hero.Tourdef--;
                if(actu_hero.Tourdef==0)
                {
                    actu_hero.identite.armure_mult_loose(1);
                    actu_hero.identite.armuremag_mult_loose(1);
                    actu_hero.seDefend =false;
                }
            }
            actu_hero.esquive=false;
        }

        Refresh_interface_hero();
        refreshStats();
        return  heroTurn;
    }
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

    //Mob
    //Tour du Mob
    class MobRunnable implements Runnable
    {
        public boolean mobWin;

        MobRunnable (boolean state)
        {
            this.mobWin=state;
        }

        @Override
        public void run()
        {
            message = findViewById(R.id.Text_cmb);
            VerifEffetSpellActifonBoss(actu_Boss);
            actu_Boss.esquive = false;

            mobWin=AllHeroDead();
            if (mobWin==false)
            {
                message.setText("tour du " + actu_Boss.identite.getName());
                message.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if(actu_Boss.Tourdef!=0)
                {
                    actu_Boss.Tourdef--;
                    if(actu_Boss.Tourdef==0)
                    {
                        actu_Boss.identite.armure_mult_loose(1);
                        actu_Boss.identite.armuremag_mult_loose(1);
                        actu_Boss.seDefend=false;
                    }
                }


                /////////choix du mobs//////////

                int prefMagie = rand.nextInt(100);
                prefMagie = prefMagie + actu_Boss.identite.getPrefMag();
                int prefDef = rand.nextInt(100);
                prefDef = prefDef + actu_Boss.identite.getPrefDef();
                int prefAttack = rand.nextInt(100);
                prefAttack = prefAttack + actu_Boss.identite.getPrefAttack();
                int cible_aleatoire = rand.nextInt(List_hero.size());

                if(prefAttack > prefDef && prefAttack > prefMagie) //mob attaque
                {
                    message.setText("Le mob attaque");
                    message.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    if(List_hero.get(cible_aleatoire).esquive==true) //le hero tente d'esquiver
                    {
                        esquive(actu_Boss.identite,List_hero.get(cible_aleatoire).identite,1);
                    }
                    else
                    {
                        AttaqueReussit(actu_Boss.identite,List_hero.get(cible_aleatoire).identite,1);
                    }
                }

                else if(prefDef>prefAttack && prefDef > prefMagie) //mob se defend
                {
                    int prefpar = rand.nextInt(100);
                    prefpar = prefpar + actu_Boss.identite.getPrefPar();
                    int prefEsq = rand.nextInt(100);
                    prefEsq = prefEsq + actu_Boss.identite.getPrefEsq();

                    if(prefpar>prefEsq) //mob veut blocker
                    {
                        actu_Boss.Tourdef=1;
                        parade(actu_Boss.identite,actu_Boss.seDefend);
                        actu_Boss.seDefend =true;
                    }
                    else// mob veut esquiver
                    {
                        actu_Boss.esquive = true;
                        message.setText(actu_Boss.identite.getName() +" tente d'esquiver");
                        message.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        if(actu_hero.esquive==true) //le mob tente d'esquiver
                        {
                            esquive(actu_Boss.identite,List_hero.get(cible_aleatoire).identite,2);
                        }
                        else
                        {
                            AttaqueReussit(actu_Boss.identite,List_hero.get(cible_aleatoire).identite,2);
                        }
                    }
                }

                else // Mob lance un sort
                {
                    boolean spellFound=false;
                    boolean dejaEnActionSurCible=false;

                    while(spellFound==false)
                    {
                        int choixSort = rand.nextInt(4);
                        if(choixSort==3)    //Sort3 toujours un boost pour le mob
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(actu_Boss,actu_Boss.identite.getSpel3());
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParMob(actu_Boss.identite.getSpel3(),actu_Boss,actu_Boss);
                                EffetSpellSur(actu_Boss.identite.getSpel3(),actu_Boss.identite,actu_Boss.identite);
                                spellFound=true;
                            }
                        }
                        else if(choixSort==2) //Sort2 toujours un heal
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(actu_Boss,actu_Boss.identite.getSpel2());
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParMob(actu_Boss.identite.getSpel2(),actu_Boss,actu_Boss);
                                EffetSpellSur(actu_Boss.identite.getSpel2(),actu_Boss.identite,actu_Boss.identite);
                                spellFound=true;
                            }
                        }
                        else if(choixSort==1) //Sort1 toujours un effet contre l'adversaire
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(cible_aleatoire),actu_Boss.identite.getSpel1());
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParMob(actu_Boss.identite.getSpel1(),List_hero.get(cible_aleatoire),actu_Boss);
                                EffetSpellSur(actu_Boss.identite.getSpel1(),List_hero.get(cible_aleatoire).identite,actu_Boss.identite);
                                spellFound=true;
                            }
                        }
                        else //sort0 toujours un effet contre l'adversaire
                        {
                            dejaEnActionSurCible=SpellDejaEnAction(List_hero.get(cible_aleatoire),actu_Boss.identite.getSpel0());
                            if(dejaEnActionSurCible==false)
                            {
                                LanceSpellParMob(actu_Boss.identite.getSpel0(),List_hero.get(cible_aleatoire),actu_Boss);
                                EffetSpellSur(actu_Boss.identite.getSpel0(),List_hero.get(cible_aleatoire).identite,actu_Boss.identite);
                                spellFound=true;
                            }
                        }
                    }
                }
                RefreshAllHp();
                VerifVictoireMob();
            }

        }
    }
    public void VerifVictoireMob()
    {
        boolean mobWin=AllHeroDead();
        if(mobWin==true)
        {
            EndScreen();
            DefetScreen();
        }
        else
        {
            StartThreadPlayer();
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText("tour de" +List_hero.get(iTour).identite.getName());
                }
            });
        }
    }

    public boolean AllHeroDead()
    {
        boolean mobWin=true;
        for (int iCompteur=0; iCompteur<List_hero.size();iCompteur++)
        {
            if(List_hero.get(iCompteur).identite.getActuHp()>0)
            {
                mobWin=false;
            }
        }
        return mobWin;
    }

    //fonction Mob
    public void RefreshMobPv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hpMob.setProgress(actu_Boss.identite.getActuHp());
            }
        });

    }


    //fonction hero
    public void RefreshHeroPv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                HpHero_actu.setMax(actu_hero.identite.getMaxHP());
                HpHero_actu.setProgress(actu_hero.identite.getActuHp());
                HpHero0.setMax(List_hero.get(0).identite.getMaxHP());
                HpHero0.setProgress(List_hero.get(0).identite.getActuHp());
                hero0.setImageResource(List_hero.get(0).identite.getImage());
                if(List_hero.size()>1)
                {
                    HpHero1.setMax(List_hero.get(1).identite.getMaxHP());
                    HpHero1.setProgress(List_hero.get(1).identite.getActuHp());
                    hero1.setImageResource(List_hero.get(1).identite.getImage());
                    if(List_hero.size()>2)
                    {
                        HpHero2.setMax(List_hero.get(2).identite.getMaxHP());
                        HpHero2.setProgress(List_hero.get(2).identite.getActuHp());
                        hero2.setImageResource(List_hero.get(2).identite.getImage());
                        if(List_hero.size()>3)
                        {
                            HpHero3.setMax(List_hero.get(3).identite.getMaxHP());
                            HpHero3.setProgress(List_hero.get(3).identite.getActuHp());
                            hero3.setImageResource(List_hero.get(3).identite.getImage());
                            if(List_hero.size()>4)
                            {
                                HpHero4.setMax(List_hero.get(4).identite.getMaxHP());
                                HpHero4.setProgress(List_hero.get(4).identite.getActuHp());
                                hero4.setImageResource(List_hero.get(4).identite.getImage());
                                if(List_hero.size()>5)
                                {
                                    HpHero5.setMax(List_hero.get(5).identite.getMaxHP());
                                    HpHero5.setProgress(List_hero.get(5).identite.getActuHp());
                                    hero5.setImageResource(List_hero.get(5).identite.getImage());
                                }
                                else
                                {
                                    HpHero5.setVisibility(View.GONE);
                                    hero5.setVisibility(View.GONE);
                                }
                            }
                            else
                            {
                                HpHero4.setVisibility(View.GONE);
                                hero4.setVisibility(View.GONE);
                                HpHero5.setVisibility(View.GONE);
                                hero5.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            HpHero3.setVisibility(View.GONE);
                            hero3.setVisibility(View.GONE);
                            HpHero4.setVisibility(View.GONE);
                            hero4.setVisibility(View.GONE);
                            HpHero5.setVisibility(View.GONE);
                            hero5.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        HpHero2.setVisibility(View.GONE);
                        hero2.setVisibility(View.GONE);
                        HpHero3.setVisibility(View.GONE);
                        hero3.setVisibility(View.GONE);
                        HpHero4.setVisibility(View.GONE);
                        hero4.setVisibility(View.GONE);
                        HpHero5.setVisibility(View.GONE);
                        hero5.setVisibility(View.GONE);
                    }
                }
                else
                {
                    HpHero1.setVisibility(View.GONE);
                    hero1.setVisibility(View.GONE);
                    HpHero2.setVisibility(View.GONE);
                    hero2.setVisibility(View.GONE);
                    HpHero3.setVisibility(View.GONE);
                    hero3.setVisibility(View.GONE);
                    HpHero4.setVisibility(View.GONE);
                    hero4.setVisibility(View.GONE);
                    HpHero5.setVisibility(View.GONE);
                    hero5.setVisibility(View.GONE);
                }
            }
        });
    }
    public void refreshStats()
    {
        final int totalDegat = (int)((actu_hero.identite.getDegat()+ actu_hero.identite.getDegat_plus())*actu_hero.identite.getDegat_mult());
        final int totalDegatmag = (int)(((actu_hero.identite.getDegat() + actu_hero.identite.getDegatmag_plus())*actu_hero.identite.getDegatmag_mult())*1.5);
        final int totalArmure = (int)((actu_hero.identite.getArmure()+actu_hero.identite.getArmure_plus())*actu_hero.identite.getArmure_mult());
        final int totalArmuremag = (int)((actu_hero.identite.getArmureMag()+actu_hero.identite.getArmuremag_plus())*actu_hero.identite.getArmuremag_mult());
        final int totalEsquive = (int)((actu_hero.identite.getEsquive()+actu_hero.identite.getEsquive_plus())*actu_hero.identite.getEsquive_mult());
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
    public void LanceSpellParMob(Spell spellChoisis,personnage_fight cible_choisie,Boss_fight lanceur)
    {
        message.setText(lanceur.identite.getName() + " lance" + spellChoisis.getNom() + " sur "+ cible_choisie.identiteMere.getName());
        message.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        if(spellChoisis.getType()%2==0||spellChoisis.getType()%7==0) //offensif et/ou malus
        {
            cible_choisie.addSpell(spellChoisis,lanceur.identite);
        }
        if(spellChoisis.getType()%5==0||spellChoisis.getType()%3==0) //heal et/ou bonus
        {
            if(spellChoisis.isSameTarget()==true)
            {
                cible_choisie.addSpell(spellChoisis,lanceur.identite);
            }
            else
            {
                lanceur.addSpell(spellChoisis,lanceur.identite);
            }
        }
    }
    private boolean SpellDejaEnAction(personnage_fight cible_choisie,Spell spellChoisis)
    {

        boolean dejaEnAction=false;
        for (int iCompteur=0; iCompteur <cible_choisie.spellEnAction.size();iCompteur++)
        {
            if(cible_choisie.spellEnAction.get(iCompteur)==spellChoisis)
            {
                dejaEnAction=true;
            }
        }
        return  dejaEnAction;
    }
    public void LanceSpellParHero(Spell spellChoisis,personnage_fight cible_choisie,hero_fight lanceur)
    {
        message.setText(lanceur.identite.getName() + " lance" + spellChoisis.getNom() + " sur "+ cible_choisie.identiteMere.getName());
        message.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        if(spellChoisis.getType()%2==0||spellChoisis.getType()%7==0) //offensif et/ou malus
        {
            cible_choisie.addSpell(spellChoisis,lanceur.identite);
        }
        if(spellChoisis.getType()%5==0||spellChoisis.getType()%3==0) //heal et/ou bonus
        {
            if(spellChoisis.isSameTarget()==true)
            {
                cible_choisie.addSpell(spellChoisis,lanceur.identite);
            }
            else
            {
                lanceur.addSpell(spellChoisis,lanceur.identite);
            }
        }
    }
    public void EffetSpellSur(Spell spellChoisis,Personnage cible_choisie,Personnage lanceur)
    {
        if(spellChoisis.getType()%2==0)
        {
            if(spellChoisis.isMagique()==true) //sort de nature magique ?
            {
                int damage = (int)(((((lanceur.getDegat()+lanceur.getDegatmag_plus())+spellChoisis.getDegats())*lanceur.getDegatmag_mult())*1.5) - ((cible_choisie.getArmureMag() + cible_choisie.getArmuremag_plus() )*cible_choisie.getArmuremag_mult()));
                if(damage <0)
                {
                    damage =0;
                }

                final int finalDamage = damage;

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        message.setText(spellChoisis.getNom()+ " inflige " + finalDamage +" à "+cible_choisie.getName() +" dégats et  inflige " + (finalDamage/10) + " dégats à " + lanceur.getName());
                    }
                });
                cible_choisie.blessed(damage) ;

                lanceur.blessed((int)(damage/10));
            }
            else
            {

                int damage = (int) ((((lanceur.getDegat()+lanceur.getDegat_plus())*lanceur.getDegat_mult())+spellChoisis.getDegats()) -  ((cible_choisie.getArmure()+cible_choisie.getArmure_plus())*cible_choisie.getArmure_mult()));
                if(damage <0)
                {
                    damage =0;
                }
                cible_choisie.blessed(damage);
                final int finalDamage = damage;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        message.setText(spellChoisis.getNom() + " inflige " + finalDamage +" dégats à "+cible_choisie.getName());
                    }
                });

            }
            RefreshMobPv();//de la cible
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(spellChoisis.getType()%5==0)
        {
            int recup = (int)((lanceur.getDegat()+spellChoisis.getDegats())*1.3);
            if(spellChoisis.isSameTarget()==true)
           {
               cible_choisie.healed(recup);
               if(cible_choisie.getActuHp() > cible_choisie.getMaxHP())
               {
                   recup = recup - (cible_choisie.getActuHp()-cible_choisie.getMaxHP());
               }
               final int finalRecup = recup;
               mainHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       message.setText(cible_choisie.getName() +" réucpère " + finalRecup + " pv");
                   }
               });
           }
           else
           {
               lanceur.healed(recup);
               if(lanceur.getActuHp() > lanceur.getMaxHP())
               {
                   recup = recup - (lanceur.getActuHp()-lanceur.getMaxHP());
               }
               final int finalRecup = recup;
               mainHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       message.setText(lanceur.getName() +" réucpère " + finalRecup + " pv");
                   }
               });
           }
        }
        if(spellChoisis.getType()%3==0)
        {
            if(spellChoisis.isSameTarget()==true)
            {
                Bonus_effect_sur(cible_choisie,spellChoisis);
            }
            else
            {
                Bonus_effect_sur(lanceur,spellChoisis);
            }
        }
        if(spellChoisis.getType()%7==0)
        {
            Malus_effect_sur(cible_choisie,spellChoisis);
        }

        RefreshAllHp();


    }
    public void Bonus_effect_sur(Personnage cible,Spell spellchoisis)
    {
        String strmess = cible.getName() + " gagne";
        if(spellchoisis.getEffet_Bonus().getArmure_plus()!=0)
        {
            cible.armure_plus_Gagne(spellchoisis.getEffet_Bonus().getArmure_plus());
            strmess= strmess + " +"+ spellchoisis.getEffet_Bonus().getArmure_plus()+ " points d'armure!";
        }
        if(spellchoisis.getEffet_Bonus().getArmure_mult()!=0)
        {
            cible.armure_mult_Gagne(spellchoisis.getEffet_Bonus().getArmure_mult());
            strmess= strmess + " +"+ (spellchoisis.getEffet_Bonus().getArmure_mult()*100) + "% d'armure!";
        }
        if(spellchoisis.getEffet_Bonus().getArmureMag_plus() !=0)
        {
            cible.armuremag_plus_Gagne(spellchoisis.getEffet_Bonus().getArmureMag_plus());
            strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getArmureMag_plus()+ " points d'armure magique!";
        }
        if(spellchoisis.getEffet_Bonus().getArmureMag_mult() !=0)
        {
            cible.armuremag_mult_Gagne(spellchoisis.getEffet_Bonus().getArmureMag_mult());
            strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getArmureMag_mult()*100) + "% d'armure magique!";
        }
        if(spellchoisis.getEffet_Bonus().getDegat_plus() !=0)
        {
            cible.degat_plus_Gagne(spellchoisis.getEffet_Bonus().getDegat_plus());
            strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegat_plus()+ " points de dégats!";
        }
        if(spellchoisis.getEffet_Bonus().getDegat_mult() !=0)
        {
            cible.degat_mult_Gagne(spellchoisis.getEffet_Bonus().getDegat_mult());
            strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegat_mult()*100) + "% de dégats!";
        }
        if(spellchoisis.getEffet_Bonus().getDegatMag_plus() !=0)
        {
            cible.degatmag_plus_Gagne(spellchoisis.getEffet_Bonus().getDegatMag_plus());
            strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getDegatMag_plus()+ " points de dégats magique!";
        }
        if(spellchoisis.getEffet_Bonus().getDegatMag_mult() !=0)
        {
            cible.degatmag_mult_Gagne(spellchoisis.getEffet_Bonus().getDegatMag_mult());
            strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getDegatMag_mult()*100) + "% de dégats magique!";
        }
        if(spellchoisis.getEffet_Bonus().getEsquive_plus() !=0)
        {
            cible.esquive_plus_Gagne(spellchoisis.getEffet_Bonus().getEsquive_plus());
            strmess= strmess + " +" + spellchoisis.getEffet_Bonus().getEsquive_plus()+ "% de chance d'esquive suplémentaire!";
        }
        if(spellchoisis.getEffet_Bonus().getEsquive_mult() !=0)
        {
            cible.esquive_mult_Gagne(spellchoisis.getEffet_Bonus().getEsquive_mult());
            strmess= strmess + " +" + (spellchoisis.getEffet_Bonus().getEsquive_mult()*100) + "% ses chances d'esquiver!";
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
    public void Malus_effect_sur(Personnage cible,Spell spellchoisis)
    {
        String strmess = cible.getName() + " perd";
        if(spellchoisis.getEffet_Malus().getArmure_moins()!=0)
        {
            cible.armure_plus_loose(spellchoisis.getEffet_Malus().getArmure_moins());
            strmess= strmess + " +"+ spellchoisis.getEffet_Malus().getArmure_moins()+ " points d'armure!";
        }
        if(spellchoisis.getEffet_Malus().getArmure_div()!=0)
        {
            cible.armure_mult_loose(spellchoisis.getEffet_Malus().getArmure_div());
            strmess= strmess + " +"+ (spellchoisis.getEffet_Malus().getArmure_div()*100) + "% d'armure!";
        }
        if(spellchoisis.getEffet_Malus().getArmureMag_moins()!=0)
        {
            cible.armuremag_plus_loose(spellchoisis.getEffet_Malus().getArmureMag_moins());
            strmess= strmess + " +" + spellchoisis.getEffet_Malus().getArmureMag_moins() + " points d'armure magique!";
        }
        if(spellchoisis.getEffet_Malus().getArmureMag_div() !=0)
        {
            cible.armuremag_mult_loose(spellchoisis.getEffet_Malus().getArmureMag_div());
            strmess= strmess + " +" + (spellchoisis.getEffet_Malus().getArmureMag_div()*100) + "% d'armure magique!";
        }
        if(spellchoisis.getEffet_Malus().getEsquive_moins()!=0)
        {
            cible.esquive_plus_loose(spellchoisis.getEffet_Malus().getEsquive_moins());
            strmess= strmess + " +" + spellchoisis.getEffet_Malus().getEsquive_moins()+ "% de chance d'esquive suplémentaire!";
        }
        if(spellchoisis.getEffet_Malus().getEsquive_div()!=0)
        {
            cible.esquive_mult_loose(spellchoisis.getEffet_Malus().getEsquive_div());
            strmess= strmess + " +" + (spellchoisis.getEffet_Malus().getEsquive_div()*100) + "% ses chances d'esquiver!";
        }
        if(spellchoisis.getEffet_Malus().getDegat_moins()!=0)
        {
            cible.degat_plus_loose(spellchoisis.getEffet_Malus().getDegat_moins());
            strmess= strmess + " +" +spellchoisis.getEffet_Malus().getDegat_moins()+ " points de dégats!";
        }
        if(spellchoisis.getEffet_Malus().getDegat_div()!=0)
        {
            cible.degat_mult_loose(spellchoisis.getEffet_Malus().getDegat_div());
            strmess= strmess + " +" + (spellchoisis.getEffet_Malus().getDegat_div()*100) + "% de dégats!";
        }
        if(spellchoisis.getEffet_Malus().getDegatMag_moins()!=0)
        {
            cible.degatmag_plus_loose(spellchoisis.getEffet_Malus().getDegatMag_moins());
            strmess= strmess + " +" + spellchoisis.getEffet_Malus().getDegatMag_moins()+ " points de dégats magique!";
        }
        if(spellchoisis.getEffet_Malus().getDegatMag_div()!=0)
        {
            cible.degatmag_mult_loose(spellchoisis.getEffet_Malus().getDegatMag_div());
            strmess= strmess + " +" + (spellchoisis.getEffet_Malus().getDegatMag_div()*100) + "% de dégats magique!";
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
    public Utilitaire Hutilise_objet (final Utilitaire objetchoisis, hero_fight lanceur)
    {
        Utilitaire objet_final = objetchoisis;

        if(objetchoisis.getQuantite() > 0)
        {
            message.setText(lanceur.identite.getName() + " utilise " + objetchoisis.getNom()+ " !");
            message.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            message.setText("choisissez une cible !");
            message.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Cible_selectionnable();
            spellSelectionne=objetchoisis.sortillege;
            objet_final.setQuantite(objet_final.getQuantite()-1);        }
        else
        {
            message.setText(objetchoisis.getNom()+ " n'a plus de charges !");
            message.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return objet_final;
    }
    public void Cible_non_selectionnable()
    {
        hero0.setEnabled(false);
        hero1.setEnabled(false);
        hero2.setEnabled(false);
        hero3.setEnabled(false);
        hero4.setEnabled(false);
        hero5.setEnabled(false);
    }
    public void Cible_selectionnable()
    {
        hero0.setEnabled(true);
        hero1.setEnabled(true);
        hero2.setEnabled(true);
        hero3.setEnabled(true);
        hero4.setEnabled(true);
        hero5.setEnabled(true);
    }

    //fontion general

    public void VerifEffetSpellActifonBoss(Boss_fight actu_personnage)
    {
        boolean Lanceur_est_actu;
        Personnage lanceur;
        Mob cible;
        for (int iCompteur=0;iCompteur<actu_personnage.spellEnAction.size();iCompteur++)
        {
            Lanceur_est_actu=false;
            lanceur=actu_personnage.LanceurspellEnAction.get(iCompteur);
            cible=actu_personnage.identite;
            Spell spellChoisis = actu_personnage.spellEnAction.get(iCompteur);

            if(lanceur==cible)
            {
                Lanceur_est_actu=true;
            }

            if(Lanceur_est_actu==true ||actu_personnage.spellEnAction.get(iCompteur).isSameTarget()==true) // si les bonus et soins lui sont destinés
            {
                if(spellChoisis.getType()%5==0)
                {
                    int recup = (int)((lanceur.getDegat()+spellChoisis.getDegats())*1.3);
                    cible.healed(recup);
                    if(cible.getActuHp() > cible.getMaxHP())
                    {
                        recup = recup - (cible.getActuHp()-cible.getMaxHP());
                    }

                }
                if(spellChoisis.getType()%3==0)
                {
                    Bonus_effect_sur(cible,spellChoisis);
                }
            }

            if(spellChoisis.getType()%2==0)
            {

                int damage;
                if(spellChoisis.isMagique()==true) //sort de nature magique ?
                {
                    damage = (int) (((((lanceur.getDegat() + lanceur.getDegatmag_plus()) + spellChoisis.getDegats()) * lanceur.getDegatmag_mult()) * 1.5) - ((cible.getArmureMag() + cible.getArmuremag_plus()) * cible.getArmuremag_mult()));
                }
                else
                {
                    damage = (int) ((((lanceur.getDegat() + lanceur.getDegat_plus()) * lanceur.getDegat_mult()) + spellChoisis.getDegats()) - ((cible.getArmure() + cible.getArmure_plus()) * cible.getArmure_mult()));
                }
                if(damage <0)
                {
                    damage =0;
                }
                cible.blessed(damage);
            }
            if(spellChoisis.getType()%7==0)
            {
                Malus_effect_sur(cible,spellChoisis);
            }
            actu_personnage.identite=cible;
        }
    }
    public void VerifEffetSpellActifonHero(hero_fight actu_personnage)
    {
        boolean Lanceur_est_actu;
        Personnage lanceur;
        Hero cible;
        for (int iCompteur=0;iCompteur<actu_personnage.spellEnAction.size();iCompteur++)
        {
            actu_personnage.spellEnAction.get(iCompteur).setDuree(actu_personnage.spellEnAction.get(iCompteur).getDuree()-1);
            Lanceur_est_actu=false;
            lanceur=actu_personnage.LanceurspellEnAction.get(iCompteur);
            cible=actu_personnage.identite;
            Spell spellChoisis = actu_personnage.spellEnAction.get(iCompteur);

            if(lanceur==cible)
            {
                Lanceur_est_actu=true;
            }

            if(Lanceur_est_actu==true ||actu_personnage.spellEnAction.get(iCompteur).isSameTarget()==true) // si les bonus et soins lui sont destinés
            {
                if(spellChoisis.getType()%5==0)
                {
                    int recup = (int)((lanceur.getDegat()+spellChoisis.getDegats())*1.3);
                    cible.healed(recup);
                    if(cible.getActuHp() > cible.getMaxHP())
                    {
                        recup = recup - (cible.getActuHp()-cible.getMaxHP());
                    }

                }
                if(spellChoisis.getType()%3==0 && actu_personnage.spellEnAction.get(iCompteur).getDuree()==0)
                {
                    HFinEffectBonus(spellChoisis,cible);
                }
            }

            if(spellChoisis.getType()%2==0) //dégats
            {

                int damage;
                if(spellChoisis.isMagique()==true) //sort de nature magique ?
                {
                    damage = (int) (((((lanceur.getDegat() + lanceur.getDegatmag_plus()) + spellChoisis.getDegats()) * lanceur.getDegatmag_mult()) * 1.5) - ((cible.getArmureMag() + cible.getArmuremag_plus()) * cible.getArmuremag_mult()));
                }
                else
                {
                    damage = (int) ((((lanceur.getDegat() + lanceur.getDegat_plus()) * lanceur.getDegat_mult()) + spellChoisis.getDegats()) - ((cible.getArmure() + cible.getArmure_plus()) * cible.getArmure_mult()));
                }
                if(damage <0)
                {
                    damage =0;
                }
                cible.blessed(damage);
            }
            if(spellChoisis.getType()%7==0 && actu_personnage.spellEnAction.get(iCompteur).getDuree()==0) //malus
            {
                HFinEffectMalus(spellChoisis,cible);
            }
            actu_personnage.identite=cible;

        }
    }

    public void HFinEffectBonus(Spell ancienSpellBonus, Personnage beneficiaire)
    {
        String strmess = ancienSpellBonus.getNom() + " cesse de faire effet";

        if(ancienSpellBonus.getEffet_Bonus().getArmure_plus() !=0)
        {
            beneficiaire.armure_plus_loose(ancienSpellBonus.getEffet_Bonus().getArmure_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmure_mult() !=0)
        {
            beneficiaire.armure_mult_loose(ancienSpellBonus.getEffet_Bonus().getArmure_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmureMag_plus() !=0)
        {
            beneficiaire.armuremag_plus_loose(ancienSpellBonus.getEffet_Bonus().getArmureMag_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getArmureMag_mult() !=0)
        {
            beneficiaire.armuremag_mult_loose(ancienSpellBonus.getEffet_Bonus().getArmureMag_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegat_plus() !=0)
        {
            beneficiaire.degat_plus_loose(ancienSpellBonus.getEffet_Bonus().getDegat_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegat_mult() !=0)
        {
            beneficiaire.degat_mult_loose(ancienSpellBonus.getEffet_Bonus().getDegat_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegatMag_plus() !=0)
        {
            beneficiaire.degatmag_plus_loose(ancienSpellBonus.getEffet_Bonus().getDegatMag_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getDegatMag_mult()!=0)
        {
            beneficiaire.degatmag_mult_loose(ancienSpellBonus.getEffet_Bonus().getDegatMag_mult());
        }
        if(ancienSpellBonus.getEffet_Bonus().getEsquive_plus() !=0)
        {
            beneficiaire.esquive_plus_loose(ancienSpellBonus.getEffet_Bonus().getEsquive_plus());
        }
        if(ancienSpellBonus.getEffet_Bonus().getEsquive_mult() !=0)
        {
            beneficiaire.esquive_mult_loose(ancienSpellBonus.getEffet_Bonus().getEsquive_mult());
        }
        final String finalStrmess = strmess;
        message.setText(strmess);

        message.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void RefreshAllHp()
    {
        RefreshHeroPv();
        RefreshMobPv();
    }
    //parer
    public void parade(Personnage cible, boolean result)
    {
        final String CiblName = cible.getName();
        if(result ==false)
        {
            cible.armure_mult_Gagne(1);
            cible.armuremag_mult_Gagne(1);
        }
        refreshStats();
        message.setText(CiblName +" se defend");
        message.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //esquiver
    public boolean esquive(Personnage attaquant, Personnage cible,int diviseur)
    {
        boolean result=false;
        final String CibleName = cible.getName();

        int tentative = rand.nextInt(100);
        if(tentative < ((cible.getEsquive()+cible.getEsquive_plus())*cible.getEsquive_mult()))
        {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(CibleName +" esquive l'attaque");
                }
            });
            message.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else
        {
            AttaqueReussit(attaquant,cible,diviseur);
        }
        RefreshAllHp();

        return result;
    }
    ///attaque
    public void AttaqueReussit( Personnage attaquant,  Personnage cible,int diviseur)
    {
        final String AttaqName = attaquant.getName();

        int damage = (int)(((attaquant.getDegat()+attaquant.getDegat_plus())*attaquant.getDegat_mult()) - ((cible.getArmure()+cible.getArmure_plus())*cible.getArmure_mult()));
        if(damage <0)
        {
            damage =0;
        }
        damage=damage/diviseur;
        cible.blessed(damage);
        cible.setActuHp(VerifPv(cible.getActuHp()));

        final int finalDamage = damage;
        message.setText(AttaqName +" attaque et inflige " + finalDamage +" dégats à "+ cible.getName());
        message.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        RefreshAllHp();

    }

    //Verifie que hp >=0
    public int VerifPv(int actuHp)
    {
        int final_actuHp = actuHp;
        if(final_actuHp<0)
        {
            final_actuHp = 0;
        }
        return  final_actuHp;
    }

    //fonction affichage
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

    public void Liaison_code_interface()
    {
        this.HpHero_actu = findViewById(R.id.PvHero_actu);
        this.HpHero0=findViewById(R.id.pvHero0);
        this.HpHero1=findViewById(R.id.pvHero1);
        this.HpHero2=findViewById(R.id.pvHero2);
        this.HpHero3=findViewById(R.id.pvHero3);
        this.HpHero4=findViewById(R.id.pvHero4);
        this.HpHero5=findViewById(R.id.pvHero5);
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

        this.Spel1 = findViewById(R.id.btn_spel1);
        this.Spel1.setVisibility(View.GONE);

        this.Spel2 = findViewById(R.id.btn_spel2);
        this.Spel2.setVisibility(View.GONE);

        this.Spel3 = findViewById(R.id.btn_spel3);
        this.Spel3.setVisibility(View.GONE);

        ///préparation des objets choisis
        this.Objet0 = findViewById(R.id.btn_objet0);
        this.Objet0.setVisibility(View.GONE);

        this.Objet1 = findViewById(R.id.btn_objet1);
        this.Objet1.setVisibility(View.GONE);

        this.Objet2 = findViewById(R.id.btn_objet2);
        this.Objet2.setVisibility(View.GONE);

        this.Objet3 = findViewById(R.id.btn_objet3);
        this.Objet3.setVisibility(View.GONE);

        this.hero0 = findViewById(R.id.btn_hero0);
        this.hero1 = findViewById(R.id.btn_hero1);
        this.hero2 = findViewById(R.id.btn_hero2);
        this.hero3 = findViewById(R.id.btn_hero3);
        this.hero4 = findViewById(R.id.btn_hero4);
        this.hero5 = findViewById(R.id.btn_hero5);
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

        this.message = findViewById(R.id.Text_cmb);
        this.combatLayout = findViewById(R.id.Combat_layout);
        this.VictoryLayout = findViewById(R.id.EndLayout);
        VictoryLayout.setVisibility(View.GONE);
        combatLayout.setVisibility(View.VISIBLE);
        Cible_non_selectionnable();
    }
    public void Refresh_interface_hero()
    {

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                HpHero_actu.setProgress(actu_hero.identite.getActuHp()/actu_hero.identite.getMaxHP()*100);
                Spel0.setText(""+actu_hero.identite.getSpel0().getNom());
                Spel1.setText(""+actu_hero.identite.getSpel1().getNom());
                Spel2.setText(""+actu_hero.identite.getSpel2().getNom());
                Spel3.setText(""+actu_hero.identite.getSpel3().getNom());

                Objet0.setText(""+actu_hero.identite.getUtilitaire0().getNom());
                Objet1.setText(""+actu_hero.identite.getUtilitaire1().getNom());
                Objet2.setText(""+actu_hero.identite.getUtilitaire2().getNom());
                Objet3.setText(""+actu_hero.identite.getUtilitaire3().getNom());

                SpriteHero.setImageResource(actu_hero.identite.getImage());
            }
        });
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
        com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat runnable = new com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat();
        new Thread(runnable).start();
    }
    public void EndScreen()
    {
        mainHandler.post(new Runnable() {
            private TextView message;

            @Override
            public void run() {
               combatLayout.setVisibility(View.GONE);

            }
        });

    }

    public void VictoryScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                VictoryLayout.setVisibility(View.VISIBLE);
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Victoire");
                VictoryItem();
            }
        });
        com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat runnable = new com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat();
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
        com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat runnable = new com.example.jormun_map.Temp.Activite_Combat_boss.CloseCombat();
        new Thread(runnable).start();
    }

    public void VictoryItem()
    {
        int chanceDobtenir;
        int quantiteObtenue;
        int seuil;
        boolean stop=false;
        Recompense recompenseObtenue;
        for (int iCompteur=0; iCompteur < actu_Boss.identite.getRecompenses_possibles().size();iCompteur++)
        {
            chanceDobtenir = rand.nextInt(100);
            seuil=(80 /actu_Boss.identite.getRecompenses_possibles().get(iCompteur).getRarete());
            if(chanceDobtenir< seuil )
            {
                quantiteObtenue=1;
                while(stop=false)
                {
                    seuil=seuil/2;
                    if(chanceDobtenir< seuil && quantiteObtenue<5)
                    {
                        quantiteObtenue++;
                    }
                    else
                    {
                        stop=true;
                    }
                }
                recompenseObtenue = actu_Boss.identite.getRecompenses_possibles().get(iCompteur);
                recompenseObtenue.setQuantite(quantiteObtenue);
                List_hero.get(0).identite.addinInventaire(recompenseObtenue);
            }
        }
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    class personnage_fight
    {
        ArrayList<Spell>spellEnAction=new ArrayList<Spell>();
        Personnage identiteMere;
        ArrayList<Personnage>LanceurspellEnAction=new ArrayList<Personnage>();
        int Tourdef=0;
        boolean esquive=false;
        boolean seDefend=false;
        public personnage_fight(Personnage Mereidentite)
        {
            identiteMere = Mereidentite;
        }
        public void  addSpell(Spell spell_cible,Personnage Lanceur)
        {
            spellEnAction.add(spell_cible);
            LanceurspellEnAction.add(Lanceur);
        }
    }
    //Classe héro_fight
    class hero_fight extends personnage_fight
    {
        Hero identite;
        public hero_fight(Hero hero_cible)
        {
            super(hero_cible);
            identite = hero_cible;
        }
    }
    //class mob fight
    class Boss_fight extends personnage_fight
    {
        Mob identite;
        public Boss_fight(Mob mob_cible)
        {
            super(mob_cible);
            identite = mob_cible;
        }
    }

    //SIMULATION
    public void Simulation()
    {
        Hero bob = new Hero(R.drawable.hero_image_test,60, 60 , 10, 15, 20, 25, 5, 5, 1.2, 7, 1.1, 5, 1.0, 7, 1.3, 8, 1.3, "barbare", 5, "mage", 5, "BoB");
        Hero bobette = new Hero(R.drawable.liche,40, 40 , 10, 18, 10, 28, 15, 7, 1.2, 7, 1.1, 5, 1.0, 18, 1.4, 8, 1.3, "barbare", 5, "mage", 5, "BoBette");

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
        //ajout spell a hero1
        bobette.addSpell(hspel0);
        bobette.addinListSpell(hspel0);
        bobette.addSpell(hspel1);
        bobette.addinListSpell(hspel1);
        bobette.addSpell(hspel2);
        bobette.addinListSpell(hspel2);
        bobette.addSpell(hspel4);
        bobette.addinListSpell(hspel4);

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
        //ajout utilitaire à hero1
        bobette.addUtilitaire(utilitaire0);
        bobette.addUtilitaire(utilitaire1);
        bobette.addUtilitaire(utilitaire2);
        bobette.addUtilitaire(utilitaire3);

        List_hero.add(new hero_fight(bob));
        List_hero.add(new hero_fight(bobette));
        //MOB//
        Recompense Recomp1 = new Recompense( "Nom1",  "c est une recompense de combat 1",  1,  2, 1);
        //creation mob
        Mob Franck = new Mob(R.drawable.loup,60, 60 , 10, 15, 20, 25, 5, 5, 1.2, 7, 1.1, 5, 1.0, 7, 1.3, 8, 1.3, 30, 20, 15, 35, 20, "Loup");
        Franck.addRecompense(Recomp1);
        Franck.addSpell(hspel0);
        Franck.addSpell(hspel1);
        Franck.addSpell(hspel2);
        Franck.addSpell(hspel3);

        Boss = Franck;
    }


}
