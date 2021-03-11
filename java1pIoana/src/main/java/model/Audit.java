package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Audit {

    int id;
    int userId;
    Timestamp time;
    String action;

    public Audit(int id, int userId, Timestamp time, String action) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", userId=" + userId +
                ", time=" + time +
                ", action='" + action + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return id == audit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
