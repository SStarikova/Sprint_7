import io.qameta.allure.Step;

import java.util.List;

public class OrderGenerator {
    // Метод для создания заказа с черным цветом
    @Step("Get black color")
    public static Order getWithBlackColor() {
        return new Order("Svetlana", // Имя заказчика
                "Starikova", // Фамилия заказчика
                "Omsk, Lenina, 10", // Адрес доставки
                "Polyarnaya", // Станция метро
                "+7 (913) 973 58 35", // Номер телефона заказчика
                3, // Время аренды (дней)
                "2025-03-25", // Дата доставки
                "Спасибо!", // Комментарий к заказу
                List.of("BLACK") // Список выбранных цветов (в данном случае - черный)
        );
    }

    // Метод для создания заказа с серым цветом
    @Step("Get grey color")
    public static Order getWithGreyColor() {
        return new Order("Svetlana", // Имя заказчика
                "Starikova", // Фамилия заказчика
                "Omsk, Lenina, 12", // Адрес доставки
                "Lenina", // Станция метро
                "+7-913-973-58-35", // Номер телефона заказчика
                1, // Время аренды (дней)
                "2025-03-26", // Дата доставки
                "Спасибо!", // Комментарий к заказу
                List.of("GREY") // Список выбранных цветов (в данном случае - серый)
        );
    }

    // Метод для создания заказа с черным и серым цветами
    @Step("Get grey and black color")
    public static Order getWithBlackAndGrayColors() {
        return new Order("Svetlana", // Имя заказчика
                "Starikova", // Фамилия заказчика
                "Omsk, Lenina, 13", // Адрес доставки
                "Tyumen", // Станция метро
                "7 913 973 58 35", // Номер телефона заказчика
                6, // Время аренды (дней)
                "2025-03-27", // Дата доставки
                "Спасибо!", // Комментарий к заказу
                List.of("BLACK", "GREY") // Список выбранных цветов (черный и серый)
        );
    }

    // Метод для создания заказа без указания цвета
    @Step("Get without color")
    public static Order getWithoutColor() {
        return new Order("Svetlana", // Имя заказчика
                "Starikova", // Фамилия заказчика
                "Omsk, Lenina, 14", // Адрес доставки
                "Magistal", // Станция метро
                "89139735813", // Номер телефона заказчика
                9, // Время аренды (дней)
                "2025-03-28", // Дата доставки
                "Спасибо!", // Комментарий к заказу
                List.of() // Пустой список цветов (без цвета)
        );
    }
}
