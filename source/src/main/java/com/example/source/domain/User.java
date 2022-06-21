package com.example.source.domain;

import java.util.Objects;

public class User {

    private int id;

    private boolean isProcessed;

    private boolean isSaved;

    private static int USER_ID  = 1;

    public User(int id, boolean isProcessed, boolean isSaved) {
        this.id = id;
        this.isProcessed = isProcessed;
        this.isSaved = isSaved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isProcessed == user.isProcessed && isSaved == user.isSaved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isProcessed, isSaved);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isProcessed=" + isProcessed +
                ", isSaved=" + isSaved +
                '}';
    }

    public static User getRandomUser() {

        int id = USER_ID ++;
        return new User(id, false, false);
    }
}
