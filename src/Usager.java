import java.io.Serializable;
import java.util.Random;
import java.util.Set;

public class Usager implements Serializable, Runnable, Observateur {
    protected String nom;
    protected Motdepasse motDePasse;
    protected Inventaire inventaire;
    protected Role role;
    protected double monnaie;
    protected boolean continuer;

    public Usager(String nom, String motDePasse, Role role) {
            this.nom = nom;
            this.motDePasse = new Motdepasse(motDePasse);
            this.inventaire = new Inventaire();
            this.role = role;
            this.monnaie = 500.00;
            this.continuer = true;
        
    }

    public String getNom() {
        return nom;
    }

    public Role getRole() {
        return role;
    }

    public boolean verifierMotDePasse(String motDePasse) {
        return this.motDePasse.verifierMotDePasse(motDePasse);
    }

    public double getMonnaie() {
        return monnaie;
    }

    public void ajouterObjet(Objet objet) {
        inventaire.ajouterObjet(objet);
    }

    public void retirerObjet(String nom) {
        inventaire.retirerObjet(nom);
    }

    public Objet getObjet(String nom) {
        return inventaire.getObjet(nom);
    }

    public String toString() {
        return "nom : [" + nom + "] mot de passe : [" + motDePasse.toString() + "] role : [" + role + "] monnaie : [" + monnaie + "] inventaire : [" + inventaire + "]";
    }

    public Enchere creerEnchere(String objet, double prixDepart, int duree) {
        System.out.println("L'usager " + nom + " veut creer une enchere son role est : " + role);
        if (!role.equals(Role.VENDEUR))
            return null;

        objet = objet.toUpperCase();

        return new Enchere(inventaire.getObjet(objet), prixDepart, duree);
    }

    public Offre faireUneOffre(double prix) {
        if (!role.equals(Role.ACHETEUR))
            return null;
        return new Offre(prix, this);
    }

    public boolean verifierBloc(Bloc bloc) {
        if (!role.equals(Role.MINEUR))
            throw new IllegalStateException("Vous n'etes pas un mineur.");
        double monnaieAcheteur = bloc.getTransaction().getOffre().getAcheteur().getMonnaie();
        double prixAchete = bloc.getTransaction().getOffre().getPrix();
        if (monnaieAcheteur < prixAchete)
            return false;
        return true;
    }

    public String trouverPrefixe(Bloc bloc) {
        String prefixe = "";
        while (true) {
            prefixe = genererChaineDeCaractereAleatoire();
            StringBuilder chaine = new StringBuilder();
            chaine.append(prefixe);
            chaine.append(bloc.getTransaction().hashCode());

            String hash = "" + Math.abs(chaine.toString().hashCode());
            for (int i = 0; hash.length() < 24; i++)
                hash = "0" + hash;

            System.out.println("Prefixe : " + prefixe + " HashCode : " + hash);

            if (hash.startsWith("00000000000000000000"))
                return prefixe;
        }
    }
    
    private String genererChaineDeCaractereAleatoire() {
        final int longueur = 20;
        StringBuilder prefixe = new StringBuilder();
        Random aleatoire = new Random();

        for (int i = 0; i < longueur; i++) {
            prefixe.append((char)(aleatoire.nextInt(10) + '0'));
        }

        return prefixe.toString();
    }

    public void changerStatut() {
        System.out.println("changement de statut " + continuer);
        if (continuer)
            continuer = false;
    }

    public void run() {
        Random aleatoire = new Random();
        if (role.equals(Role.ACHETEUR)) {
            monnaie = aleatoire.nextFloat(1000);
            System.out.println("Je suis " + nom + ", un acheteur qui a " + monnaie + " Euros");
            while(continuer) { // tout ce que l'acheteur va faire
                System.out.println("Acheteur " + nom + " travaille");
            }
            System.out.println("Fin");
        }

        if (role.equals(Role.VENDEUR)) {
            ajouterObjet(new Objet(NomAleatoire.nomsObjets.avoirNom(), ""));
            for (int i = 0; i < aleatoire.nextInt(5) + 1; i++)
                ajouterObjet(new Objet(NomAleatoire.nomsObjets.avoirNom(), ""));
            System.out.println("Je suis " + nom + ", un vendeur qui a ces objets : " + inventaire);
            while(continuer) { //tout ce que le vendeur va faire 
                if (inventaire.objets.size() != 0) {
                    Set<String> cles = inventaire.objets.keySet();
                    for (String cle : cles) {
                        Blockchain.blockchainPrimaire.ajouterEnchere(creerEnchere(cle, aleatoire.nextInt(50), aleatoire.nextInt(60) + 20));
                    }
                }
            }
            System.out.println("Fin");
        }

        if (role.equals(Role.MINEUR)) {
            System.out.println("Je suis " + nom + ", un mineur");
            while(continuer) { // tout ce que le mineur va faire
                System.out.println("Mineur " + nom + " travaille");
            }
            System.out.println("Fin");
        }

        System.out.println("Fin");
    }

    @Override
    public void misAJour(ObservOptions option) {
        if (option.equals(ObservOptions.EnchereDemarre)) {}
        if (option.equals(ObservOptions.EnchereTermine)) {}
        if (option.equals(ObservOptions.OffreMeilleure)) {}
    }
}