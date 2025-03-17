package com.challege.bjss.challegebjss.goods.rules;


import com.challege.bjss.challegebjss.goods.basket.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplePricingRuleTest {

    @Test
    public void testCalculateDiscount() {
        List<Item> items = new ArrayList<>();
        items.add(Item.APPLE);
        items.add(Item.APPLE);
        ApplePricingRule rule = new ApplePricingRule(items);
        BigDecimal expectedDiscount = Item.APPLE.getPrice().multiply(Item.APPLE.getDiscount());
        assertEquals(expectedDiscount, rule.calculateDiscount());
    }

    @Test
    public void testIsApplicable() {
        ApplePricingRule rule = new ApplePricingRule(null);
        assertTrue(rule.isApplicable(Item.APPLE));
        assertFalse(rule.isApplicable(Item.BREAD));
    }


}