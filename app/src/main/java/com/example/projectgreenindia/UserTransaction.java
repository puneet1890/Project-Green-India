package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class UserTransaction implements Serializable
{
    private String amount;
    private String date;

    public UserTransaction(String amount, String date)
    {
        this.amount = amount;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserTransaction)) return false;
        UserTransaction that = (UserTransaction) o;
        return Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAmount(), getDate());
    }

    @NotNull
    @Override
    public String toString()
    {
        return "Amount: " + amount + '\n' +
                "Date: " + date ;
    }
}
