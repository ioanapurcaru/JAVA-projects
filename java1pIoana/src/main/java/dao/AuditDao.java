package dao;

import model.Audit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditDao {

    private PreparedStatement addAudit;
    private PreparedStatement deleteAudit;

    public AuditDao(Connection connection) {
        try {
            addAudit = connection.prepareStatement("INSERT INTO audit VALUES (null, ?, ?, ?)");
            addAudit = connection.prepareStatement("DELETE FROM audit WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addAudit(Audit a) {
        try {
            addAudit.setInt(1, a.getUserId());
            addAudit.setTimestamp(2, a.getTime());
            addAudit.setString(3, a.getAction());

            return addAudit.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Audit a) {
        try {
            deleteAudit.setInt(1, a.getId());

            return deleteAudit.executeUpdate() != 0;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
