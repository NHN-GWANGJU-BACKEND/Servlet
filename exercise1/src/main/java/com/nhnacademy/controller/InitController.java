package com.nhnacademy.controller;

import com.nhnacademy.domain.Food;
import com.nhnacademy.domain.FoodStand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class InitController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        String[] initFood = servletContext.getInitParameter("foodList").split("\n");

        FoodStand foodStand = new FoodStand();

        for(int i=0;i<initFood.length;i++){
            initFood[i] = initFood[i].trim();

            String countTmp = initFood[i].split(" ")[1];
            int count = Integer.parseInt(countTmp.substring(0,countTmp.length()-1));
            String foodInfo = initFood[i].split(" ")[0];
            String foodName = foodInfo.split("\\(")[0];
            String tmp =foodInfo.split("\\(")[1];
            String foodPrice = tmp.substring(0,tmp.length()-1);

            for(int j=0;j<count;j++){
                foodStand.add(new Food(foodName,Integer.parseInt(foodPrice)));
            }
            Collections.sort(foodStand.getFoods());
        }
        servletContext.setAttribute("foodStand",foodStand);

        return "redirect:/init.jsp";
    }
}
