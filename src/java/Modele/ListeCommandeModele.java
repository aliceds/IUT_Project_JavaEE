package Modele;

import java.util.ArrayList;

public class ListeCommandeModele {

    ArrayList<CommandeModele> listeCommande = new ArrayList<CommandeModele>();

    public ArrayList<CommandeModele> getListe() {
        return listeCommande;
    }

    public void setListe(ArrayList<CommandeModele> a) {
        this.listeCommande = listeCommande;
    }

    public void ajout(CommandeModele commande) {

        this.listeCommande.add(commande);
    }

}
