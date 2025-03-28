import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

// Класс OrderMethods отвечает за взаимодействие с API курьеров
public class OrderMethods extends Client {
    private static final String COURIER = "orders";

    // Метод для получения списка заказов
    @Step("Get order list")
    public ValidatableResponse getOrderList() {
        // Создание GET-запроса для получения списка заказов
        return spec()
                .when()
                .get(COURIER)
                .then().log().all();
    }

    // Метод для создания нового заказа
    @Step("Create order")
    public ValidatableResponse createOrder(Order order) {
        // Создание POST-запроса для создания нового заказа
        return spec()
                .body(order) // Установка тела запроса с объектом заказа
                .when()
                .post(COURIER)
                .then();
    }

    // Метод для отмены заказа по номеру отслеживания
    @Step("Cancel order")
    public ValidatableResponse cancelOrder(int track) {
        return spec()
                .when()
                .delete(COURIER + "/" + track)
                .then();
    }
}
