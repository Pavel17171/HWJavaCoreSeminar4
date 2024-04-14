package org.example;

import java.util.HashMap;
import java.util.Map;


public class Order {
    final int discountNewYear = 20;
    final int discountFebruary_23 = 15;
    final int discountMarch_8 = 15;
    private final int orderId;
    static int nextOrderId = 1;
    private final Client client;
    private final Map<Integer, Integer> products;
    private int quantity;
    private final Holiday.day holiday;
    private int productId;
    private double discount = 0.0;

    public Order(Client client, String holiday, int productId, int quantity) {
        this.orderId = nextOrderId++;
        this.client = client;
        this.holiday = Holiday.day.valueOf(holiday);
        this.discount = discount(Holiday.day.valueOf(holiday));
        products = new HashMap<Integer, Integer>();
        products.put(productId, quantity);
    }

    public int getOrderId() {
        return orderId;
    }

    public Client getClient() {
        return client;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }


    public void addProduct(Integer productId, Integer quantity) {
        products.put(productId, quantity);
    }

    double discount(Holiday.day holiday) {
        double percent = 1.0;
        switch (holiday) {
            case NEW_YEAR -> percent = percent - (percent  * discountNewYear / 100);
            case FEBRUARY_23 -> {
                if (this.client.getSex().equals(Gender.gender.MAN)) {
                    percent = percent - (percent * discountFebruary_23 / 100);
                }
            }
            case MARCH_8 -> {
                if (this.client.getSex().equals(Gender.gender.WOMAN)) {
                    percent = percent - (percent * discountMarch_8 / 100);
                }
            }
        }
        return percent;
    }

    public int getDiscount (Holiday.day toDay, Gender.gender sex) {
        int percent = 0;
        if (toDay == Holiday.day.NEW_YEAR) {
            percent = discountNewYear;
        } else if (toDay == Holiday.day.FEBRUARY_23 && sex.equals(Gender.gender.MAN)) {
            percent = discountFebruary_23;
        } else if (toDay == Holiday.day.MARCH_8 && sex.equals(Gender.gender.WOMAN)) {
            percent = discountMarch_8;
        }
        return percent;
    }

    public static void printOrder (Order order) {
        System.out.println(
                "Order: " + order.getOrderId() + ": \n"
                + order.client
        );
        printProduct(order);
    }


    public static void printProduct(Order order) {
        for (Integer product : order.getProducts().keySet()) {
            System.out.println(product);
        }
    }

    /**
     * Возвращает строку из продуктов с количеством и общей стоимостью по каждому продукту
     * @param products продукты
     * @return стпрока продуктов
     */
    public static String productsToString(Map<Integer, Integer> products) {
        String s = "";
        for (int i : products.keySet()) {
            Product product = Store.getProduct(i);
            int count = products.get(i);
            s += ("\n" + product.toString())
                 + ", count: " + count
                 + ", total cost: " + product.getPrice() * count;
            System.out.println(i);
        }
        return s;
    }


    public double getCostAllProduct() {
        double sum = 0;
        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            sum += (entry.getValue()) * Store.getProduct(entry.getKey()).getPrice();
        }

        sum *= discount(holiday);
        return Math.round(sum * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "\nToday is " + holiday + "\n"
               + "Order ID: " + orderId
               + ", client ID:" + client.getId()
               + ", products: " + productsToString(products)
               + "\nTotal: " + getCostAllProduct() + " (discount: " + getDiscount(holiday, this.client.getSex())+ "%)\n";
    }
}