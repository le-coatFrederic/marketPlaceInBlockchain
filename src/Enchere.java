import java.util.Calendar;
import java.util.Date;

public class Enchere {
    private Objet objet;
    private double prixDepart;
    private Date tempsDebut;
    private Date tempsFin;
    private int duree;
    
    public Enchere(Objet objet, double prixDepart, int duree) {
        this.objet = objet;
        this.prixDepart = prixDepart;
        this.duree = duree;
    }

    public Objet getObjet() {
        return objet;
    }

    public double getPrixDepart() {
        return prixDepart;
    }

    public Date getTempsDebut() {
        return tempsDebut;
    }

    public Date getTempsFin() {
        return tempsFin;
    }

    public void setTempsDebut() {
        this.tempsDebut = new Date();
        Calendar calendrier = Calendar.getInstance();
        calendrier.setTime(tempsDebut);
        calendrier.add(calendrier.SECOND, duree);
        this.tempsFin = calendrier.getTime();
    }

    public String toString() {
        return "Est mis en vente : [" + objet + "] au prix initial de : " + prixDepart + "€. L'enchere commence a " + tempsDebut + " et se termine a " + tempsFin + " pour une duree totale de " + duree + "secondes.";
    }
}
