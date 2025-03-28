import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderMethods orderMethods = new OrderMethods();
    private Order order; // Заказ, который мы будем создавать
    private int statusCode; // Ожидаемый статус-код ответа
    private int trackNumber; // номер отслеживания

    // Конструктор для инициализации параметров теста
    public CreateOrderTest(Order order, int statusCode) {
        this.order = order; // Инициализация объекта заказа
        this.statusCode = statusCode; // Инициализация ожидаемого статус-кода
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, Ожидаемый статус: {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {OrderGenerator.getWithBlackColor(), HttpURLConnection.HTTP_CREATED}, // Заказ с черным цветом
                {OrderGenerator.getWithGreyColor(), HttpURLConnection.HTTP_CREATED}, // Заказ с серым цветом
                {OrderGenerator.getWithBlackAndGrayColors(), HttpURLConnection.HTTP_CREATED}, // Заказ с черным и серым цветами
                {OrderGenerator.getWithoutColor(), HttpURLConnection.HTTP_CREATED} // Заказ без цвета
        };
    }

    // Метод, который будет выполнен перед каждым тестом
    @Before
    public void setUp() {
        orderMethods = new OrderMethods(); // Инициализация клиента для создания заказа
    }

    // Тест, который проверяет создание заказа
    @Test
    @DisplayName("Order can be created")
    @Description("Этот тест проверяет возможность создания заказа и наличие в ответе валидного номера отслеживания.")
    // Описание теста
    public void orderCanBeCreatedTest() {
        // Создание заказа и получение ответа
        ValidatableResponse responseCreate = orderMethods.createOrder(order)
                .assertThat()
                .statusCode(statusCode) // Проверка, что фактический статус-код совпадает с ожидаемым
                .body("track", notNullValue()); // Проверка, что номер отслеживания не является null

        // получаем номер отслеживания из ответа
        trackNumber = responseCreate.extract().path("track");
    }

    // Метод для отмены заказа
    @After
    public void deleteOrder() {
        orderMethods.cancelOrder(trackNumber);
    }
}
