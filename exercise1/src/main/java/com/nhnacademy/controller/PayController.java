package com.nhnacademy.controller;

import com.nhnacademy.domain.Basket;
import com.nhnacademy.domain.FoodStand;
import com.nhnacademy.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Slf4j
public class PayController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        Basket basket = (Basket) servletContext.getAttribute("basket");
        FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodStand");

        int price = (int) servletContext.getAttribute("price");
        User user = (User) servletContext.getAttribute("user");
        int renewMoney;
        try {
            renewMoney = user.getMoney() - price;
            if(renewMoney<0){
                throw new NotEnoughMoneyException();
            }else{
                user.setMoney(renewMoney);
            }
        }catch (NotEnoughMoneyException ex){
            for(int i=0;i<basket.getFoods().size();i++){
                foodStand.add(basket.getFoods().get(i));
            }
             Collections.sort(foodStand.getFoods());
            log.error("",ex);
            servletContext.setAttribute("exception",ex);
            return "redirect:/errorPage.jsp";
        }
        return "redirect:/pay.jsp";
    }
}

class NotEnoughMoneyException extends NumberFormatException{
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
