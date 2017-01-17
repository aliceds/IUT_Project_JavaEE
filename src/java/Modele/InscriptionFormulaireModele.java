package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class InscriptionFormulaireModele {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "pass";
    private static final String CHAMP_CONF = "repass";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_LOGIN = "login";
    private static final String CHAMP_CELL = "cell";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public ClientModele inscrireClient(HttpServletRequest request) throws Exception {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        System.out.println(email);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        System.out.println(motDePasse);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        System.out.println(confirmation);
        String nom = getValeurChamp(request, CHAMP_NOM);
        System.out.println(nom);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        System.out.println(prenom);
        String cell = getValeurChamp(request, CHAMP_CELL);
        System.out.println(cell);
        String login = getValeurChamp(request, CHAMP_LOGIN);
        System.out.println(login);

        ClientModele client = new ClientModele();

        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        client.setEmail(email);

        try {
            validationLogin(login);
        } catch (Exception e) {
            setErreur(CHAMP_LOGIN, e.getMessage());
        }
        client.setLogin(login);

        try {
            validationPrenom(prenom);
        } catch (Exception e) {
            setErreur(CHAMP_PRENOM, e.getMessage());
        }
        client.setPrenom(prenom);

        try {
            validationCell(cell);
        } catch (Exception e) {
            setErreur(CHAMP_CELL, e.getMessage());
        }
        client.setCell(cell);

        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        client.setPass(motDePasse);

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        client.setNom(nom);

        if (erreurs.isEmpty()) {

            Boolean userExist = verifUtilisateur(login, motDePasse);
            if (userExist == false) {
                ConnexionBDModele cnx = new ConnexionBDModele();
                Connection connexion = cnx.getCnx();

                LienBaseModele lien = new LienBaseModele();
                Statement statement = lien.getLien(connexion);

                ResultSet res = null;
                try {

                    statement.execute("INSERT INTO utilisateur VALUES (NULL, '" + login + "', '" + motDePasse + "', 'CLIENT', '" + email + "', '" + nom + "', '" + prenom + "', '" + cell + "'); ");
                } catch (Exception e) {
                    System.out.println(e);
                }
                resultat = "Inscription réussie !";
            }

        } else {
            resultat = "Inscription raté !";
        }

        return client;
    }

    private Boolean verifUtilisateur(String login, String motDePasse) throws Exception {
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        int count = 0;

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        ResultSet res = null;

        try {
            res = statement.executeQuery("SELECT pseudo FROM utilisateur WHERE pseudo='" + login + "' AND mdp='" + motDePasse + "' ;");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (res.next()) {
                count = count + 1;
            }
            System.out.println(count);
            if (count == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
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

    private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    private void validationNom(String nom) throws Exception {
        if (nom == null) {
            throw new Exception("Le nom d'utilisateur doit être non vide.");
        }
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    private void validationLogin(String login) throws Exception {
        if (login == null || login.length() < 3) {
            throw new Exception("Le login doit être d'au moins 3 caractères.");
        }
    }

    private void validationPrenom(String prenom) throws Exception {
        if (prenom == null) {
            throw new Exception("Le prénom doit être non vide.");
        }
    }

    private void validationCell(String cell) throws Exception {
        if (cell == null) {
            throw new Exception("Le numéro de téléphone doit être non vide.");
        }
    }

}
