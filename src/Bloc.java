import java.util.Date;

public class Bloc {
    private int hash;
    private int hashPrecedent;
    private Transaction transaction;
    private Date date;

    public Bloc (int hashPrecedent, Transaction transaction) {
        this.hashPrecedent = hashPrecedent;
        this.transaction = transaction;
        date = new Date();
        hashage();
    }

    private void hashage() {
        StringBuilder hash = new StringBuilder();
        hash.append(hashPrecedent);
        if (transaction != null)
            hash.append(transaction.toString    ());
        hash.append(date.toString());

        this.hash = hash.toString().hashCode(); //ce hash est très faible et peut facilement etre trouvé, on aurait pu utiliser d'autres fonctions de hachage comme SHA-256.
    }

    public int getHash() {
        return hash;
    }

    public int getHashPrecedent() {
        return hashPrecedent;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return "Hash : [" + hash + "] hashPrecedent : [" + hashPrecedent + "] transaction : [" + transaction + "] date : [" + date + "]"; 
    }
}
