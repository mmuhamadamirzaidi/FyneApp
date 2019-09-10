package com.mmuhamadamirzaidi.fyneapp.Model;

import java.util.List;

public class OrderRequest {

    public String Phone;
    public String Name;
    public String Address;
    public String Total;

    public List<Order> product;

    public OrderRequest() {
    }

    public OrderRequest(String phone, String name, String address, String total, List<Order> product) {
        Phone = phone;
        Name = name;
        Address = address;
        Total = total;
        this.product = product;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<Order> getProduct() {
        return product;
    }

    public void setProduct(List<Order> product) {
        this.product = product;
    }
}
