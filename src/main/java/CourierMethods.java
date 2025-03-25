import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

// Класс CourierClient отвечает за взаимодействие с API курьеров
public class CourierMethods extends Client{
    private static final String COURIER = "courier";

    // Метод для логина курьера
    @Step("Login courier")
    public ValidatableResponse logIn(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER + "/login")
                .then().log().all();
    }

    // Метод для создания курьера
    @Step("Create courier")
    // Создание курьера
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER)
                .then().log().all();
    }

    // Метод для удаления курьера
    @Step("Delete courier")
    public void deleteCourier(int id) {
        spec()
                .body(Map.of("id", id))
                .when()
                .delete(COURIER + "/" + id)
                .then().log().all();
    }
}
