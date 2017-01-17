package Modele;

public class ProduitModele {

    int idProduit;
    String photoProduit;
    String nomProduit;
    int prixProduit;

    public int getIdProduit() {
        return idProduit;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public int getPrixProduit() {
        return prixProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setPrixProduit(int ancienPrix) {
        this.prixProduit = ancienPrix;
    }


}
