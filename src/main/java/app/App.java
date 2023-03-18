package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionPool;
import connection.Environment;
import groups.GroupsService;
import students.Student;
import students.StudentsService;
import templates.ItemTemplate;
import templates.RowTemplate;
import templates.StudentTemplate;
import templates.TableTemplate;

public abstract class App {
    protected Object _serv = null;
    protected ConnectionPool _pool = null;
    protected Connection _conn = null;
    protected StudentsService _studentServ = null;
    protected GroupsService _groupsServ = null;

    public App() {
        try {
            _pool = new ConnectionPool(Environment.URL, Environment.USER, Environment.PASS);
            _conn = _pool.getConnection();
            _studentServ = new StudentsService(_conn);
            _groupsServ = new GroupsService(_conn);
        } catch (SQLException e) {
            e.printStackTrace();
            if (_pool != null) {
                try {
                    _pool.closeAll();
                } catch (SQLException c) {
                    c.printStackTrace();
                }
            }
        }
    }

    public String printStudentsInGroup(int groupId) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            students = _studentServ.requestAllStudentsInGroup(groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<RowTemplate> rows = new ArrayList<>();
        rows.add(StudentTemplate.getTableHeader());
        for (Student s : students) {
            RowTemplate row = (new StudentTemplate(s)).toRowTemplate();
            rows.add(row);
        }
        return printTable(rows, "students.jsp");
    }

    public String printSimpleStudentsInGroup(int groupId) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            students = this._studentServ.requestAllStudentsInGroup(groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<RowTemplate> rows = new ArrayList<>();
        rows.add(StudentTemplate.getSimpleTableHeader("Alumnos del grupo"));
        for (Student s : students) {
            RowTemplate row = new StudentTemplate(s).toSimpleRowTemplate();
            rows.add(row);
        }
        return printSimpleTable(rows);
    }

    public String printTable(ArrayList<?> tableContent, String action, String... classes) {
        ArrayList<Object> rows = (ArrayList<Object>) tableContent.clone();
        rows.add(ItemTemplate.getAddOneButton(action, "tr-add"));
        return new TableTemplate(rows, classes).toString();
    }

    public String printSimpleTable(ArrayList<?> tableContent, String... classes) {
        ArrayList<String> rows = new ArrayList<>();
        for (Object row : tableContent)
            rows.add(row.toString());
        TableTemplate table = new TableTemplate(rows, classes);
        return table.toString();
    }

    public void closeAll() {
        if (_pool != null) {
            try {
                _pool.closeAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
