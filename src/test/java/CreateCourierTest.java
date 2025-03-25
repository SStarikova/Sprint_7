import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
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

    // нельзя создать двух одинаковых курьеров;
    @Test
    @DisplayName("Failed create with duplicate courier")
    @Description("Этот тест проверяет, что нельзя повторно создать курьера, тест упадет, т.к. реализация текста отличается от документации")
    public void failedCreateWithDuplicateCourierTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.successLogin();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.isCreated(createResponse);

        // Создаем объект с теми же данными для второго курьера
        ValidatableResponse createDoubleResponse = client.createCourier(courier);
        // проверяем что при создании дублера будет ошибка
        check.doubleCreated(createDoubleResponse);

        // Авторизуемся под созданным курьером для получения id курьера
        Credentials creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loginSuccess(loginResponse);
    }

    // нельзя создать курьера без логина
    @Test
    @DisplayName("Create without login")
    @Description("Этот тест проверяет, можно ли создать курьера без логина")
    public void createWithoutLoginTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.loginWithoutLogin();
        ValidatableResponse createResponse = client.createCourier(courier);
        // проверяем что не создался курьер
        check.isNotCreated(createResponse);
    }

    // нельзя создать курьера без пароля
    @Test
    @DisplayName("Create without password")
    @Description("Этот тест проверяет, можно ли создать курьера без пароля")
    public void createWithoutPasswordTest() {
        // Создаем объект с данными для нового курьера
        Courier courier = Courier.loginWithoutPassword();
        ValidatableResponse createResponse = client.createCourier(courier);
        // проверяем что не создался курьер
        check.isNotCreated(createResponse);
    }
}
