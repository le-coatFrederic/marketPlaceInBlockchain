import java.io.Serializable;

public class Objet implements Serializable {
    // L'objet est ce qui va être vendu.

    protected String nom;
    protected String description;
    protected boolean modifiable; // Un objet ne peut être modifié pendant qu'il est mis en vente.

    public Objet(String nom, String description) {
        this.nom = nom.toUpperCase();
        this.description = description;
        modifiable = true;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (modifiable)
            this.description = description;
    }

    public void changerStatut() {
        if (modifiable)
            modifiable = false;
        else
            modifiable = true;
    }

    public String toString() {
        return "nom : [" + nom + "] description : [" + description + "] modifiable : [" + modifiable + "]";
    }
}