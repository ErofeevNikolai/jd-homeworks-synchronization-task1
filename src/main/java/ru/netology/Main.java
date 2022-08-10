package ru.netology;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static final int MAX_SOLD_CARS = 10;

    public static void main(String[] args) {
        final List<Car> shop = new LinkedList<>();
        new SupplierCars().supplyCar(shop);
        new Buyer("1").buyCar(shop);
        new Buyer("2").buyCar(shop);
    }
}