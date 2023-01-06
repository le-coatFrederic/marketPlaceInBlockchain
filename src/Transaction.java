import java.util.Random;

public class Transaction {
    private int id;
    private Enchere enchere;
    private Offre offre;

    public Transaction(Enchere enchere, Offre offre) {
        this.enchere = enchere;
        this.offre = offre;
        genererId();
    }

    public void genererId() {
        Random aleatoire = new Random();
        id = aleatoire.nextInt(10000);
        try {
            id = aleatoire.nextInt((int)enchere.getPrixDepart()) * (int)offre.getPrix();
        } catch (OutOfMemoryError ome) {
            ome.getStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public Offre getOffre() {
        return offre;
    }

    public boolean isValid(Transaction precedent) {
        double prix = enchere.getPrixDepart();
        if (precedent != null)
            prix = precedent.getOffre().getPrix();

        if (prix < offre.getPrix())
            return true;
        
        return false;
    }

    public String toString() {
        return "enchere : [" + enchere.toString() + "] offre : [" + offre.toString() + "]";
    }
}
