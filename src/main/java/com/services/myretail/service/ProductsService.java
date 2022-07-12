package com.services.myretail.service;

import com.services.myretail.exceptions.ProductNotFoundException;
import com.services.myretail.interfaces.IProductsService;
import com.services.myretail.models.Product;
import com.services.myretail.models.ProductPricing;
import com.services.myretail.models.RedskyProduct;
import com.services.myretail.repository.ProductPricingRepository;
import com.services.myretail.repository.RedskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService implements IProductsService {

    public ProductPricingRepository productRepository;
    public RedskyRepository redskyRepository;

    @Autowired
    public ProductsService(ProductPricingRepository productRepository, RedskyRepository redskyRepository) {
        this.productRepository = productRepository;
        this.redskyRepository = redskyRepository;
    }


    @Override
    public String test() { return "This is a test"; }

    @Override
    public List<ProductPricing> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct (int id) {
        Product product;
        ProductPricing productPricing = productRepository.findById (id);
        RedskyProduct redskyProduct = redskyRepository.getRedskyProduct (id);

        if (productPricing != null && redskyProduct != null) {
            product = createProduct (productPricing, redskyProduct);
        } else
            throw new ProductNotFoundException ();

        return product;
    }

    @Override
    public ProductPricing updateProductPrice (Product product) {

        if (productRepository.existsById (product.getId ())) {
            ProductPricing pp = productRepository.findById (product.getId ());
            pp.getCurrent_price ().setValue (product.getPrice ());
            return productRepository.save (pp);
        } else
            throw new ProductNotFoundException ("Illegal Argument. Product Id does not exist.");
    }

    private Product createProduct(ProductPricing productPricing, RedskyProduct redskyProduct) {
        Product product = new Product ();
        product.setId (productPricing.get_id ());
        product.setName (redskyProduct.getItem ().getProduct_description ().getTitle ());
        product.setPrice (productPricing.getCurrent_price ().getValue ());

        return product;
    }

}
