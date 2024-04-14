package org.example;

import org.example.exceptions.ClientNotFoundException;
import org.example.exceptions.ProductNotFoundException;
import org.example.exceptions.QuantityIsNegativeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    private static List<Client> clients;
    private static List<Product> products;
    private static List<Order> orders;
    private static Order order;
    private static Holiday.day toDay;

    public Store(Holiday.day holiday) {
        this.toDay = holiday;
        clients = new ArrayList<>(List.of(
                new Client("Altukhov", "Artem",
                        "Andreevich","+7-901-000-00-01",
                        "MAN", LocalDate.of(1987, 5,15)),
                new Client("Ivanov", "Ivan",
                        "Ivanovich", "+7-999-999-99-99",
                        "MAN", LocalDate.of(1957, 2, 28)),
                new Client("Petrov", "Petr",
                        "Petrovich", "+7-947-156-48-88",
                        "MAN",LocalDate.of(1986,5,17)),
                new Client("Clim", "Veronica",
                        "Ivanovna", "+7-901-123-40-32",
                        "WOMAN", LocalDate.of(1998,8,31)),
                new Client("Mitrofanofa", "Nataliya",
                        "Biktorovna","+7-942-612-12-32",
                        "WOMAN",LocalDate.of(1975,1,1)),
                new Client("Ivanova", "Margarita",
                        "Pavlovna", "+7-951-055-54-87",
                        "WOMAN",LocalDate.of(2000,4,12)),
                new Client("Yarkov", "Oleg",
                        "Igorevich","+7-953-654-78-98",
                        "MAN",LocalDate.of(1948,4,7)),
                new Client("Pturova", "Anna",
                        "Alekseevna","+7-951-555-33-11",
                        "WOMAN",LocalDate.of(1977, 5,15))
        ));
        products = new ArrayList<>(List.of(
                new Product("Milk", 75),
                new Product("Bread", 37),
                new Product("Cheese", 357),
                new Product("Button", 115),
                new Product("Water", 51),
                new Product("Tea", 78),
                new Product("Coffee", 370)
        ));

        orders = new ArrayList<>();
    }

    public static Holiday.day getToDay() {
        return toDay;
    }

    /**
     * Создание заказа
     * @param clientId ID клиента
     * @param productId ID товара
     * @param quantity количество товара
     * @return ID заказа
     * @throws ClientNotFoundException клиент не найден
     * @throws ProductNotFoundException продукт не найден
     * @throws QuantityIsNegativeException количество продукта <= 0
     */
    public int createOrder (int clientId, int productId, int quantity)
        throws ClientNotFoundException, ProductNotFoundException, QuantityIsNegativeException {
        String holiday = String.valueOf(toDay);
        boolean flag = false;
        int indexOfClient = -1;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == clientId) {
                indexOfClient = i;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new ClientNotFoundException("Клиент с ID " + clientId + " не найден");
        }

        long count = products.stream().filter(o -> o.getProductId() == productId).count();
        if (count == 0) {
            throw new ProductNotFoundException("Продукт с ID " + productId + " не найден");
        }
        if (quantity <= 0) {
            throw new QuantityIsNegativeException("Введеное количество продуктов: " + quantity + " (количество должно быть больше 0)");
        }

        order = new Order(clients.get(indexOfClient), holiday, productId, quantity);
        orders.add(order);
        return order.getOrderId();
    }

    public static void addClientToList (Client client) {
        clients.add(client);
    }

    public void addProductToOrder(int orderId, int productId, int quantity) {
        long count = products.stream().filter(o -> o.getProductId() == productId).count();
        try {
            if (count == 0) {
                throw new ProductNotFoundException("The product with ID " + productId + " was not found");
            }
            if (quantity <= 0) {
                throw new QuantityIsNegativeException("The number of products entered: " + quantity + " (must be greater than 0)");
            }
            Order order = orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().get();
            order.addProduct(productId, quantity);
        } catch (ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }


    public static List<Client> getAllClient() {
        return clients;
    }

    public static List<Product> getAllProducts() {
        return products;
    }

    public static Product getProduct(int productId) {
        Product product = null;
        for (Product p : products) {
            if (p.getProductId() == productId) {
                product = p;
            }
        }
        return product;
    }


    public List<Client> getClients() {
        return clients;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static Order getOrder() {
        return order;
    }

    /**
     * Выводит все заказы в магазине
     */
    public static void printStore() {
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println(o.getClient());
            for (Map.Entry<Integer, Integer> entry : o.getProducts().entrySet()) {
                System.out.println(products.get(entry.getKey()-1)
                                   + ", count: " + entry.getValue()
                                   + ", total cost: " + products.get(entry.getKey()-1).getPrice() * entry.getValue());
            }
            System.out.println("Total: " + o.getCostAllProduct()
                               + " (discount: "
                               + o.getDiscount(toDay, o.getClient().getSex())
                               + "%)");

            System.out.println("-".repeat(20));

        }
    }
}