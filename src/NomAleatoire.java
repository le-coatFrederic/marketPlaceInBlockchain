import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NomAleatoire {
    public static NomAleatoire nomsBasiques = new NomAleatoire(new ArrayList<String>(Arrays.asList("Jacques", "Fred", "Melvin", "Simon", "Francois", "Jean", "Yves", "Dupradeau", "Ilias", "Julie", "Jeremy", "Fatima", "Antonin")));
    public static NomAleatoire nomsObjets = new NomAleatoire(new ArrayList<String>(Arrays.asList("Chien", "Loup", "Singe", "Canard", "Lion", "Tigre", "Ours", "Panda", "Flamand rose", "Cygne", "Poulet", "Cochon", "Vache", "Otarie", "Requin", "Poisson", "Souris")));

    private ArrayList<String> noms;

    private NomAleatoire(ArrayList<String> noms) {
        this.noms = noms;
    }

    public String avoirNom() {
        Random rand = new Random();
        return noms.get(rand.nextInt(noms.size()));
    }
}
