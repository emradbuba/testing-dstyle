package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

    private String productName;

    @Singular
    private List<ProductProperties> properties;

    private PriceHistory priceHistory;
}
