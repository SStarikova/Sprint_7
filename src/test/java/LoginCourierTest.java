import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class LoginCourierTest {
    private final CourierMethods client = new CourierMethods();
    private final CourierChecks check = new CourierChecks();
    private int courierId;

    // удаляем созданного курьера
    @After
    public void deleteCourier() {
        if (courierId > 0) {
            client.deleteCourier(courierId);
        }
    }

    // успешное создание и логин курьера
    @Test
    @DisplayName("Successfull create and login")
    @Description("Этот тест проверяет, может ли курьер успешно войти в систему, используя созданные валидные учетные данные.")
    public void successfullCreateAndLoginTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.isCreated(createResponse);

        // Авторизуемся под созданным курьером
        Credentials creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loginSuccess(loginResponse);

        assertNotEquals(0, courierId);
    }

    // успешное создание и логин курьера без имени
    @Test
    @DisplayName("Successfull login without name")
    @Description("Этот тест проверяет, может ли курьер успешно войти в систему, используя валидные учетные данные без имени.")
    public void successfullLoginWithoutNameTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.loginWithoutFirstName();
        ValidatableResponse createResponse = client.createCourier(courier);

        // Авторизуемся под созданным курьером
        Credentials creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loginSuccess(loginResponse);

        assertNotEquals(0, courierId);
    }

    // нельзя авторизоваться с невалидными логином
    @Test
    @DisplayName("Failed login with uncorrect login")
    @Description("Этот тест проверяет, может ли курьер войти в систему, используя невалидный логин.")
    public void failedLoginWithUncorrectLoginTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);

        // Авторизуемся под курьером с невалидным логином
        Credentials creds = Credentials.fromCourierWithWrongLogin(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loginWithUncorrectCreds(loginResponse);
    }

    // нельзя авторизоваться с невалидными паролем
    @Test
    @DisplayName("Failed login with uncorrect password")
    @Description("Этот тест проверяет, может ли курьер войти в систему, используя невалидный пароль.")
    public void failedLoginWithUncorrectPasswordTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);

        // Авторизуемся под курьером с невалидным паролем
        Credentials creds = Credentials.fromCourierWithWrongPassword(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loginWithUncorrectCreds(loginResponse);
    }

    // нельзя авторизоваться без пароля
    @Test
    @DisplayName("Failed login with empty password")
    @Description("Этот тест проверяет, может ли курьер войти в систему, используя пустой пароль.")
    public void failedLoginWithEmptyPasswordTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);

        // Авторизуемся под курьером без пароля
        Credentials creds = Credentials.fromCourierWithNullPassword(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loginWithNullCreds(loginResponse);
    }

    // нельзя авторизоваться без логина
    @Test
    @DisplayName("Failed login with empty login")
    @Description("Этот тест проверяет, может ли курьер войти в систему, используя пустой логин.")
    public void failedLoginWithEmptyLoginTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);

        // Авторизуемся под курьером без логина
        Credentials creds = Credentials.fromCourierWithNullLogin(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loginWithNullCreds(loginResponse);
    }
}
