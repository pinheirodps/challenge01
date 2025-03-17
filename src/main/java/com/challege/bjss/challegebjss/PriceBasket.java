package com.challege.bjss.challegebjss;

import com.challege.bjss.challegebjss.goods.basket.Basket;
import com.challege.bjss.challegebjss.goods.basket.BasketFactory;
import com.challege.bjss.challegebjss.goods.fomatter.IOutputFormatter;
import com.challege.bjss.challegebjss.goods.fomatter.OutputFormatterWithDiscount;
import com.challege.bjss.challegebjss.goods.fomatter.OutputFormatterWithoutDiscount;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * The type Price basket.
 */


@SpringBootApplication
public class PriceBasket {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Basket basket = BasketFactory.createBasket(args);
        BigDecimal subtotal = basket.getSubtotal();
        BigDecimal total = basket.getTotal();
        System.out.println("Subtotal: £" + subtotal);
        IOutputFormatter formatter = basket.hasDiscount() ?
                new OutputFormatterWithDiscount() : new OutputFormatterWithoutDiscount();
        formatter.print(basket);
        System.out.print("Total: £" + total);
    }

}
