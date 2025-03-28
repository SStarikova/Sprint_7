import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.*;

public class OrderListTest {
    private OrderMethods order;

    // Метод, который выполняется перед каждым тестом
    @Before
    public void setUp() {
        // Инициализация клиента заказов
        order = new OrderMethods();
    }

    // проверка получения списка заказов
    @Test
    @DisplayName("Returned order list")
    @Description("Проверка, что в тело ответа возвращается список заказов.")
    public void returnedOrderListTest() {
        ValidatableResponse responseOrderList = order.getOrderList()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)  // проверка статус-кода
                .body("orders", notNullValue())         // проверка наличия списка
                .body("orders", hasSize(greaterThan(0)));  // проверка, что список не пустой
    }
}
