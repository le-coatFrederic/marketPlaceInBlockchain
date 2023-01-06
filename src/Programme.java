import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programme {

    public Programme() {
    }

    public static void main(String[] args) {
        ListeUsager usagers = new ListeUsager();
        int nbrAcheteur = 0;
        int nbrVendeur = 0;
        int nbrMineur = 0;
        NomAleatoire noms = NomAleatoire.nomsBasiques;

        Scanner sc = new Scanner(System.in);

        System.out.println("Nombre d'acheteur ?");
        nbrAcheteur = sc.nextInt();

        System.out.println("Nombre de vendeur ?");
        nbrVendeur = sc.nextInt();

        System.out.println("Nombre de mineur ?");
        nbrMineur = sc.nextInt();

        System.out.println("Nombre d'enchere ?");
        Blockchain.blockchainPrimaire.setNbrEnchere(sc.nextInt());

        int i;
        int taille = usagers.getUsagers().size();
        for (i = 0; i < nbrMineur; i++) {
            usagers.ajouterUsager(new Usager(noms.avoirNom(), "00000", Role.MINEUR));
            Blockchain.blockchainPrimaire.ajouterObservateur(ObservOptions.BlocAVerifier, usagers.getUsagers().get(taille + i));
            Blockchain.blockchainVerifiee.ajouterObservateur(ObservOptions.BlocAVerifier, usagers.getUsagers().get(taille + i));
        }

        taille += nbrMineur;
        for (i = 0; i < nbrVendeur; i++) {
            usagers.ajouterUsager(new Usager(noms.avoirNom(), "22222", Role.VENDEUR));
        }

        taille += nbrVendeur;
        for (i = 0; i < nbrAcheteur; i++) {
            usagers.ajouterUsager(new Usager(noms.avoirNom(), "11111", Role.ACHETEUR));
            Blockchain.blockchainPrimaire.ajouterObservateur(ObservOptions.EnchereDemarre, usagers.getUsagers().get(taille + i));
            Blockchain.blockchainVerifiee.ajouterObservateur(ObservOptions.EnchereTermine, usagers.getUsagers().get(taille + i));
        }

        taille += nbrMineur;
        ArrayList<Thread> threads = new ArrayList<>();

        for (i = 0; i < usagers.getUsagers().size(); i++) {
            threads.add(new Thread(usagers.getUsagers().get(i)));
            threads.get(i).start();
        }
            
        /*usagers.ajouterUsager(new Usager("Fred", "01082000", Role.ACHETEUR));
        usagers.ajouterUsager(new Usager("Seb", "123456", Role.ACHETEUR));
        usagers.ajouterUsager(new Usager("Audray", "01082000", Role.VENDEUR));
        usagers.ajouterUsager(new Usager("Sushi", "28212", Role.VENDEUR));
        usagers.ajouterUsager(new Usager("ChatGPT", "12", Role.ACHETEUR));
        usagers.ajouterUsager(new Usager("G", "28212", Role.MINEUR));
        usagers.ajouterUsager(new Usager("H", "12", Role.MINEUR));

        usagers.getUsager("Fred").ajouterObjet(new Objet("Rasoir", "Permet de se raser."));
        usagers.getUsager("Fred").ajouterObjet(new Objet("Ampoule", "Emet de la lumiere."));
        usagers.getUsager("Fred").ajouterObjet(new Objet("Tournevis", "Permet de visser ou devisser des vis."));
        usagers.getUsager("Seb").ajouterObjet(new Objet("Pierre", "Il a taillé cette pierre."));
        usagers.getUsager("Audray").ajouterObjet(new Objet("Blouse", "Pour se proteger."));
        usagers.getUsager("Audray").ajouterObjet(new Objet("Seringue", "Pour administrer des produits dans le sang, ou recuperer du sang."));
        usagers.getUsager("Sushi").ajouterObjet(new Objet("Croquette", "Pour se nourrir, il y en a de tous les gouts."));
        usagers.getUsager("ChatGPT").ajouterObjet(new Objet("Serveur", "Permet de communiquer avec le monde exterieur."));

        System.out.println(usagers);

        Blockchain blockchain = new Blockchain();
        Enchere enchere = usagers.getUsager("Audray").creerEnchere("Blouse", 5, 50);
        System.out.println(enchere);
        Offre offre = usagers.getUsager("Seb").faireUneOffre(10);
        blockchain.ajouterBloc(new Transaction(enchere, offre));

        if (usagers.getUsager("G").verifierBloc(blockchain.getBlocs().get(blockchain.getBlocs().size()-1)))
            usagers.getUsager("G").trouverPrefixe(blockchain.getBlocs().get(blockchain.getBlocs().size()-1));*/ 

        System.out.println("Debut chronos");

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        System.out.println("Fin chronos");
        for (i = 0; i < usagers.getUsagers().size(); i++)
            usagers.getUsagers().get(i).changerStatut();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.getStackTrace();
        }

        for (Enchere enchere : Blockchain.blockchainPrimaire.getAllEncheres())
            System.out.println(enchere);
    }
}
