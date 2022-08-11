package ru.netology;

import java.util.List;

public class Buyer {
    public static int numberSoldCars = 0;
    private String name;
    private Thread thread;

    Buyer(String name) {
        this.name = name;
    }

    public void buyCar(List nameShop) {
        Runnable logick = (() -> {
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
        });
        thread = new Thread(logick);
        thread.start();
    }

    public void stopBuyer() {
    if (numberSoldCars >= Main.MAX_SOLD_CARS) {
            thread.interrupt();
        }
    }
}
