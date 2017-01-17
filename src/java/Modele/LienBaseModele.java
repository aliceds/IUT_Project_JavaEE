package Modele;

import java.sql.*;

public class LienBaseModele implements java.io.Serializable {

    private Statement lien = null;

    public Statement getLien(Connection cnx) {
        if (construireStatement(cnx)) {
            return lien;
        } else {
            return null;
        }
    }

    private boolean construireStatement(Connection cnx) {
        boolean statusStatement = false;
        try {
            lien = cnx.createStatement();
            statusStatement = true;
        } catch (Exception e) {
            statusStatement = false;
            System.out.println(e);
        }
        return statusStatement;
    }
}
