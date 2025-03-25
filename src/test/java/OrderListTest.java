import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        ValidatableResponse responseOrderList = order.getOrderList();

        // извлечение списка заказов из ответа
        ArrayList actualList = responseOrderList.extract().path("orders");

        // определение размера списка заказов
        int ordersSize = actualList.size();

        // проверка, что размер списка больше 0
        boolean actual = ordersSize > 0;

        // проверка, что код статуса равен 200 (OK)
        assertEquals("Status Code incorrect", HttpURLConnection.HTTP_OK, responseOrderList.extract().statusCode());

        // проверка, что размер списка заказов больше 0
        assertTrue("Expected order list size more than 0", actual);
    }
}
