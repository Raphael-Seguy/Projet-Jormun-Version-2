package com.example.jormun_propre;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activite_Village extends AppCompatActivity {

    private Handler mainHandler = new Handler();

    private TextView VilNom;
    private TextView VilLvl;
    private Button UpgVil;

    private Button btn_bat0;
    private Button btn_bat1;
    private Button btn_bat2;
    private Button btn_bat3;
    private Button btn_bat4;

    private Button btn_quit;
    private Button btn_suiv;
    private Button btn_prec;

    ///bat up

    private ImageView SpriteBat;
    private TextView Nom_bat;
    private ScrollView Scroll_prod_bat;
    private Button btn_up_bat;
    private TextView text_nec_bat;
    private Button btn_back;
    private TextView Text_prod_titre_up;

    //creer Bat
    private ScrollView ListCreat;
    private Button btn_retour_creat;


    //fin crea Bat
    private  TextView NomBat_end;
    private ImageView Sprite_bat_crea_end;
    private ScrollView Prod_bat_crea_end;
    private Button Back_crea_end;
    private Button Yes_crea_end;
    private TextView Titre_prod_crea_end;

    private Button Btn_yesCreate;

    Village vill=new Village( "ChezRolo",  5,  50);
    Utilisateur actuUtilisateur = new Utilisateur();

    private int ActuPage=0;

    String Manq ; //pour les ressources manquantes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_village);

        Simulation();

        LiaisonCode_affichBat();
        SetNormal();
        Generation_village(0);
        GenerationListeCrea();
        VerifLvl(vill.lvl);
        LaunchBase();
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
                btn_suiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_prec.setVisibility(View.VISIBLE);
                        AffichPageNext();
                        VerifLvl(vill.lvl);
                    }
                });
                btn_prec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_suiv.setVisibility(View.VISIBLE);
                        AffichBtn();
                        AffichPagePrec();
                        VerifLvl(vill.lvl);
                    }
                });
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetNormal();
                        VerifLvl(vill.lvl);
                    }
                });

                btn_retour_creat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetNormal();
                        VerifLvl(vill.lvl);

                    }
                });
                Back_crea_end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        VilNom.setVisibility(View.GONE);
                        VilLvl.setVisibility(View.GONE);
                        UpgVil.setVisibility(View.GONE);

                        btn_bat0.setVisibility(View.GONE);
                        btn_bat1.setVisibility(View.GONE);
                        btn_bat2.setVisibility(View.GONE);
                        btn_bat3.setVisibility(View.GONE);
                        btn_bat4.setVisibility(View.GONE);

                        btn_quit.setVisibility(View.VISIBLE);
                        btn_prec.setVisibility(View.GONE);
                        btn_suiv.setVisibility(View.GONE);

                        ListCreat.setVisibility(View.VISIBLE);
                        btn_retour_creat.setVisibility(View.VISIBLE);

                        NomBat_end.setVisibility(View.GONE);
                        Sprite_bat_crea_end.setVisibility(View.GONE);
                        Prod_bat_crea_end.setVisibility(View.GONE);
                        Back_crea_end.setVisibility(View.GONE);
                        Yes_crea_end.setVisibility(View.GONE);
                        Titre_prod_crea_end.setVisibility(View.GONE);
                    }
                });
                btn_quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuitThisPlace();
                    }
                });
            }
        }
    }
    public void QuitThisPlace() {
        Intent intent = new Intent(Activite_Village.this,
                Activite_Base.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void LiaisonCode_affichBat()
    {
        VilNom=findViewById(R.id.text_nom_vill);
        VilLvl=findViewById(R.id.text_lvl_vil);
        UpgVil = findViewById(R.id.btn_up_vil);

        btn_bat0=findViewById(R.id.btn_bat0_vil);
        btn_bat1=findViewById(R.id.btn_bat1_vil);
        btn_bat2=findViewById(R.id.btn_bat2_vil);
        btn_bat3=findViewById(R.id.btn_bat3_vil);
        btn_bat4=findViewById(R.id.btn_bat4_vil);

        btn_quit=findViewById(R.id.btn_quit_vil);
        btn_prec=findViewById(R.id.btn_prec_vil);
        btn_suiv=findViewById(R.id.btn_suiv_vil);

        SpriteBat = findViewById(R.id.Sprite_bat_prec);
        Nom_bat = findViewById(R.id.text_nom_bat_prec);
        Scroll_prod_bat = findViewById(R.id.Scroll_prod_bat_prec);
        btn_up_bat = findViewById(R.id.btn_up_bat_prec);
        text_nec_bat = findViewById(R.id.text_up_nec_bat_prec);
        btn_back = findViewById(R.id.btn_back);
        Text_prod_titre_up=findViewById(R.id.text_titre_prod_up);

        ListCreat=findViewById(R.id.Scorl_creat_liste);
        btn_retour_creat=findViewById(R.id.btn_retour_creat);

        NomBat_end = findViewById(R.id.Nom_bat_creat_end);
        Sprite_bat_crea_end = findViewById(R.id.Sprite_Bat_crea_end);
        Prod_bat_crea_end = findViewById(R.id.Scroll_crea_end_prod);
        Back_crea_end = findViewById(R.id.btn_back_crea_end);
        Yes_crea_end = findViewById(R.id.Btn_Yes_crea_end);
        Titre_prod_crea_end=findViewById(R.id.Pord_titre_crea_end);
    }

    public void Generation_village(final int rang)
    {
        if(rang==5)
        {
            btn_suiv.setVisibility(View.VISIBLE);
            btn_suiv.setVisibility(View.VISIBLE);
        }
        else if(rang==10)
        {
            btn_bat3.setVisibility(View.GONE);
            btn_bat4.setVisibility(View.GONE);
        }
        ArrayList<Batiment> ListBat = vill.listBat;
        Batiment batactu;
        VilNom.setText(vill.nom);
        VilLvl.setText("Lvl"+vill.lvl);
        if(ListBat.size()>=(rang+1))
        {
            batactu = ListBat.get(0);
            btn_bat0.setBackgroundResource(batactu.image);
            SetColorBat(btn_bat0,batactu);
            final Batiment finalBatactu4 = batactu;
            btn_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AffichBatprec(rang,btn_bat0, finalBatactu4);
                }
            });
            if(ListBat.size()>=2)
            {
                batactu = ListBat.get(1);
                btn_bat1.setBackgroundResource(batactu.image);
                SetColorBat(btn_bat1,batactu);
                final Batiment finalBatactu3 = batactu;
                btn_bat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichBatprec(rang+1,btn_bat1, finalBatactu3);
                    }
                });
                if(ListBat.size()>=3)
                {
                    batactu = ListBat.get(2);
                    btn_bat2.setBackgroundResource(batactu.image);
                    SetColorBat(btn_bat2,batactu);
                    final Batiment finalBatactu2 = batactu;
                    btn_bat2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AffichBatprec(rang +2,btn_bat2, finalBatactu2);
                        }
                    });
                    if (ListBat.size()>=4)
                    {
                        batactu = ListBat.get(3);
                        btn_bat3.setBackgroundResource(batactu.image);
                        SetColorBat(btn_bat3,batactu);
                        final Batiment finalBatactu1 = batactu;
                        btn_bat3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AffichBatprec(rang + 3,btn_bat3, finalBatactu1);
                            }
                        });
                        if(ListBat.size()>=5)
                        {
                            batactu = ListBat.get(4);
                            btn_bat4.setBackgroundResource(batactu.image);
                            SetColorBat(btn_bat4,batactu);
                            final Batiment finalBatactu = batactu;
                            btn_bat4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AffichBatprec(rang + 4,btn_bat4, finalBatactu);
                                }
                            });
                        }
                        else
                        {
                            EmptyButon(5);
                        }
                    }
                    else
                    {
                        EmptyButon(4);
                    }
                }
                else
                {
                    EmptyButon(3);
                }
            }
            else
            {
                EmptyButon(2);
            }
        }
        else
        {
            EmptyButon(1);
        }

        VerifLvl(vill.lvl);

    }

    public void AffichBatprec(final int Batplace, final Button btnCible, final Batiment bat)
    {
        btnCible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////Sprite = sprite bat
                VilNom.setVisibility(View.GONE);
                VilLvl.setVisibility(View.GONE);
                UpgVil.setVisibility(View.GONE);

                btn_bat0.setVisibility(View.GONE);
                btn_bat1.setVisibility(View.GONE);
                btn_bat2.setVisibility(View.GONE);
                btn_bat3.setVisibility(View.GONE);
                btn_bat4.setVisibility(View.GONE);
                btn_suiv.setVisibility(View.GONE);
                btn_prec.setVisibility(View.GONE);


                Nom_bat.setText(bat.nom+ " " +bat.lvl);
                Nom_bat.setVisibility(View.VISIBLE);
                /////sprite = sprite bat
                SpriteBat.setVisibility(View.VISIBLE);
                SpriteBat.setImageResource(bat.image);
                SetUpTrue(btnCible,bat,text_nec_bat,Batplace);
                btn_up_bat.setVisibility(View.VISIBLE);
                AffichProd(bat);
                Scroll_prod_bat.setVisibility(View.VISIBLE);
                text_nec_bat.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.VISIBLE);
                Text_prod_titre_up.setVisibility(View.VISIBLE);

            }
        });
    }
    public void EmptyButon(int place)
    {
        if(place==1)
        {
            btn_bat0.setBackgroundResource(R.drawable.add_logo);
            btn_bat0.setText("");
            btn_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=2)
        {
            btn_bat1.setBackgroundResource(R.drawable.add_logo);
            btn_bat1.setText("");
            btn_bat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=3)
        {
            btn_bat2.setBackgroundResource(R.drawable.add_logo);
            btn_bat2.setText("");
            btn_bat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if(place<=4)
        {
            btn_bat3.setBackgroundResource(R.drawable.add_logo);
            btn_bat3.setText("");
            btn_bat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=5)
        {
            btn_bat4.setBackgroundResource(R.drawable.add_logo);
            btn_bat4.setText("");
            btn_bat4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }

    }
    public void VerifLvl(int LVL) //////Verif du lvl village et donc du nombre d emplacement
    {
        int emplacement = LVL+2;
        for (int Compteur = ActuPage; Compteur > 0; Compteur--)
        {
            emplacement=emplacement-5;
        }
        if(emplacement<5)
        {
            btn_bat4.setVisibility(View.GONE);
            if(emplacement<4)
            {
                btn_bat3.setVisibility(View.GONE);
                if(emplacement<3)
                {
                    btn_bat2.setVisibility(View.GONE);
                    if (emplacement<2)
                    {
                        btn_bat1.setVisibility(View.GONE);
                    }
                }
            }
            btn_suiv.setVisibility(View.GONE);
        }

    }
    public void AffichPageNext()
    {
        ActuPage+=1;
        if(ActuPage==1)
        {
            Generation_village(5);
        }
        else
        {
            Generation_village(10);
            btn_suiv.setVisibility(View.GONE);
        }
    }
    public void AffichPagePrec()
    {
        ActuPage=ActuPage-1;
        if(ActuPage==1)
        {
            Generation_village(5);
        }
        else
        {
            Generation_village(0);
            btn_prec.setVisibility(View.GONE);
        }
    }
    public String SetNameBat(Batiment batactu) ////doit renvoyer un sprite et non un texte
    {
        String image;
        if(batactu.type==1)
        {
            if(batactu.lvl<4)
            {
                image="taverne";
            }
            else if (batactu.lvl>=7)
            {
                image="guilde des aventuriers";
            }
            else
            {
                image="auberge";
            }
        }
        else if(batactu.type==2)
        {
            if(batactu.lvl<4)
            {
                image="réparateur";
            }
            else if (batactu.lvl>=7)
            {
                image="guilde des artisans";
            }
            else
            {
                image="Forge";
            }
        }
        else if(batactu.type==3)
        {
            if(batactu.lvl<4)
            {
                image="cabane de bucheron";
            }
            else
            {
                image="Scierie";
            }
        }
        else if (batactu.type==4)
        {
            if(batactu.lvl<4)
            {
                image="baricade";
            }
            else if (batactu.lvl>=7)
            {
                image="Enceinte";
            }
            else
            {
                image="Muraille";
            }
        }
        else if (batactu.type==5)
        {
            if(batactu.lvl<4)
            {
                image="échoppe";
            }
            else if (batactu.lvl>=7)
            {
                image="Marché";
            }
            else
            {
                image="Magasin";
            }
        }
        else if(batactu.type==6)
        {
            if(batactu.lvl<4)
            {
                image="Mine";
            }
            else
            {
                image="Carrière";
            }
        }
        else
        {
            image="enchanteur";
        }

        return image;
    }
    public int SetImage(Batiment batactu) ////doit renvoyer un sprite et non un texte
    {
        int image;
        if(batactu.type==1)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.taverne;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.guilde_aventurier;
            }
            else
            {
                image=R.drawable.auberge;
            }
        }
        else if(batactu.type==2)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.reprateur;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.guilde_artisan;
            }
            else
            {
                image=R.drawable.forgeron;
            }
        }
        else if(batactu.type==3)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.cabane_bucheron;
            }
            else
            {
                image=R.drawable.scierie;
            }
        }
        else if (batactu.type==4)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.baricade;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.enceinte;
            }
            else
            {
                image=R.drawable.muraille;
            }
        }
        else if (batactu.type==5)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.echoppe;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.marche;
            }
            else
            {
                image=R.drawable.magasin;
            }
        }
        else if(batactu.type==6)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.mine;
            }
            else
            {
                image=R.drawable.cariere;
            }
        }
        else
        {
            image=R.drawable.enchanteur;
        }

        return image;
    }
    public void SetColorBat(Button btn_bat, Batiment bat_actu)    ////affiche si possible de LvlUp avec ressource actu
    {

        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;
        final ArrayList<Ressources> Bat_requis = Upressources(typebat,LvlBat);
        boolean up_ok = VerififRessSuff(bat_actu,LvlBat,typebat,Bat_requis);
        if(up_ok==true)
        {
            btn_bat.setBackgroundResource(bat_actu.image);
        }
    }
    public void SetUpTrue(final Button btn_bat, final Batiment bat_actu, TextView ressourceManq, final int Batplace)    ////affiche si possible de LvlUp avec ressource actu
    {
        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;
        final ArrayList<Ressources> Bat_requis = Upressources(typebat,LvlBat);
        boolean up_ok = VerififRessSuff(bat_actu,LvlBat,typebat,Bat_requis);

        if(up_ok==false)
        {
            btn_up_bat.setBackgroundResource(R.drawable.btn_yes_radius_no);
            btn_up_bat.setText("Ressources insufisantes");
            ressourceManq.setText(Manq);
        }
        else
        {
            btn_up_bat.setBackgroundResource(R.drawable.btn_yes_radius_yes);
            btn_up_bat.setText("Ameliorer");
            btn_up_bat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bat_actu.lvl = bat_actu.lvl +1;
                    EnleverRess(Bat_requis);
                    SetNormal();
                }
            });

        }
    }

    public boolean VerififRessSuff(Batiment bat_actu, int LvlBat, int typebat, ArrayList<Ressources> Bat_requis)
    {

        boolean up_ok = true;
        boolean Find=false;
        Manq ="Ressources manquantes:";
        Ressources ressourceActu;
        Ressources ressourceInInv;
        for(int Compteur_Bat=0; Compteur_Bat< Bat_requis.size();Compteur_Bat++)
        {
            ressourceActu= Bat_requis.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< actuUtilisateur.Inventaire_ressources.size();Compteur_inv++)
            {
                ressourceInInv=  actuUtilisateur.Inventaire_ressources.get(Compteur_inv);
                if(ressourceActu.nom == ressourceInInv.nom)
                {
                    if(ressourceActu.quantite>ressourceInInv.quantite)
                    {
                        up_ok=false;
                        Manq = Manq + " "+ (ressourceActu.quantite-ressourceInInv.quantite) + " " + ressourceActu.nom;
                    }
                    Find=true;
                }
            }
            if(Find==false)
            {
                Manq = Manq + " " + ressourceActu.quantite + " " + ressourceActu.nom;
                up_ok=false;
                Find=true;
            }
        }
        return up_ok;
    }

    public void AffichProd(Batiment batActu)
    {
        LinearLayout Container = new LinearLayout(this);
        Container.setOrientation(LinearLayout.VERTICAL);
        ArrayList<Ressources> prodSem = batActu.ProdSem;
        Ressources Ress;

        for (int Compteur=0; Compteur<prodSem.size();Compteur++)
        {
            TextView Prod = new TextView(this);
            Ress= prodSem.get(Compteur);
            Prod.setText(""+Ress.quantite+" "+Ress.nom+" par semaine");
            Prod.setTextSize(15);
            Prod.setTextColor(Color.rgb(219,223,223));
            Container.addView(Prod);
        }
        Scroll_prod_bat.removeAllViews();
        Scroll_prod_bat.addView(Container);
    }


    public void CreateBat()
    {
        VilNom.setVisibility(View.GONE);
        VilLvl.setVisibility(View.GONE);
        UpgVil.setVisibility(View.GONE);

        btn_bat0.setVisibility(View.GONE);
        btn_bat1.setVisibility(View.GONE);
        btn_bat2.setVisibility(View.GONE);
        btn_bat3.setVisibility(View.GONE);
        btn_bat4.setVisibility(View.GONE);

        btn_quit.setVisibility(View.VISIBLE);
        btn_prec.setVisibility(View.GONE);
        btn_suiv.setVisibility(View.GONE);

        ListCreat.setVisibility(View.VISIBLE);
        btn_retour_creat.setVisibility(View.VISIBLE);

    }

    public void GenerationListeCrea()
    {
        LinearLayout ContainerTot = new LinearLayout(this);
        ContainerTot.setOrientation(LinearLayout.VERTICAL);

        LinearLayout ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        ////taverne
        TextView Tavernes =new TextView(this);
        Tavernes.setText("repos");
        SetTextCreer(Tavernes);
        ContainerTot.addView(Tavernes);

        Button Tavern = new Button(this); ///Sprite
        Tavern.setBackgroundResource(R.drawable.taverne);
        SetBatCreer(Tavern);
        Tavern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Tavern",1,1);
            }
        });
        ContainerListe.addView(Tavern);

        Button Auberge = new Button(this);
        Auberge.setBackgroundResource(R.drawable.auberge);
        SetBatCreer(Auberge);
        Auberge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Auberge",1,4);
            }
        });
        ContainerListe.addView(Auberge);

        Button Guilde_des_aventuriers = new Button(this);
        Guilde_des_aventuriers.setBackgroundResource(R.drawable.guilde_aventurier);
        SetBatCreer(Guilde_des_aventuriers);
        Guilde_des_aventuriers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Guilde_des_aventuriers",1,8);
            }
        });
        ContainerListe.addView(Guilde_des_aventuriers);

        ContainerTot.addView(ContainerListe);

        ///Forge
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Forge =new TextView(this);
        Forge.setText("Forges");
        SetTextCreer(Forge);
        ContainerTot.addView(Forge);

        Button reparateur = new Button(this);
        reparateur.setBackgroundResource(R.drawable.reprateur);
        SetBatCreer(reparateur);
        reparateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("reparateur",2,1);
            }
        });
        ContainerListe.addView(reparateur);

        Button forge = new Button(this);
        forge.setBackgroundResource(R.drawable.forgeron);
        SetBatCreer(forge);
        forge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("forge",2,4);
            }
        });
        ContainerListe.addView(forge);

        Button guilde_des_artisans = new Button(this);
        guilde_des_artisans.setBackgroundResource(R.drawable.guilde_artisan);
        SetBatCreer(guilde_des_artisans);
        guilde_des_artisans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("guilde_des_artisans",2,8);
            }
        });
        ContainerListe.addView(guilde_des_artisans);

        ContainerTot.addView(ContainerListe);
        ///Bucheron
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Bucherons =new TextView(this);
        Bucherons.setText("Bucherons");
        SetTextCreer(Bucherons);
        ContainerTot.addView(Bucherons);

        Button Bucheron = new Button(this);
        Bucheron.setBackgroundResource(R.drawable.cabane_bucheron);
        SetBatCreer(Bucheron);
        Bucheron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Bucheron",3,1);
            }
        });
        ContainerListe.addView(Bucheron);

        Button Scierie = new Button(this);
        Scierie.setBackgroundResource(R.drawable.scierie);
        SetBatCreer(Scierie);
        Scierie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Scierie",3,4);
            }
        });
        ContainerListe.addView(Scierie);

        ContainerTot.addView(ContainerListe);
        ///Defenses
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Defenses =new TextView(this);
        Defenses.setText("Defenses");
        SetTextCreer(Defenses);
        ContainerTot.addView(Defenses);

        Button Barricade = new Button(this);
        Barricade.setBackgroundResource(R.drawable.baricade);
        SetBatCreer(Barricade);
        Barricade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Barricade",4,1);
            }
        });
        ContainerListe.addView(Barricade);

        Button Muraille = new Button(this);
        Muraille.setBackgroundResource(R.drawable.muraille);
        SetBatCreer(Muraille);
        Muraille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Muraille",4,4);
            }
        });
        ContainerListe.addView(Muraille);

        Button Enceinte = new Button(this);
        Enceinte.setBackgroundResource(R.drawable.enceinte);
        SetBatCreer(Enceinte);
        ContainerListe.addView(Enceinte);
        Enceinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Enceinte",4,8);
            }
        });
        ContainerTot.addView(ContainerListe);
        ///Vendeurs
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Vendeurs =new TextView(this);
        Vendeurs.setText("Vendeurs");
        SetTextCreer(Vendeurs);
        ContainerTot.addView(Vendeurs);

        Button Echope = new Button(this);
        Echope.setBackgroundResource(R.drawable.echoppe);
        SetBatCreer(Echope);
        Echope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Echope",5,1);
            }
        });
        ContainerListe.addView(Echope);

        Button Magasin = new Button(this);
        Magasin.setBackgroundResource(R.drawable.magasin);
        SetBatCreer(Magasin);
        Magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Magasin",5,4);
            }
        });
        ContainerListe.addView(Magasin);

        Button Marche = new Button(this);
        Marche.setBackgroundResource(R.drawable.marche);
        SetBatCreer(Marche);
        Marche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Marche",5,8);
            }
        });
        ContainerListe.addView(Marche);

        ContainerTot.addView(ContainerListe);
        ///Minage
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Minage =new TextView(this);
        Minage.setText("Minage");
        SetTextCreer(Minage);
        ContainerTot.addView(Minage);

        Button Mine = new Button(this);
        Mine.setBackgroundResource(R.drawable.mine);
        SetBatCreer(Mine);
        Mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Mine",6,1);
            }
        });
        ContainerListe.addView(Mine);

        Button Carriere = new Button(this);
        Carriere.setBackgroundResource(R.drawable.cariere);
        SetBatCreer(Carriere);
        Carriere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Carriere",6,4);
            }
        });
        ContainerListe.addView(Carriere);

        ContainerTot.addView(ContainerListe);
        ///Enchantement
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Enchantement =new TextView(this);
        Enchantement.setText("Enchantement");
        SetTextCreer(Enchantement);
        ContainerTot.addView(Enchantement);

        Button Enchanteur = new Button(this);
        Enchanteur.setBackgroundResource(R.drawable.enchanteur);
        SetBatCreer(Enchanteur);
        Enchanteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Enchanteur",7,1);
            }
        });
        ContainerListe.addView(Enchanteur);

        ContainerTot.addView(ContainerListe);
        ListCreat.removeAllViews();
        ListCreat.addView(ContainerTot);

    }
    public void SetBatCreer(TextView bat)
    {
        bat.setHeight(350);
        bat.setWidth(400);
        bat.setTextColor(Color.rgb(86,87,87));
    }
    public void SetTextCreer(TextView Titre)
    {
        Titre.setHeight(80);
        Titre.setTextSize(18);
        Titre.setGravity(Gravity.CENTER);
        Titre.setTextColor(Color.rgb(219,223,223));
    }
    public void CreateBatYEs(String Bat_nom,int type,int Lvl) ///+, Sprite
    {
        ///afficher section
        ListCreat.setVisibility(View.GONE);
        btn_retour_creat.setVisibility(View.GONE);
        Batiment newBat = new Batiment(type,Lvl);
        vill.addBatimentCible(newBat);

        NomBat_end.setText(Bat_nom);
        NomBat_end.setVisibility(View.VISIBLE);
        ////set Sprite
        Sprite_bat_crea_end.setVisibility(View.VISIBLE);
        Gen_prod_end(vill);
        Prod_bat_crea_end.setVisibility(View.VISIBLE);

        Back_crea_end.setVisibility(View.VISIBLE);
        Verif_end_Ress(newBat,actuUtilisateur.Inventaire_ressources);
        Yes_crea_end.setVisibility(View.VISIBLE);
        Titre_prod_crea_end.setVisibility(View.VISIBLE);

    }

    public void Gen_prod_end(Village village)
    {
        LinearLayout Container = new LinearLayout(this);
        Container.setOrientation(LinearLayout.VERTICAL);

        for (int Compteur=0; Compteur<village.listBat.size();Compteur++)
        {
            for (int Compteur2=0; Compteur2<village.listBat.get(Compteur).ProdSem.size();Compteur2++)
            {
                TextView Prod = new TextView(this);
                Prod.setText(""+village.listBat.get(Compteur).ProdSem.get(Compteur2).quantite+" "+village.listBat.get(Compteur).ProdSem.get(Compteur2).nom+" par semaine");
                Prod.setTextColor(Color.rgb(219,223,223));
                Container.addView(Prod);
            }
        }
        Prod_bat_crea_end.removeAllViews();
        Prod_bat_crea_end.addView(Container);
    }
    public void Verif_end_Ress(final Batiment bat_actu, ArrayList invress)    ////affiche si possible de LvlUp avec ressource actu
    {
        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;
        final ArrayList<Ressources> Bat_requis = Upressources(typebat,LvlBat);
        boolean up_ok = VerififRessSuff(bat_actu,LvlBat,typebat,Bat_requis);

        if(up_ok==false)
        {
            Yes_crea_end.setBackgroundResource(R.drawable.btn_yes_radius_no);
            Yes_crea_end.setText(Manq);
            Yes_crea_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        else
        {
            Yes_crea_end.setBackgroundResource(R.drawable.btn_yes_radius_yes);
            Yes_crea_end.setText("Creer");
            Yes_crea_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddenewBat(bat_actu);
                    EnleverRess(Bat_requis);
                }
            });

        }
    }
    public void AddenewBat(Batiment newBat)
    {
        vill.listBat.add(newBat);
        LiaisonCode_affichBat();
        SetNormal();
        Generation_village(0);
        GenerationListeCrea();
        VerifLvl(vill.lvl);
        LaunchBase();
    }


    public ArrayList Upressources(int Type, int Lvl)
    {
        ArrayList List_ressources_req = new ArrayList();
        if(Type==1)
        {
            List_ressources_req=UpTavern(Lvl);
        }
        else if(Type==2)
        {
            List_ressources_req=UpForge(Lvl);
        }
        else if(Type==3)
        {
            List_ressources_req=UpBucheron(Lvl);
        }
        else if(Type==4)
        {
            List_ressources_req=UpDefense(Lvl);
        }
        else if(Type==5)
        {
            List_ressources_req=UpMagasin(Lvl);
        }
        else if(Type==6)
        {
            List_ressources_req=UpMine(Lvl);
        }
        else if(Type==7)
        {
            List_ressources_req=UpEnchanteur(Lvl);
        }

        return List_ressources_req;
    }
    ///verif des ressources nécessaire à evolution
    public ArrayList UpTavern(int Lvl)
    {
        ArrayList listeRess = new ArrayList();
        listeRess.add(new Ressources((int)(25+Math.pow(5,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(5+Math.pow(3,(Lvl/2))),"pierre",""));

        return listeRess;
    }
    public ArrayList UpForge(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(12+Math.pow(3,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(3+Math.pow(2,(Lvl/2))), "pierre",""));
        listeRess.add(new Ressources((int)(1+Math.pow(1.2,(Lvl/2))),"fer",""));

        return listeRess;
    }
    public ArrayList UpBucheron(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(12+Math.pow(2,(Lvl/2))), "Bois",""));

        return listeRess;
    }
    public ArrayList UpDefense(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(30+Math.pow(4,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(3+Math.pow(3,(Lvl/2))),"pierre",""));

        return listeRess;
    }
    public ArrayList UpMagasin(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(8+Math.pow(2.5,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(1+Math.pow(1.5,(Lvl/2))),"tissus",""));
        listeRess.add(new Ressources((int)(1+Math.pow(2,(Lvl/2))),"pierre",""));

        return listeRess;
    }
    public ArrayList UpMine(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(18+Math.pow(3.5,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(14+Math.pow(4,(Lvl/2))),"pierre",""));

        return listeRess;
    }
    public ArrayList UpEnchanteur(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(new Ressources((int)(15+Math.pow(3.5,(Lvl/2))),"Bois",""));
        listeRess.add(new Ressources((int)(25+Math.pow(2,(Lvl/2))),"pierre",""));
        listeRess.add(new Ressources((int)(1+Math.pow(4,(Lvl/2))),"Crystal blanc",""));

        return listeRess;
    }



    public void SetNormal()
    {
        VilNom.setVisibility(View.VISIBLE);
        VilLvl.setVisibility(View.VISIBLE);
        UpgVil.setVisibility(View.VISIBLE);

        btn_bat0.setVisibility(View.VISIBLE);
        btn_bat1.setVisibility(View.VISIBLE);
        btn_bat2.setVisibility(View.VISIBLE);
        btn_bat3.setVisibility(View.VISIBLE);
        btn_bat4.setVisibility(View.VISIBLE);

        btn_quit.setVisibility(View.VISIBLE);
        if(ActuPage==0)
        {
            btn_prec.setVisibility(View.GONE);
        }
        else
        {
            btn_prec.setVisibility(View.VISIBLE);
        }
        if (ActuPage==3)
        {
            btn_suiv.setVisibility(View.GONE);
        }
        else
        {
            btn_suiv.setVisibility(View.VISIBLE);
        }

        SpriteBat.setVisibility(View.GONE);
        Nom_bat.setVisibility(View.GONE);
        Scroll_prod_bat.setVisibility(View.GONE);
        btn_up_bat.setVisibility(View.GONE);
        text_nec_bat.setVisibility(View.GONE);
        btn_back.setVisibility(View.GONE);
        Text_prod_titre_up.setVisibility(View.GONE);

        ListCreat.setVisibility(View.GONE);
        btn_retour_creat.setVisibility(View.GONE);

        NomBat_end.setVisibility(View.GONE);
        Sprite_bat_crea_end.setVisibility(View.GONE);
        Prod_bat_crea_end.setVisibility(View.GONE);
        Back_crea_end.setVisibility(View.GONE);
        Yes_crea_end.setVisibility(View.GONE);
        Titre_prod_crea_end.setVisibility(View.GONE);
    }

    public void EnleverRess(ArrayList<Ressources> RessDemandee)
    {
        Ressources ressourceActu;
        for(int Compteur_Bat=0; Compteur_Bat< RessDemandee.size();Compteur_Bat++)
        {
            ressourceActu= RessDemandee.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< actuUtilisateur.Inventaire_ressources.size();Compteur_inv++)
            {
                if(ressourceActu.nom == actuUtilisateur.Inventaire_ressources.get(Compteur_inv).nom)
                {
                    actuUtilisateur.Inventaire_ressources.get(Compteur_inv).quantite = actuUtilisateur.Inventaire_ressources.get(Compteur_inv).quantite - ressourceActu.quantite;
                }
            }
        }
    }

    public void AffichBtn()
    {
        btn_bat0.setVisibility(View.VISIBLE);
        btn_bat1.setVisibility(View.VISIBLE);
        btn_bat2.setVisibility(View.VISIBLE);
        btn_bat3.setVisibility(View.VISIBLE);
        btn_bat4.setVisibility(View.VISIBLE);
    }
    //////////simulation Db



    public void Simulation()
    {
        Ressources Ordepart = new Ressources(4,"or","ca brille");
        Ressources boisdepart= new Ressources(115,"bois"," ca pousse sur les arbres");
        actuUtilisateur.addinInventaire_utilitaire(Ordepart);
        actuUtilisateur.addinInventaire_utilitaire(boisdepart);
    }
}
