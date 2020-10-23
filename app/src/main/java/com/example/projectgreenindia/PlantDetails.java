package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class PlantDetails implements Serializable
{
    private String plantName;
    private String location;
    private String dateOfPlanted;
    private String contributeStatus;
    private int image;

    public PlantDetails(String plantName, String location, String dateOfPlanted, String contributeStatus, int image)
    {
        this.plantName = plantName;
        this.location = location;
        this.dateOfPlanted = dateOfPlanted;
        this.contributeStatus = contributeStatus;
        this.image = image;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getLocation() {
        return location;
    }

    public String getDateOfPlanted() {
        return dateOfPlanted;
    }

    public String getContributeStatus() {
        return contributeStatus;
    }

    public int getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PlantDetails)) return false;
        PlantDetails that = (PlantDetails) o;
        return getImage() == that.getImage() &&
                Objects.equals(getPlantName(), that.getPlantName()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getDateOfPlanted(), that.getDateOfPlanted()) &&
                Objects.equals(getContributeStatus(), that.getContributeStatus());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPlantName(), getLocation(), getDateOfPlanted(), getContributeStatus(), getImage());
    }

    @NotNull
    @Override
    public String toString()
    {
        return "PlantDetails{" +
                "plantName='" + plantName + '\'' +
                ", location='" + location + '\'' +
                ", dateOfPlanted='" + dateOfPlanted + '\'' +
                ", contributeStatus='" + contributeStatus + '\'' +
                ", image=" + image +
                '}';
    }

}
