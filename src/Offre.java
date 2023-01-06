import java.util.Date;

public class Offre {
    private double prix;
    private Usager acheteur;
    private Date dateEmission;

    public Offre(double prix, Usager acheteur) {
        this.prix = prix;
        this.acheteur = acheteur;
        dateEmission = new Date();
    }

    public double getPrix() {
        return prix;
    }

    public Usager getAcheteur() {
        return acheteur;
    }

    public Date getDate() {
        return dateEmission;
    }

    public String toString() {
        return "prix : [" + prix + "] acheteur : [" + acheteur + "] date : [" + dateEmission + "]";
    }
}
