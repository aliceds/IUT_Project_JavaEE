package Modele;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import Modele.ClientModele;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnexionFormulaireModele {

    private static final String CHAMP_LOGIN = "login";
    private static final String CHAMP_PASS = "motdepasse";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public ClientModele connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String login = getValeurChamp(request, CHAMP_LOGIN);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        System.out.println(login);
        System.out.println(motDePasse);

        ClientModele client = new ClientModele();

        /* Validation du champ email. */
        try {
            validationLogin(login);
        } catch (Exception e) {
            setErreur(CHAMP_LOGIN, e.getMessage());
        }
        client.setLogin(login);

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(motDePasse, login);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }
        client.setPass(motDePasse);

        try {
            String type = checkTypeMembre(motDePasse, login);
            client.setTypeMembre(type);

        } catch (Exception e) {
            System.out.println(e);
        }

        ConnexionBDModele cnx = new ConnexionBDModele();
        System.out.println("Connexion BD OK");
        Connection connexion = cnx.getCnx();
        String email;
        String nom;
        String prenom;
        String cell;

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        ResultSet res = null;
        System.out.println(login);
        try {
            res = statement.executeQuery("SELECT email from utilisateur where pseudo='" + login + "'");
            if (res.next()) {
                email = res.getString(1);
                client.setEmail(email);
                System.out.println("ICI");
                System.out.println(email);
                System.out.println("LA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            res = statement.executeQuery("SELECT nom from utilisateur where pseudo='" + login + "'");
            if (res.next()) {
                nom = res.getString(1);
                client.setNom(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            res = statement.executeQuery("SELECT prenom from utilisateur where pseudo='" + login + "'");
            if (res.next()) {
                prenom = res.getString(1);
                client.setPrenom(prenom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            res = statement.executeQuery("SELECT cell from utilisateur where pseudo='" + login + "'");
            if (res.next()) {
                cell = res.getString(1);
                client.setCell(cell);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return client;
    }

    private String checkTypeMembre(String motDePasse, String login) throws Exception {
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        int count = 0;
        System.out.println("Connexion --verif type");

        LienBaseModele lien = new LienBaseModele();
        System.out.println("Lien");
        Statement statement = lien.getLien(connexion);

        ResultSet res = null;
        System.out.println("execute");
        res = statement.executeQuery("SELECT typeMembre FROM utilisateur WHERE pseudo='" + login + "' AND mdp='" + motDePasse + "' ;");
        if (res.next()) {

            String membretype = res.getString(1);
            System.out.println("get type");

            System.out.println("affichage");
            System.out.println(membretype);
            return membretype;
        }
        return "";

    }

    private void validationLogin(String login) throws Exception {
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        int count = 0;
        System.out.println("Connexion --verif login");

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        ResultSet res = null;

        try {
            res = statement.executeQuery("SELECT pseudo FROM utilisateur WHERE pseudo='" + login + "' ;");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (res.next()) {
                count = count + 1;

            }
            System.out.println(count);
            if (count == 0) {
                throw new Exception("Login non existant.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionFormulaireModele.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void validationMotDePasse(String motDePasse, String login) throws Exception {

        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        int count = 0;
        System.out.println("Connexion --verif mdp");

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
                throw new Exception("login ou mdp non existant.");
            }
        } catch (SQLException ex) {
        }

        if (motDePasse != null) {
            if (count == 0) {
                throw new Exception("Mot de passe ou login invalide.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }

        res.close();
        connexion.close();
        statement.close();
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

}
