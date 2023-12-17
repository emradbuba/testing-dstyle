package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductsService {

    public Product getSeasonalAndCheapestProduct() {
        // fake implementation
        return Product.builder()
                .productName("ProductName")
                .property(ProductProperties.SEASONAL)
                .priceHistory(PriceHistory.builder()
                        .currentPrice(new BigDecimal("9.99"))
                        .priceOneMonthAgo(new BigDecimal("11.99"))
                        .priceTwoMonthsAgo(new BigDecimal("12.99"))
                        .build())
                .build();
    }
}
