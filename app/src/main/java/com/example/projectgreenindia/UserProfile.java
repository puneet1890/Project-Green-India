package com.example.projectgreenindia;

import android.net.Uri;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UserProfile
{
    private String email;
    private String uri;

    public UserProfile(String email, String uri)
    {
        this.email = email;
        this.uri = uri;
    }

    public String getEmail() {
        return email;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile profile = (UserProfile) o;
        return Objects.equals(getEmail(), profile.getEmail()) &&
                Objects.equals(getUri(), profile.getUri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getUri());
    }

    @NotNull
    @Override
    public String toString()
    {
        return "UserProfile{" +
                "email='" + email + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

}
