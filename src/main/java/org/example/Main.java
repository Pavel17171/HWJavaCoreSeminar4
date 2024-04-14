package org.example;

import org.example.exceptions.ClientNotFoundException;
import org.example.exceptions.ProductNotFoundException;
import org.example.exceptions.QuantityIsNegativeException;

import java.time.LocalDate;
import java.util.List;

import static org.example.Holiday.day.*;
import static org.example.Store.printStore;


public class Main {
    public static void main(String[] args) throws
            ClientNotFoundException,
            QuantityIsNegativeException,
            ProductNotFoundException {



        // Создание магазина
        Holiday.day toDay;
        System.out.println("""
                           Выберите правздник:
                           1 - Новый год
                           2 - 23 февраля
                           3 - 8 марта
                           (любое другое значение - обычный день)
                           """);
        switch (Method.answer()) {
            case 1 -> {
                System.out.println("Сегодня Новый год!\n");
                toDay = NEW_YEAR;
            }
            case 2 -> {
                System.out.println("Сегодня 23 февраля!\n");
                toDay = FEBRUARY_23;
            }
            case 3 -> {
                System.out.println("Сегодня 8 марта!\n");
                toDay = MARCH_8;
            }
            default -> {
                System.out.println("Сегодня обычный день!\n");
                toDay = JUST_DAY;
            }
        }
        Store store = new Store(toDay);

        System.out.println("\n" + "*".repeat(20));

        List<Client> clients = store.getClients(); // Клиенты созданы в магазине

        Client cl = new Client("Popova", "Olga",
                "Vladimirovna",
                "+7-904-831-93-31", "WOMAN",
                LocalDate.of(2001, 12, 30));

        clients.add(cl);

        List<Product> products = store.getProducts(); // Продукты созданы в магазине

        // Добавление нового продукта
        Product sugar = new Product("Sugar", 100);
        products.add(sugar);

        // Список клиентов
        for (Client c : clients) {
            System.out.println(c);
        }
        System.out.println("*".repeat(20));

        // Добавление товара в список продуктов
        products.add(new Product("Candies", 157));

        // Список продуктов в магазине
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("*".repeat(20));


        int order1;
        int order2;
        int order3;
        int order4;
        int order5;


        System.out.println("\n*** Заказ 1 ***");

        // Создание правильного заказа (MAN)
        try {
            order1 = store.createOrder(clients.get(1).getId(),  3, 11);
            store.addProductToOrder(order1, 1, 5);
            store.addProductToOrder(order1, 6, 17);
            System.out.println("Заказ создан успешно!");
        } catch (ClientNotFoundException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n*** Заказ 2 ***");

        // Некорректный ID клиента
        try {
            order2 = store.createOrder(1019, 2, 25);
            store.addProductToOrder(order2, 2, 35);
            System.out.println("Заказ создан успешно!");
        } catch (ClientNotFoundException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n*** Заказ 3 ***");


        // Некорректный ID продукта
        try {
            order3 = store.createOrder(1005, 50, 25);
            store.addProductToOrder(order3, 2, 35);
            System.out.println("Заказ создан успешно!");
        } catch (ClientNotFoundException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n*** Заказ 4 ***");


        // Некорректное количество продукта
        try {
            order4 = store.createOrder(1005, 3, 0);
            store.addProductToOrder(order4, 2, 35);
            System.out.println("Заказ создан успешно!");
        } catch (ClientNotFoundException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n*** Заказ 5 ***");

        // Создание правильного заказа (WOMAN)
        try {
            order5 = store.createOrder(clients.get(4).getId(),  7, 23);
            store.addProductToOrder(order5, 2, 3);
            store.addProductToOrder(order5, 5, 1);
            System.out.println("Заказ создан успешно!");
        } catch (ClientNotFoundException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n" + "*/* ".repeat(20) + "\n");
        System.out.println("Все заказы в магазине:\n");
        printStore();
        System.out.println("\n" + "*/* ".repeat(20) + "\n");

    }

}