import java.util.concurrent.ThreadLocalRandom;

//Данные для создания курьера
public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier successLogin() {
        int random = ThreadLocalRandom.current().nextInt(100,100_000);
        return new Courier("Sveta" + random, "1234567890", "Sveta");
    }

    public static Courier loginWithoutLogin() {
        return new Courier(null, "1234567890", "Sveta");
    }

    public static Courier loginWithoutPassword() {
        int random = ThreadLocalRandom.current().nextInt(100,100_000);
        return new Courier("Sveta" + random, null, "Sveta");
    }

    public static Courier loginWithoutFirstName() {
        int random = ThreadLocalRandom.current().nextInt(100,100_000);
        return new Courier("Sveta" + random, "1234567890", null);
    }

    public Courier() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
