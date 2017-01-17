package Modele;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBDModele implements java.io.Serializable {

    private String login;
    private String password;
    private String hostname;
    private String port;
    private String nomDeLaBase;
    private Connection cnx;

    public void setLogin(String valeur) {
        login = valeur;
    }

    public void setPassword(String valeur) {
        password = valeur;
    }

    public void setHostname(String valeur) {
        hostname = valeur;
    }

    public void setPort(String valeur) {
        port = valeur;
    }

    public void setNomDeLaBase(String valeur) {
        nomDeLaBase = valeur;
    }

    public Connection getCnx() {
        if (etablirConnexion()) {
            return cnx;
        } else {
            return null;
        }
    }

    private String construireUrlJdbc() {
        String urlJdbc;
        urlJdbc = "jdbc:mysql://" + hostname + ":" + port
                + "/" + nomDeLaBase;
        urlJdbc = urlJdbc
                + "?user=" + login + "&password=" + password;
        return urlJdbc;
    }

    private boolean etablirConnexion() {
        boolean statusConnexion = false;
        String urlJdbc;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/java_ee","root","");
            System.out.println("----------- Connexion -------------------");
            statusConnexion = true;
        } catch (Exception e) {
            statusConnexion = false;
            System.out.println(e);
        }
        return statusConnexion;
    }
}
