package main.java.at.jku.restservice;

import src.restapi.RestConfig;
import src.restapi2.RestConfig2;

import java.util.ArrayList;
import java.util.List;

public class Angebot
{
    private RestConfig lieferant1 ;
    private RestConfig2 lieferant2 ;

    private String handlebarType;
    private String handlebarMaterial;
    private String handlebarGearshift;
    private String handleMaterial;
    private String price;
    private String date;

    public Angebot(String handlebarType, String handlebarMaterial, String handlebarGearshift, String handleMaterial)
    {
        this.handlebarType = handlebarType;
        this.handlebarMaterial= handlebarMaterial;
        this.handlebarGearshift= handlebarGearshift;
        this.handleMaterial = handleMaterial;
        this.price = bestOffer().get(0);
        this.date = bestOffer().get(1);
    }

    public List<String> bestOffer()
    {

        List<RestConfig> liste1 = lieferant1.calculateDatePrice();
        List<RestConfig2> liste2 = lieferant2.calculateDatePrice();

        List<String> bestOffer = new ArrayList<>();

        // Bestes Angebot wird nur durch den Preis berechnet, das Datum spielt keine Rolle
        if(liste1.get(0).getPrice() <= liste2.get(0).getPrice())
        {
            bestOffer.add(""+liste1.get(0).getPrice());
            bestOffer.add(""+liste1.get(0).getDate());

            return bestOffer;
        }

        bestOffer.add(""+liste2.get(0).getPrice());
        bestOffer.add(""+liste2.get(0).getDate());

        return bestOffer;
    }

}
