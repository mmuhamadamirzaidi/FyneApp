package com.mmuhamadamirzaidi.fyneapp.Model;

public class User {
    private String Name, Password, IdentityCard;

    public User() {
    }

    public User(String name, String password, String identityCard) {
        Name = name;
        Password = password;
        IdentityCard = identityCard;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String identityCard) {
        IdentityCard = identityCard;
    }
}
