package com.example.jormun_map.model.data_classes;

public class Ressources extends Recompense // les ressources sont des recompenses étant dans la catégorie 20
{
    public Ressources(int Vquantite, String Vnom, String Vdescription)
    {
        super(Vnom,Vdescription,Vquantite,20);
    }
}
