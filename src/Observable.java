import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Observable {
    public Map<ObservOptions, ArrayList<Observateur>> observateurs;

    public Observable() {
        observateurs = new HashMap<>();
        Arrays.stream(ObservOptions.values()).forEach(options -> observateurs.put(options, new ArrayList<>())); //permet d'initialiser pour toutes les options dans ObservOption un arraylist
    }

    public void ajouterObservateur(ObservOptions option, Observateur observateur) {
        if (observateur == null)
            throw new IllegalArgumentException("L'argument observateur ne peut pas etre null");
        observateurs.get(option).add(observateur);
    }

    public void supprimerObservateur(ObservOptions option, Observateur observateur) {
        if (observateur == null)
            throw new IllegalArgumentException("L'argument observateur ne peut pas etre null");
        observateurs.get(option).remove(observateur);
    }

    public void notifyAll (ObservOptions option) {
        observateurs.get(option).forEach(observateur -> observateur.misAJour(option));
    }
}
