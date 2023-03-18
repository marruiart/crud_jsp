package students;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentsService {
    private Connection _conn;

    public StudentsService(Connection conn) {
        this._conn = conn;
    }

    public ArrayList<Student> requestAll() throws SQLException {
        return requestAll("");
    }

    public ArrayList<Student> requestAll(String filter) throws SQLException {
        Statement statement = null;
        ArrayList<Student> result = new ArrayList<Student>();
        statement = this._conn.createStatement();
        filter = filter.equals("") ? ""
                : " WHERE LOWER(s.name) LIKE '%" + filter + "%' OR LOWER(s.surname) LIKE '%"
                        + filter + "%'  OR LOWER(g.name) LIKE '%" + filter + "%'";
        String sql = "SELECT s.id, s.name, s.surname, s.group_id, g.name AS 'group_name' FROM students s"
                + " LEFT JOIN groups g ON (s.group_id=g.id)"
                + filter;
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while (querySet.next()) {
            int id = querySet.getInt("id");
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            int groupId = querySet.getInt("group_id");
            String groupName = querySet.getString("group_name");
            result.add(new Student(id, name, surname, groupId, groupName));
        }
        statement.close();
        return result;
    }

    public ArrayList<Student> requestAllStudentsInGroup(int groupId) throws SQLException {
        return requestAllStudentsInGroup("", groupId);
    }

    public ArrayList<Student> requestAllStudentsInGroup(String filter, int groupId) throws SQLException {
        Statement statement = null;
        ArrayList<Student> result = new ArrayList<Student>();
        statement = this._conn.createStatement();
        filter = filter.equals("") ? ""
                : " AND (LOWER(s.name) LIKE '%" + filter + "%'" +
                        " OR LOWER(s.surname) LIKE '%" + filter + "%')";
        String sql = "SELECT s.id, s.name, s.surname, s.group_id, g.name AS 'group_name' FROM students s" +
                " LEFT JOIN groups g ON (s.group_id=g.id)" +
                " WHERE group_id " + (groupId == 0 ? "IS NULL" : "= " + groupId) + filter;
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while (querySet.next()) {
            int id = querySet.getInt("id");
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            String groupName = querySet.getString("group_name");
            result.add(new Student(id, name, surname, groupId, groupName));
        }
        statement.close();
        return result;
    }

    public Student requestById(int id) throws SQLException {
        Statement statement = null;
        Student result = null;
        statement = this._conn.createStatement();
        String sql = String.format(
                "SELECT s.name, s.surname, s.group_id, g.id, g.name AS 'group_name' FROM students s"
                        + " LEFT JOIN groups g ON (s.group_id=g.id) WHERE s.id=%d",
                id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if (querySet.next()) {
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            int groupId = querySet.getInt("group_id");
            String groupName = querySet.getString("group_name");
            result = new Student(id, name, surname, groupId, groupName);
        }
        statement.close();
        return result;
    }

    public int create(String name, String surname) throws SQLException {
        return create(name, surname, 0);
    }

    public int create(String name, String surname, int group_id) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("INSERT INTO students (name, surname, group_id) VALUES ('%s', '%s', %d)",
                name, surname, group_id == 0 ? null : group_id);
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

    public int update(int id, int group_id) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("UPDATE students SET group_id = %d WHERE id=%d", group_id == 0 ? null : group_id,
                id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public int update(int id, String name, String surname, int group_id) throws SQLException {
        Statement statement = null;
        statement = this._conn.createStatement();
        String sql = String.format("UPDATE students SET name = '%s', surname = '%s', group_id = %d WHERE id=%d",
                name, surname, group_id == 0 ? null : group_id, id);
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
        String sql = String.format("DELETE FROM students WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result == 1;
    }
}
