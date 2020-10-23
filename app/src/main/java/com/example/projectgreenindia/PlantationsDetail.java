package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class PlantationsDetail implements Serializable
{
    private String plantName;
    private String noofPlant;
    private String location;
    private String date;
    private String amount;
    private String imagepath;

    public PlantationsDetail(String plantName, String noofPlant, String location, String date, String amount, String imagepath)
    {
        this.plantName = plantName;
        this.noofPlant = noofPlant;
        this.location = location;
        this.date = date;
        this.amount = amount;
        this.imagepath = imagepath;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getNoofPlant() {
        return noofPlant;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getImagepath() {
        return imagepath;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PlantationsDetail)) return false;
        PlantationsDetail that = (PlantationsDetail) o;
        return Objects.equals(getPlantName(), that.getPlantName()) &&
                Objects.equals(getNoofPlant(), that.getNoofPlant()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getImagepath(), that.getImagepath());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPlantName(), getNoofPlant(), getLocation(), getDate(), getAmount(), getImagepath());
    }

    @NotNull
    @Override
    public String toString()
    {
        return  "plantName: " + plantName +'\n' +
                "noofPlant: " + noofPlant +'\n' +
                "location: " + location + '\n' +
                "date: " + date + '\n' +
                "amount: " + amount + '\n';
    }
}
