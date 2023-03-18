package app;

import java.sql.SQLException;
import java.util.ArrayList;

import students.Student;
import students.StudentsService;
import templates.DivTemplate;
import templates.InputCheckboxTemplate;
import templates.RowTemplate;
import templates.StudentTemplate;

public class AppStudent extends App {
    StudentsService _serv;

    public AppStudent() {
        super();
        this._serv = (StudentsService) super._studentServ;
    }

    private ArrayList<Student> getAllStudents(String filter) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            students = this._serv.requestAll(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private ArrayList<Student> getAllStudentsInAGroup(String filter, int groupId) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            students = this._serv.requestAllStudentsInGroup(filter, groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public String printStudentsTable(String filter) {
        ArrayList<Student> students = getAllStudents(filter);
        ArrayList<RowTemplate> rows = new ArrayList<>();

        rows.add(StudentTemplate.getTableHeader());
        for (Student s : students) {
            RowTemplate row = new StudentTemplate(s).toRowTemplate();
            rows.add(row);
        }
        return super.printTable(rows, "student.jsp");
    }

    public String printCheckboxStudentsList(String filter, int group_id, String tableHeader) {
        ArrayList<Student> students = getAllStudentsInAGroup(filter, group_id);
        ArrayList<String> rows = new ArrayList<>();

        rows.add(StudentTemplate.getSimpleTableHeader(tableHeader).toString());
        if (!(tableHeader.contains("grupo") && group_id == 0))
            for (Student s : students) {
                String row = new InputCheckboxTemplate("id", s.getId(), "td")
                        .revLabelled(s.getName() + " " + s.getSurname(), "tr");
                rows.add(row);
            }
        return super.printSimpleTable(rows, "table-enroll");
    }

    public String printStudentFile(Student student, boolean isDisabled, String req) {
        return new StudentTemplate(student).toFormTemplate(isDisabled, req, _groupsServ).toString();
    }

    public String printStudentFile() {
        Student s = new Student();
        return new StudentTemplate(s).toEmptyFormTemplate(_groupsServ).toString();
    }

    public int createStudent(String name, String surname, int group_id) {
        int id = 0;
        try {
            if (group_id == 0)
                id = this._serv.create(name, surname);
            else
                id = this._serv.create(name, surname, group_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String updateStudent(int id, String name, String surname, int group_id) {
        int affectedRows = 0;
        try {
            affectedRows = this._serv.update(id, name, surname, group_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DivTemplate("Filas afectadas: " + affectedRows, affectedRows == 0 ? "warn" : "okay").toString();
    }

    public String updateStudent(int id, int group_id) {
        int affectedRows = 0;
        try {
            affectedRows = this._serv.update(id, group_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DivTemplate("Filas afectadas: " + affectedRows, affectedRows == 0 ? "warn" : "okay").toString();
    }

    public String deleteStudent(int id) {
        boolean deleted = false;
        StudentTemplate s = null;
        try {
            s = new StudentTemplate(this._serv.requestById(id));
            deleted = this._serv.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DivTemplate(deleted ? s.toString() + " ha sido eliminado" : "Error al eliminar: " + s.toString(), deleted ? "okay" : "warn").toString();
    }
}
