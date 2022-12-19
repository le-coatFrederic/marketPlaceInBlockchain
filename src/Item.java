public class Item {
    protected String name;
    protected float value;

    public Item(String name, float value) {
        this.name = name;
        this.value = value;
    }
    
    public String getname() {
        return name;
    } 

    public float getValue() {
        return value;
    }
    
    public void setName(String name) {
        if (name.length() > 0)
            this.name = name;
    }

    public void setValue(float value) {
        if (value >= 0)
            this.value = value;
    }

    public String toString() {
        return name + " : " + value + "$";
    }
}
