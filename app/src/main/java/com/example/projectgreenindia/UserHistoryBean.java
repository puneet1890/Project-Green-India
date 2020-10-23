package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UserHistoryBean implements Serializable
{
    private String referals;
    private String plant;
    private String contributions;
    private String earning;

    public UserHistoryBean(String referals, String plant, String contributions, String earning)
    {
        this.referals = referals;
        this.plant = plant;
        this.contributions = contributions;
        this.earning = earning;
    }

    public String getReferals()
    {
        return referals;
    }

    public String getPlant()
    {
        return plant;
    }

    public String getContributions()
    {
        return contributions;
    }

    public String getEarning()
    {
        return earning;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserHistoryBean)) return false;
        UserHistoryBean that = (UserHistoryBean) o;
        return Objects.equals(getReferals(), that.getReferals()) &&
                Objects.equals(getPlant(), that.getPlant()) &&
                Objects.equals(getContributions(), that.getContributions()) &&
                Objects.equals(getEarning(), that.getEarning());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getReferals(), getPlant(), getContributions(), getEarning());
    }

    @NotNull
    @Override
    public String toString()
    {
        return "UserHistoryBean{" +
                "referals='" + referals + '\'' +
                ", plant='" + plant + '\'' +
                ", contributions='" + contributions + '\'' +
                ", earning='" + earning + '\'' +
                '}';
    }
}

