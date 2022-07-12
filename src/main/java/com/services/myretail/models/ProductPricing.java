package com.services.myretail.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productPricing")
public class ProductPricing {

    @Id
    private int _id;
    private CurrentPrice current_price;

    public ProductPricing (int _id, CurrentPrice current_price) {
        this._id = _id;
        this.current_price = current_price;
    }

    public int get_id () { return _id; }

    public void set_id (int _id) { this._id = _id; }

    public CurrentPrice getCurrent_price () {
        return current_price;
    }

    public void setCurrent_price (CurrentPrice current_price) {
        this.current_price = current_price;
    }

    @Override
    public String toString() {
        return "Product { " +
                "id=" + _id + "\n" +
                ", current_price=" + current_price.getValue () + "}";
    }
}
