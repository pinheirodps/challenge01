package com.challege.bjss.challegebjss.goods.fomatter;

import com.challege.bjss.challegebjss.goods.basket.Basket;
import com.challege.bjss.challegebjss.goods.basket.Item;
import com.challege.bjss.challegebjss.goods.rules.IPricingRule;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Output formatter with discount.
 */
public class OutputFormatterWithDiscount implements IOutputFormatter {
    @Override
    public void print(final Basket basket) {
        Set<Item> items = new HashSet<>(basket.getItems());
        items.stream()
                .sorted(Comparator.comparing(Item::getName))
                .forEach(item -> {
                    BigDecimal discount = basket.getPricingRules().stream()
                            .filter(rule -> rule.isApplicable(item))
                            .map(IPricingRule::calculateDiscount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    if (discount.compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println(item.getName().trim() + " " + item.getDiscount()
                                .multiply(BigDecimal.valueOf(100)).intValueExact() + "% " +
                                "off: -" + discount.multiply(BigDecimal.valueOf(100)).intValueExact() + "p");
                    }
                });
    }
}

