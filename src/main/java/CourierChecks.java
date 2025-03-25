import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierChecks {

    // Проверка на успешную авторизацию
    @Step("Success login")
    public int loginSuccess(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
        return id;
    }

    // Проверка на провальную авторизацию с неправильным данными курьера
    @Step("Failed login with uncorrect credentials")
    public void loginWithUncorrectCreds(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    // Проверка на провальную авторизацию с пустыми данными курьера
    @Step("Failed login with null credentials")
    public void loginWithNullCreds(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    // Проверка на то, что курьер создан
    @Step("Courier is created")
    public void isCreated(ValidatableResponse createResponse) {
        createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .assertThat()
                .body("ok", equalTo(true));
    }

    // Проверка на то, что курьер не создан
    @Step("Courier is not created")
    public void isNotCreated(ValidatableResponse createResponse) {
        createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    // Проверка на то, что курьер дублируется
    @Step("Courier is duplication")
    public void doubleCreated(ValidatableResponse createResponse) {
        createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется")); // здесь будет ошибка, так как реализация текста отличается от документации
    }
}