package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class Plant implements Serializable
{
    private String plantName;
    private String description;
    private String facts;
    private String importance;

    public Plant(String plantName, String description, String facts, String importance)
    {
        this.plantName = plantName;
        this.description = description;
        this.facts = facts;
        this.importance = importance;
    }

    public Plant(String plantName, String description)
    {
        this.plantName = plantName;
        this.description = description;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getDescription() {
        return description;
    }

    public String getFacts() {
        return facts;
    }

    public String getImportance() {
        return importance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant = (Plant) o;
        return Objects.equals(getPlantName(), plant.getPlantName()) &&
                Objects.equals(getDescription(), plant.getDescription()) &&
                Objects.equals(getFacts(), plant.getFacts()) &&
                Objects.equals(getImportance(), plant.getImportance());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPlantName(), getDescription(), getFacts(), getImportance());
    }

    @NotNull
    @Override
    public String toString()
    {
        return "Plant{" +
                "plantName='" + plantName + '\'' +
                ", description='" + description + '\'' +
                ", facts='" + facts + '\'' +
                ", importance='" + importance + '\'' +
                '}';
    }
}
