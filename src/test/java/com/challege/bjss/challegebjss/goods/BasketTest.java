package com.challege.bjss.challegebjss.goods;


import com.challege.bjss.challegebjss.goods.basket.Basket;
import com.challege.bjss.challegebjss.goods.basket.Item;
import com.challege.bjss.challegebjss.goods.rules.BreadPricingRule;
import com.challege.bjss.challegebjss.goods.rules.IPricingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    private Basket basket;
    private List<Item> items;
    private List<IPricingRule> pricingRules;
    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        pricingRules = new ArrayList<>();
        basket = new Basket(items, pricingRules);
    }

    @Test
    public void testGetSubtotal_emptyBasket() {
        BigDecimal subtotal = basket.getSubtotal();
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), subtotal);
    }

    @Test
    public void testGetSubtotal_nonEmptyBasket() {
        items.add(Item.SOUP);
        items.add(Item.BREAD);
        BigDecimal subtotal = basket.getSubtotal();
        assertEquals(new BigDecimal("1.45"), subtotal);
    }

    @Test
    public void testGetTotal_emptyBasket() {
        BigDecimal total = basket.getTotal();
        assertEquals(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP), total);
    }

    @Test
    public void testGetTotal_noDiscount() {
        items.add(Item.SOUP);
        items.add(Item.BREAD);
        BigDecimal total = basket.getTotal();
        assertEquals(new BigDecimal("1.45"), total);
    }

    @Test
    public void testGetTotal_with_Discount() {
        items.add(Item.SOUP);
        items.add(Item.SOUP);
        items.add(Item.BREAD);
        pricingRules.add(new BreadPricingRule(items));
        BigDecimal total = basket.getTotal();
        BigDecimal expectedTotal = Item.SOUP.getPrice().multiply(new BigDecimal("2"))
                .add(Item.BREAD.getPrice().multiply(Item.BREAD.getDiscount())).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedTotal, total);
    }


}
