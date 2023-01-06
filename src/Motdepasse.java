import java.io.Serializable;
import java.util.Random;

public class Motdepasse implements Serializable {
    protected String motdepasse;
    protected String cle;

    public Motdepasse(String mdp) {
        cle = genererCle();
        motdepasse = transformation(mdp);
    }

    private String transformation(String mdp) {
        String newmdp = "";
        for (int i = 0; i < mdp.length(); i++) {
            newmdp += lettreAvecCle(mdp.charAt(i), i);
        }

        return newmdp;
    }

    private String lettreAvecCle(char lettre, int indice) {
        Random aleatoire = new Random();
        int taille = aleatoire.nextInt(6);
    
        String rendu = "";        
        for (int i = 0; i < taille; i++)
            rendu += (char)((lettre + cle.charAt((indice + i) % cle.length())) % 126);
    
        return rendu;
    }

    private String genererCle() {
        Random aleatoire = new Random();
        String cle = "";

        for (int i = 0; i < aleatoire.nextInt(32) + 8; i++)
            cle += (char)aleatoire.nextInt(126) + 1;

        return cle;
    }

    public Boolean verifierMotDePasse(String mdp) {
        if (transformation(mdp).equals(this.motdepasse))
            return true;

        return false;
    }

    public String toString() {
        return "motdepasse : " + motdepasse + " cle : " + cle;
    }
}
