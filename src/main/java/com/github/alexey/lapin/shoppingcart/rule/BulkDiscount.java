package com.github.alexey.lapin.shoppingcart.rule;

import com.github.alexey.lapin.shoppingcart.CalculationRule;

import java.math.BigDecimal;

public class BulkDiscount implements CalculationRule {

    private final int quantityToBuy;
    private final int quantityToPayFor;

    public BulkDiscount(int quantityToBuy, int quantityToPayFor) {
        this.quantityToBuy = quantityToBuy;
        this.quantityToPayFor = quantityToPayFor;
    }

    @Override
    public BigDecimal calculate(int count, BigDecimal price) {
        long calculatedCount = (long) (count / quantityToBuy) * quantityToPayFor + (count % quantityToBuy);
        return price.multiply(BigDecimal.valueOf(calculatedCount));
    }

}
