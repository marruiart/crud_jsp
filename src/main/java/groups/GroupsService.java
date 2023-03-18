package groups;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupsService {
    private Connection _conn;

    public GroupsService(Connection conn) {
        this._conn = conn;
    }

    public ArrayList<Group> requestAll() throws SQLException {
        return requestAll("");
    }

    public ArrayList<Group> requestAll(String filter) throws SQLException {
        Statement statement = null;
        ArrayList<Group> result = new ArrayList<Group>();
        statement = this._conn.createStatement();
        filter = filter.equals("") ? ""
                : " HAVING LOWER(g.tutor) LIKE '%" + filter + "%'" +
                        " OR LOWER(g.name) LIKE '%" + filter + "%'";
        String sql = "SELECT g.id, g.name, g.tutor, s.name, s.surname, IF(s.name IS NULL, 0, COUNT(g.id)) AS 'stu_amount' FROM groups g"
                + " LEFT JOIN students s ON (s.group_id=g.id)" +
                " GROUP BY g.id" + filter;
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while (querySet.next()) {
            int id = querySet.getInt("id");
            String name = querySet.getString("name");
            String tutor = querySet.getString("tutor");
            int stuAmount = querySet.getInt("stu_amount");
            result.add(new Group(id, name, tutor, stuAmount));
        }
        statement.close();
        return result;
    }

    public Group requestById(int id) throws SQLException {
        Statement statement = null;
        Group result = null;
        statement = this._conn.createStatement();
        String sql = "SELECT g.id, g.name, g.tutor, IF(s.name IS NULL, 0, COUNT(g.id)) AS 'stu_amount' FROM groups g"
                + " LEFT JOIN students s ON (s.group_id=g.id) GROUP BY g.id HAVING g.id=" + id;
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if (querySet.next()) {
            String name = querySet.getString("name");
            String tutor = querySet.getString("tutor");
            int stuAmount = querySet.getInt("stu_amount");
            result = new Group(id, name, tutor, stuAmount);
        }
        statement.close();
        return result;
    }

    public int create(String name, String tutor) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("INSERT INTO groups (name, tutor) VALUES ('%s', '%s')", name, tutor);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                statement.close();
                return id;
            } else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public int update(int id, String name, String tutor) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("UPDATE groups SET name = '%s', tutor = '%s' WHERE id=%d", name, tutor, id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean delete(int id) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("DELETE FROM groups WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result == 1;
    }
}
