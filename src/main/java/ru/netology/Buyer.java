package ru.netology;

import java.util.List;
import java.util.TreeMap;

public class Buyer {
    public static int numberSoldCars = 0;
    private String name;

    Buyer(String name) {
        this.name = name;
    }

    public void buyCar(List nameShop) {
        new Thread(() -> {
            while (true) {
                synchronized (nameShop) {
                    System.out.println("Покупатель " + name + " зашел в салон");
                    if (nameShop.isEmpty()) {
                        try {
                            System.out.println("Машин нет");
                            System.out.println();
                            nameShop.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    } else {
                        numberSoldCars++;
                        System.out.println("МАШИНА ПРОДАНА ПОКУПАТЕЛЮ " + name);
                        nameShop.remove(nameShop.size() - 1);
                        if(numberSoldCars>Main.MAX_SOLD_CARS){
                            return;
                        }
                    }
                }
            }
        }).start();
    }
}
