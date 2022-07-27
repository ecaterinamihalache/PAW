package ro.tuiasi.GatewayService.Models;

import java.io.Serializable;

public class Address implements Serializable {
    private Integer id;
    private String city;
    private String county;
    private String street_name;
    private String country;
    private String postal_code;

    public Address() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getStreet_name() {
        return street_name;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_code() {
        return postal_code;
    }
}
