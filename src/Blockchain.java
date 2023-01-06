import java.util.ArrayList;

public class Blockchain extends Observable {
    public static Blockchain blockchainPrimaire = new Blockchain();
    public static Blockchain blockchainVerifiee = new Blockchain();
    private ArrayList<Bloc> blocs;
    private ArrayList<Enchere> encheres;
    private Object blocage;

    public Blockchain() {
        synchronized(blocage) {
            blocs = new ArrayList<>();
            encheres = new ArrayList<>();
            blocs.add(new Bloc(0, null));
        }
    }

    public void ajouterBloc(Bloc bloc, Offre offre) {
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
            encheres.add(enchere);
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
}
