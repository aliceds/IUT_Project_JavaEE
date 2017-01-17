package Modele;

import java.util.ArrayList;

public class Modele {

    ArrayList<ProduitModele> listeProduit = new ArrayList<ProduitModele>();

    public ArrayList<ProduitModele> getListe() {
        return listeProduit;
    }

    public void setListe(ArrayList<ProduitModele> a) {
        this.listeProduit = listeProduit;
    }

    public void ajout(ProduitModele prod) {

        this.listeProduit.add(prod);
    }

}
