import java.util.ArrayList;

public class Blockchain extends Observable {
    public static Blockchain blockchainPrimaire = new Blockchain();
    public static Blockchain blockchainVerifiee = new Blockchain();
    private ArrayList<Bloc> blocs;
    private ArrayList<Enchere> encheres;
    private Motdepasse blocage = new Motdepasse("123");
    private int nbrEnchere;

    public Blockchain() {
        synchronized(blocage) {
            blocs = new ArrayList<>();
            encheres = new ArrayList<>();
            blocs.add(new Bloc(0, null));
            nbrEnchere = 0;
        }
    }

    public void ajouterBloc(Offre offre) {
        synchronized(blocage) {
            Bloc blocPrecedent = blocs.get(blocs.size() - 1);
                if (encheres.size() != 0)
                    blocs.add(new Bloc(blocPrecedent.getHash(), new Transaction(getEnchere(), offre)));
        }
    }

    public ArrayList<Bloc> getBlocs() {
        synchronized(blocage) {
            return blocs;
        }
    }

    public void ajouterEnchere(Enchere enchere) {
        synchronized(blocage) {
            if (enchere == null)
                throw new IllegalArgumentException();
            if (nbrEnchere > 0) {
                encheres.add(enchere);
                System.out.println("Enchere " + enchere + " a ete ajoute");
                nbrEnchere -= 1;
            }
        }
    }

    public synchronized Enchere getEnchere() {
        synchronized(blocage) {
            return encheres.get(0);
        }
    }

    public ArrayList<Enchere> getAllEncheres() {
        synchronized(blocage) {
            return encheres;
        }
    }

    public void setNbrEnchere(int nbr) {
        if (nbr > 0)
            nbrEnchere = nbr;
    }

    public int getNbrEnchere() {
        return nbrEnchere;
    }
}
