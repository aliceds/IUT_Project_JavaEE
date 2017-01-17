package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class PaiementFormulaireModele {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_VILLE = "ville";
    private static final String CHAMP_POSTALE = "postale";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_BANK = "bank";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CELL = "cell";
    private static final String CHAMP_TOTAL = "total";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public CommandeModele stockerCommande(HttpServletRequest request) throws Exception {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String ville = getValeurChamp(request, CHAMP_VILLE);
        String postale = getValeurChamp(request, CHAMP_POSTALE);
        String nom = getValeurChamp(request, CHAMP_NOM);
        String bank = getValeurChamp(request, CHAMP_BANK);
        String cell = getValeurChamp(request, CHAMP_CELL);
        String adresse = getValeurChamp(request, CHAMP_ADRESSE);

        CommandeModele commande = new CommandeModele();

        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }

        try {
            validationVille(ville);
        } catch (Exception e) {
            setErreur(CHAMP_VILLE, e.getMessage());
        }

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }

        try {
            validationCell(cell);
        } catch (Exception e) {
            setErreur(CHAMP_CELL, e.getMessage());
        }

        try {
            validationAdresse(adresse);
        } catch (Exception e) {
            setErreur(CHAMP_ADRESSE, e.getMessage());
        }

        try {
            validationPostale(postale);
        } catch (Exception e) {
            setErreur(CHAMP_POSTALE, e.getMessage());
        }

        try {
            validationBank(bank);
        } catch (Exception e) {
            setErreur(CHAMP_BANK, e.getMessage());
        }

        if (erreurs.isEmpty()) {

            ConnexionBDModele cnx = new ConnexionBDModele();
            Connection connexion = cnx.getCnx();

            LienBaseModele lien = new LienBaseModele();
            Statement statement = lien.getLien(connexion);

            ResultSet res = null;
            try {
                //  System.out.println("INSERT INTO utilisateur VALUES (5, '"+login+"', '"+motDePasse+"', 'CLIENT'); ");
                // statement.execute("INSERT INTO utilisateur VALUES (NULL, '"+login+"', '"+motDePasse+"', 'CLIENT'); ");
            } catch (Exception e) {
                System.out.println(e);
            }
            resultat = "Succès du paiement, votre commande est en cours !";
        } else {
            resultat = "Échec lors de la fourniture d'informations.";
        }

        return commande;

    }

    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    private void validationNom(String nom) throws Exception {
        if (nom == null) {
            throw new Exception("Le nom d'utilisateur doit être non vide.");
        }
    }

    private void validationCell(String cell) throws Exception {
        if (cell == null) {
            throw new Exception("Le numéro de cellulaire doit être non vide.");
        }
    }

    private void validationAdresse(String adresse) throws Exception {
        if (adresse == null) {
            throw new Exception("L'adresse doit être fournie.");
        }
    }

    private void validationVille(String ville) throws Exception {
        if (ville == null) {
            throw new Exception("La ville doit être fournie.");
        }
    }

    private void validationPostale(String postale) throws Exception {
        if (postale == null) {
            throw new Exception("Le code postale doit être non vide.");
        }
    }

    private void validationBank(String bank) throws Exception {
        if (bank == null) {
            throw new Exception("Le code de la carte bancaire doit être non vide.");
        }
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

}
