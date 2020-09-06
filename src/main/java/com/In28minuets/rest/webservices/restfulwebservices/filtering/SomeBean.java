package com.In28minuets.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value={"field1","field2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
   // @JsonIgnore
    private String field3;

    public SomeBean(String value1, String value2, String value3) {
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
