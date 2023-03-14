package com.nhnacademy.controller;

import com.nhnacademy.domain.Basket;
import com.nhnacademy.domain.BuyList;
import com.nhnacademy.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.StringTokenizer;

@Slf4j
public class CartFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodStand");
        Basket basket = new Basket();

        int price = 0;

        String food = req.getParameter("foodInput");

        BuyList buyList = new BuyList();
        StringTokenizer st = new StringTokenizer(food.trim());
        while (st.hasMoreTokens()) {
            buyList.add(new BuyList.Item(st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        try {
            for (int i = 0; i < buyList.getItems().size(); i++) {
                for (int j = 0; j < buyList.getItems().get(i).getAmount(); j++) {
                    boolean existFood = false;
                    for (int z = 0; z < foodStand.getFoods().size(); z++) {
                        if (foodStand.getFoods().get(z).getName().startsWith(buyList.getItems().get(i).getName())) {
                            basket.add(foodStand.getFoods().get(z));
                            price+=foodStand.getFoods().get(z).getPrice();
                            foodStand.getFoods().remove(z);
                            existFood = true;
                            break;
                        }
                    }
                    if (!existFood) {
                        throw new AmountException();
                    }
                }
            }
        } catch (AmountException e) {
            for(int i=0;i<basket.getFoods().size();i++){
                foodStand.add(basket.getFoods().get(i));
            }
            Collections.sort(foodStand.getFoods());
            log.error("",e);
            servletContext.setAttribute("exception",e);
            return "redirect:/errorPage.jsp";
        }

        servletContext.setAttribute("foodStand", foodStand);
        servletContext.setAttribute("basket", basket);
        servletContext.setAttribute("price", price);

        return "redirect:/cartFormView.jsp";
    }
}

class AmountException extends IndexOutOfBoundsException{
    public AmountException() {
    }

    public AmountException(String s) {
        super(s);
    }
}
