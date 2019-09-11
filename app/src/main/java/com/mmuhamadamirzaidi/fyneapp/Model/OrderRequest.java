package com.mmuhamadamirzaidi.fyneapp.Model;

import java.util.List;

public class OrderRequest {

    public String userPhone;
    public String userName;
    public String userAddress;
    public String userTotal;

    public List<Order> product;

    public OrderRequest() {
    }

    public OrderRequest(String userPhone, String userName, String userAddress, String userTotal, List<Order> product) {
        this.userPhone = userPhone;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTotal = userTotal;
        this.product = product;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(String userTotal) {
        this.userTotal = userTotal;
    }

    public List<Order> getProduct() {
        return product;
    }

    public void setProduct(List<Order> product) {
        this.product = product;
    }
}
