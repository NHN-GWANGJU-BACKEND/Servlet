package com.nhnacademy.domain;

import java.util.ArrayList;
import java.util.Comparator;

public class FoodStand {
    private final ArrayList<Food> foods = new ArrayList<>();
    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void add(Food onion1) {
        foods.add(onion1);
    }


}
