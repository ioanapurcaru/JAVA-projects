package model;

import java.util.Objects;

public class User {

    private final int id;
    private final String username;
    private final String email;
    private final String password;

    public User(int id, String username, String email, String pass) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = pass;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }


    public String getPassw() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passw='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
