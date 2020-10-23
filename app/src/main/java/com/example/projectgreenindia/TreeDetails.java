package com.example.projectgreenindia;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class TreeDetails implements Serializable
{
    private String name;
    private String importance;
    private String description;
    private int image;

    TreeDetails(String name, String importance, String description, int image)
    {
        this.name = name;
        this.importance = importance;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof TreeDetails)) return false;
        TreeDetails that = (TreeDetails) o;
        return getImage() == that.getImage() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getImportance(), that.getImportance()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getName(), getImportance(), getDescription(), getImage());
    }

    @Override
    @NotNull
    public String toString()
    {
        return "TreeDetails{" +
                "name='" + name + '\'' +
                ", importance='" + importance + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }

}
