// Данные для логина курьера
public class Credentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static Credentials fromCourier(Courier courier){
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials fromCourierWithWrongLogin(Courier courier){
        return new Credentials("Ляля2020", courier.getPassword());
    }

    public static Credentials fromCourierWithWrongPassword(Courier courier){
        return new Credentials(courier.getLogin(), "1234");
    }

    public static Credentials fromCourierWithNullLogin(Courier courier){
        return new Credentials("", courier.getPassword());
    }

    public static Credentials fromCourierWithNullPassword(Courier courier){
        return new Credentials(courier.getLogin(), "");
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
}
