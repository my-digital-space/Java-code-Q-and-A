package org.demo.utility;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductsUtil {

    public static List<Product> getProductsList() {
        return Arrays.asList(
                new Product(1, new BigDecimal("29.99"), 100),
                new Product(2, new BigDecimal("49.99"), 200),
                new Product(3, new BigDecimal("19.99"), 150),
                new Product(4, new BigDecimal("99.99"), 50),
                new Product(5, new BigDecimal("9.99"), 300)
        );
    }

}
