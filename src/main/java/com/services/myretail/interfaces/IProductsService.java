package com.services.myretail.interfaces;

import com.services.myretail.exceptions.ProductNotFoundException;
import com.services.myretail.models.Product;
import com.services.myretail.models.ProductPricing;

import java.util.List;

public interface IProductsService {

    String test();

    Product getProduct(int id);

    List<ProductPricing> getAllProducts();

    ProductPricing updateProductPrice (Product product);
}
