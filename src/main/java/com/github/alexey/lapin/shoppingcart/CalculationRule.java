package com.github.alexey.lapin.shoppingcart;

import java.math.BigDecimal;

/**
 * Represents a calculation rule for an item.
 */
@FunctionalInterface
public interface CalculationRule {

    CalculationRule DEFAULT = (count, price) -> price.multiply(BigDecimal.valueOf(count));

    /**
     * Calculates the price for the given count of items.
     *
     * @param count the count of items
     * @param price the price of one item
     * @return the total price for the given count of items
     */
    BigDecimal calculate(int count, BigDecimal price);

}
