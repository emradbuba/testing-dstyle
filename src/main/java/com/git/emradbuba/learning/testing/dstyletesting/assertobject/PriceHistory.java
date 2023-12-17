package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class PriceHistory {
    private BigDecimal currentPrice;
    private BigDecimal priceOneMonthAgo;
    private BigDecimal priceTwoMonthsAgo;
}
