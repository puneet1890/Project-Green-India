package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.Objects;

class User implements Serializable
{
    private int id;
    private String name;
    private String email;
    private String mobile_no;
    private String refferal_code;
    private String password;
    private String displayContribute;

    public User(int id, String name, String email, String mobile_no, String refferal_code, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
        this.refferal_code = refferal_code;
        this.password = password;
    }

    User(String name, String email, String mobile_no, String refferal_code, String password) {
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
        this.refferal_code = refferal_code;
        this.password = password;
    }

    User(String name, String email, String mobile_no, String refferal_code, String password, String displayContribute) {
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
        this.refferal_code = refferal_code;
        this.password = password;
        this.displayContribute = displayContribute;
    }

    public User(String name, String email, String password, String mobile_no) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile_no = mobile_no;
    }

    public String getDisplayContribute()
    {
        return displayContribute;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getRefferal_code() {
        return refferal_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getMobile_no(), user.getMobile_no()) &&
                Objects.equals(getRefferal_code(), user.getRefferal_code()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getMobile_no(), getRefferal_code(), getPassword());
    }

/*
    @NotNull
    @Override
    public String toString()
    {
        return "User{" + " 'name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile_no='" + mobile_no + '\'' + '}';
    }
*/

    @NotNull
    @Override
    public String toString()
    {
        return "User{" +
                "  name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", refferal_code='" + refferal_code + '\'' +
                ", password='" + password + '\'' +
                ", displayContribute='" + displayContribute + '\'' +
                '}';
    }
}

