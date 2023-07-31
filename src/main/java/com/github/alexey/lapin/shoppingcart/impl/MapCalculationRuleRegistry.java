package com.github.alexey.lapin.shoppingcart.impl;

import com.github.alexey.lapin.shoppingcart.CalculationRule;
import com.github.alexey.lapin.shoppingcart.CalculationRuleRegistry;

import java.util.Map;
import java.util.Objects;

public class MapCalculationRuleRegistry implements CalculationRuleRegistry {

    private final Map<String, CalculationRule> rules;

    public MapCalculationRuleRegistry(Map<String, CalculationRule> rules) {
        this.rules = Objects.requireNonNull(rules, "rules must not be null");
    }

    @Override
    public CalculationRule getRule(String name) {
        return rules.getOrDefault(name, CalculationRule.DEFAULT);
    }

}
