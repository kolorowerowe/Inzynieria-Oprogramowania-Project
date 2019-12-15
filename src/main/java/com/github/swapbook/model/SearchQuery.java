package com.github.swapbook.model;

public class SearchQuery {
    private String attribute;
    private String value;


    //Gettery i Settery

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {return value;}

    public void setValue(String value) {
        this.value = value;
    }
}
