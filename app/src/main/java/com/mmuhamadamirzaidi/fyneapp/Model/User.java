package com.mmuhamadamirzaidi.fyneapp.Model;

public class User {
    private String UserName, UserPassword, UserIdentityCard, UserPhone, UserAddress, UserImage, IsStaff, UserSecureCode, UserHolderId;

    public User() {
    }

    public User(String userName, String userPassword, String userIdentityCard, String userPhone, String userAddress, String userImage, String isStaff, String userSecureCode, String userHolderId) {
        UserName = userName;
        UserPassword = userPassword;
        UserIdentityCard = userIdentityCard;
        UserPhone = userPhone;
        UserAddress = userAddress;
        UserImage = userImage;
        IsStaff = isStaff;
        UserSecureCode = userSecureCode;
        UserHolderId = userHolderId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserIdentityCard() {
        return UserIdentityCard;
    }

    public void setUserIdentityCard(String userIdentityCard) {
        UserIdentityCard = userIdentityCard;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getUserSecureCode() {
        return UserSecureCode;
    }

    public void setUserSecureCode(String userSecureCode) {
        UserSecureCode = userSecureCode;
    }

    public String getUserHolderId() {
        return UserHolderId;
    }

    public void setUserHolderId(String userHolderId) {
        UserHolderId = userHolderId;
    }
}
