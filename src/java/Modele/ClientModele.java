package Modele;

import java.io.Serializable;

public class ClientModele implements Serializable{
    
    private String nom;
    private String prenom;
    private String login;
    private String email;
    private String pass;
    private String cell;
    private String typeMembre;
    private int idPseudo;

    public int getIdPseudo() {
        return idPseudo;
    }

    public String getTypeMembre() {
        return typeMembre;
    }

    public void setTypeMembre(String typeMembre) {
        this.typeMembre = typeMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setIdPseudo(int idPseudo) {
        this.idPseudo = idPseudo;
    }
   

    
    
}
