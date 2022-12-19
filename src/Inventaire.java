import java.util.EmptyStackException;
import java.util.HashMap;

public class Inventaire {
    protected HashMap<String, Item> items = new HashMap<>();

    public Inventaire() {}

    public void addItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Argument item can't be null");
        items.put(item.getname(), item);
    }

    public Item getItem(String name) {
        if (name == null)
            throw new IllegalArgumentException("Argument name can't be null");
        
        if (items.get(name) == null)
            throw new IllegalArgumentException("Item " + name + " is not in the inventory");

        return items.get(name);
    }

    public void removeItem(String name) {
        items.remove(name);
    }
}
