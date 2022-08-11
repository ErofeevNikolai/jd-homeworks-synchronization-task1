package ru.netology;

import java.util.List;

public class SupplierCars {
    private final static int MAX_CAR_IN_SHOP = 3;

    public void supplyCar(List shopCar){
        new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                synchronized (shopCar) {
                    shopCar.add(new Car(i));
                    System.out.println("Производитель Toyota выпустил 1 авто");
                    shopCar.notify();
                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    return;
                }
                if(shopCar.size() > (MAX_CAR_IN_SHOP-1)){
                    return;
                }
            }
        }).start();
    }
}
