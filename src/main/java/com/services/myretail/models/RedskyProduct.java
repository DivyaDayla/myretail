package com.services.myretail.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedskyProduct {

    private String tcin;
    private Item item;

    public String getTcin () {
        return tcin;
    }

    public void setTcin (String tcin) {
        this.tcin = tcin;
    }

    public Item getItem () {
        return item;
    }

    public void setItem (Item item) {
        this.item = item;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private ProductDescription product_description;

        public ProductDescription getProduct_description () {
            return product_description;
        }

        public void setProduct_description (ProductDescription product_description) {
            this.product_description = product_description;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductDescription {

        private String title;
        private String downstream_description;

        public String getTitle () { return title; }

        public void setTitle (String title) {
            this.title = title;
        }

        public String getDownstream_description () {
            return downstream_description;
        }

        public void setDownstream_description (String downstream_description) {
            this.downstream_description = downstream_description;
        }

    }
}
