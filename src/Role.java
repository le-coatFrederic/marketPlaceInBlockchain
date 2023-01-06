import java.io.Serializable;

public class Role implements Serializable{
    public static Role VENDEUR = new Role("VENDEUR");
    public static Role ACHETEUR = new Role("ACHETEUR");
    public static Role MINEUR = new Role("MINEUR");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String toString() {
        return role;
    }
}
