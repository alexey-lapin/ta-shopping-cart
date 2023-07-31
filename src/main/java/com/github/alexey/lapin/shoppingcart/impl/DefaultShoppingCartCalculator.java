package com.github.alexey.lapin.shoppingcart.impl;

import com.github.alexey.lapin.shoppingcart.CalculationRule;
import com.github.alexey.lapin.shoppingcart.CalculationRuleRegistry;
import com.github.alexey.lapin.shoppingcart.PriceRegistry;
import com.github.alexey.lapin.shoppingcart.ShoppingCart;
import com.github.alexey.lapin.shoppingcart.ShoppingCartCalculator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class DefaultShoppingCartCalculator implements ShoppingCartCalculator {

    private final PriceRegistry priceRegistry;
    private final CalculationRuleRegistry calculationRuleRegistry;

    public DefaultShoppingCartCalculator(PriceRegistry priceRegistry, CalculationRuleRegistry calculationRuleRegistry) {
        this.priceRegistry = Objects.requireNonNull(priceRegistry,
                "priceRegistry must not be null");
        this.calculationRuleRegistry = Objects.requireNonNull(calculationRuleRegistry,
                "calculationRuleRegistry must not be null");
    }

    @Override
    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Objects.requireNonNull(shoppingCart, "shoppingCart must not be null");
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<String, Integer> entry : shoppingCart.getItems().entrySet()) {
            String itemName = entry.getKey();
            Integer itemCount = entry.getValue();
            BigDecimal itemPrice = priceRegistry.getPrice(itemName)
                    .orElseThrow(() -> new IllegalArgumentException("No price for " + itemName));
            CalculationRule rule = calculationRuleRegistry.getRule(itemName);
            BigDecimal itemTotalPrice = rule.calculate(itemCount, itemPrice);
            totalPrice = totalPrice.add(itemTotalPrice);
        }
        return totalPrice;
    }

}
