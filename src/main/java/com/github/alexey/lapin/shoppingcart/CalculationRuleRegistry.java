package com.github.alexey.lapin.shoppingcart;

/**
 * Provides calculation rules for items.
 */
public interface CalculationRuleRegistry {

    /**
     * Returns a calculation rule for the given item.
     *
     * @param name the name of the item
     * @return the calculation rule for the given item
     */
    CalculationRule getRule(String name);

}
