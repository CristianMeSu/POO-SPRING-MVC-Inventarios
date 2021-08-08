package com.demo.utility;

import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.models.Supplier;

import java.util.List;

public class Validation {

    public boolean haveAnyProductByCategory(List<Product> products, Category category) {
        boolean definition = false;
        for (Product product : products) {
            if (product.getCategory().getIdCategory() == category.getIdCategory()) {
                definition = true;
                break;
            }
        }
        return definition;
    }

    public boolean haveAnyProductBySupplier(List<Product> products, Supplier supplier) {
        boolean definition = false;
        for (Product product : products) {
            if (product.getSupplier().getIdSupplier() == supplier.getIdSupplier()) {
                definition = true;
                break;
            }
        }
        return definition;
    }
}
