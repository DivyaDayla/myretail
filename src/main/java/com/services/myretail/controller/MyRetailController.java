package com.services.myretail.controller;

import com.services.myretail.exceptions.ProductNotFoundException;
import com.services.myretail.models.Product;
import com.services.myretail.models.ProductPricing;
import com.services.myretail.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/myretail")
public class MyRetailController {

    public ProductsService productsService;

    @Autowired
    public MyRetailController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/test")
    private String test() {
        return productsService.test();
    }

    @GetMapping("/allProducts")
    private List<ProductPricing> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("products/{id}")
    private Product getProduct(@PathVariable("id") int id) throws ProductNotFoundException {
        return productsService.getProduct(id);
    }

    @PutMapping("products/{id}")
    private ProductPricing updateProductPrice(@RequestBody Product product, @PathVariable int id) {
        if(product.getId () != id)
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST,
                    "Mismatch between Product Id in the request and the Product Id in Request Body.");

        return productsService.updateProductPrice (product);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product Not Found")
    private void handleException() {
    }

}
