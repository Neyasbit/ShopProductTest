package com.example.shopprotucttest.model;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class ShopData implements Serializable {

    @Json(name = "data")
    private List<Item> data = null;

    public List<Item> getData() {
        return data;
    }

    public static class Item implements Serializable{

        @Json(name = "id")
        public Integer id;
        @Json(name = "name")
        private String name;
        @Json(name = "price")
        private Double price;
        @Json(name = "bar_code")
        private String barCode;
        @Json(name = "book_category")
        private BookCategory bookCategory;
        @Json(name = "type")
        private String type;
        @Json(name = "properties")
        private List<Property> properties = null;

        public List<Property> getProperties() {
            return properties;
        }

        public String getName() {
            return name;
        }

        public Double getPrice() {
            return price;
        }

        public String getBarCode() {
            return barCode;
        }

        public String getType() {
            return type;
        }

        public BookCategory getBookCategory() {
            return bookCategory;
        }

        public static class Property implements Serializable{

            @Json(name = "id")
            private Integer id;
            @Json(name = "name")
            private String name;
            @Json(name = "value")
            private String value;

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }
        }

        public static class BookCategory implements Serializable{

            @Json(name = "id")
            private Integer id;
            @Json(name = "name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }
}
