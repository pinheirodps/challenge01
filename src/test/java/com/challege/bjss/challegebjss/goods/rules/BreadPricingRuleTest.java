package com.challege.bjss.challegebjss.goods.rules;


import com.challege.bjss.challegebjss.goods.basket.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BreadPricingRuleTest {

    @Test
    public void testCalculateDiscount_lessThan2Soup() {
        List<Item> items = Arrays.asList(Item.SOUP, Item.BREAD);
        BreadPricingRule rule = new BreadPricingRule(items);
        BigDecimal discount = rule.calculateDiscount();
        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    public void testCalculateDiscount_2SoupOrMore() {
        List<Item> items = Arrays.asList(Item.SOUP, Item.SOUP, Item.SOUP, Item.BREAD);
        BreadPricingRule rule = new BreadPricingRule(items);
        BigDecimal discount = rule.calculateDiscount();
        assertEquals(discount,  Item.BREAD.getPrice().multiply(Item.BREAD.getDiscount()));
    }

    @Test
    public void testIsApplicable() {
        BreadPricingRule rule = new BreadPricingRule(null);
        assertTrue(rule.isApplicable(Item.BREAD));
        assertFalse(rule.isApplicable(Item.SOUP));
    }
}
