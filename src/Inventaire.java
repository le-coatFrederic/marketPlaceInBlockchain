import java.io.Serializable;
import java.util.HashMap;

public class Inventaire implements Serializable {
    protected HashMap<String, Objet> objets;

    public Inventaire() {
        objets = new HashMap<>();
    }

    public void ajouterObjet(Objet objet) {
        if (objet == null)
            throw new IllegalArgumentException("L'argument objet ne peut pas etre null");

        objets.put(objet.getNom(), objet);
    }

    public void retirerObjet(String nom) {
        if (nom == null) 
            throw new IllegalArgumentException("L'argument nom ne peut pas etre null");

        objets.remove(nom); // il n'y a pas besoin de faire de testes pour HashMap.remove() qui verifie déjà si objet est présent
    }

    public Objet getObjet(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("L'argument nom ne peut pas etre null");
            
        return objets.get(nom);
    }

    public String toString() {
        return objets.toString();
    }
}