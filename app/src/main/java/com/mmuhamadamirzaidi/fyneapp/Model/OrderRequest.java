package com.mmuhamadamirzaidi.fyneapp.Model;

import java.util.List;

public class OrderRequest {

    public String userPhone;
    public String userName;
    public String userAddress;
    public String grandTotal;

    public String status;

    public List<Order> product;

    public OrderRequest() {
    }

    public OrderRequest(String userPhone, String userName, String userAddress, String grandTotal, List<Order> product) {
        this.userPhone = userPhone;
        this.userName = userName;
        this.userAddress = userAddress;
        this.grandTotal = grandTotal;
        this.status = "0"; //Default 0:Processing, 1:On the way, 2:Delivered
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

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getProduct() {
        return product;
    }

    public void setProduct(List<Order> product) {
        this.product = product;
    }
}
