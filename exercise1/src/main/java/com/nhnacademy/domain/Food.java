package com.nhnacademy.domain;

import java.util.Objects;

public class Food implements Comparable {
    private final String name;
    private final int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int compareTo(Object o) {
        return this.name.compareTo(((Food) o).getName());
    }
}
