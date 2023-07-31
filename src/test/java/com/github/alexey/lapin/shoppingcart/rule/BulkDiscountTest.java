package com.github.alexey.lapin.shoppingcart.rule;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BulkDiscountTest {

    @Test
    void should_calculateCorrectTotal_when_noDiscount() {
        BulkDiscount rule = new BulkDiscount(1, 1);

        assertThat(rule.calculate(1, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(1));
        assertThat(rule.calculate(2, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(2));
        assertThat(rule.calculate(3, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(3));
    }

    @Test
    void should_calculateCorrectTotal_when_twoForOneDiscount() {
        BulkDiscount rule = new BulkDiscount(2, 1);

        assertThat(rule.calculate(1, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(1));
        assertThat(rule.calculate(2, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(1));
        assertThat(rule.calculate(3, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(2));
    }

    @Test
    void should_calculateCorrectTotal_when_threeForTwoDiscount() {
        BulkDiscount rule = new BulkDiscount(3, 2);

        assertThat(rule.calculate(1, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(1));
        assertThat(rule.calculate(2, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(2));
        assertThat(rule.calculate(3, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(2));
        assertThat(rule.calculate(4, BigDecimal.ONE)).isEqualByComparingTo(BigDecimal.valueOf(3));
    }

}