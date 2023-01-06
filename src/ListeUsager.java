import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ListeUsager implements Serializable {
    private ArrayList<Usager> usagers;

    public ListeUsager() {
        usagers = new ArrayList<>();
    }

    public void ajouterUsager(Usager usager) {
        usagers.add(usager);
    }

    public ArrayList<Usager> getUsagers() {
        return usagers;
    }

    public Usager getUsager(String nom) {
        for (Usager usager : usagers) {
            if (usager.getNom().equals(nom)) {
                return usager;
            }
        }

        return null;
    }

    public void enregistrerUsagers(String nomFichier) throws IOException {
        FileOutputStream file = new FileOutputStream(nomFichier);
        try (ObjectOutputStream out = new ObjectOutputStream(file)) {
            for (Usager usager : usagers) {
                out.writeObject(usager);
                out.flush();
            }
            out.writeObject(new Usager("fred", "123", Role.ACHETEUR));;
            out.close();
            file.close();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    public void chargerUsagers(String fichier) throws IOException, ClassNotFoundException {
        // On utilise un try-with-resources pour s'assurer que les flux de données seront automatiquement fermés à la fin du bloc try
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            Usager temoin = null;
            do {
                try {
                    temoin = (Usager)ois.readObject();
                    if (temoin != null) {
                        usagers.add(temoin);
                    }
                } catch (EOFException eof) {
                    // On a atteint la fin du fichier, on arrête la boucle
                    break;
                }
            } while (true);
        } catch (IOException | ClassNotFoundException e) {
            // Le fichier n'existe pas ou il ne contient pas de liste d'usagers valide, on initialise donc une liste vide
            usagers = new ArrayList<>();
        }
    }
    
    

    public String toString() {
        String retour = "";
        for (int i = 0; i < usagers.size(); i++)
            retour += usagers.get(i).toString() + "\n";

        return retour;
    }
}
