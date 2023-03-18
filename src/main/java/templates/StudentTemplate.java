package templates;

import java.util.ArrayList;
import java.util.HashMap;

import groups.GroupsService;
import students.Student;

public class StudentTemplate extends ItemTemplate {
    Student _stu;

    public StudentTemplate(Student student) {
        super(student);
        this._stu = (Student) super._item;
    }

    private HashMap<String, Object> getStudentInfo() {
        HashMap<String, Object> studentInfo = new HashMap<>();
        studentInfo.put("id", _stu.getId());
        studentInfo.put("name", _stu.getName());
        studentInfo.put("surname", _stu.getSurname());
        studentInfo.put("group_id", _stu.getGroupId());
        studentInfo.put("group_name", _stu.getGroupName());

        return studentInfo;
    }

    public static RowTemplate getTableHeader() {
        return ItemTemplate.getTableHeader("Nombre", "Apellidos", "Grupo", "");
    }

    public static RowTemplate getSimpleTableHeader(String header) {
        return ItemTemplate.getTableHeader(header);
    }

    public static RowTemplate getAddOneButton() {
        return ItemTemplate.getAddOneButton("student.jsp", "tr-add");
    }

    public String getFormHeader() {
        return super.getFormHeader("Ficha del estudiante");
    }

    public RowTemplate toRowTemplate() {
        ArrayList<String> rowContent = new ArrayList<>();

        rowContent.add(_stu.getName());
        rowContent.add(_stu.getSurname());
        if (_stu.getGroupName() == null)
            rowContent.add("---");
        else
            rowContent.add(_stu.getGroupName());
        return super.toRowTemplate("student", getStudentInfo(), rowContent);
    }

    public RowTemplate toSimpleRowTemplate() {
        ArrayList<String> rowContent = new ArrayList<>();

        rowContent.add(_stu.getName() + " " + _stu.getSurname());
        return new RowTemplate(rowContent, "student" + _stu.getId());
    }

    public FormTemplate toFormTemplate(boolean isDisabled, String req, GroupsService groupsServ) {
        ArrayList<String> formContent = new ArrayList<>();

        formContent.add(new InputHiddenTemplate("id", _stu.getId()).toString());
        formContent.add(new InputTextTemplate("name", _stu.getName(), "Nombre", isDisabled).labelled().toString());
        formContent.add(
                new InputTextTemplate("surname", _stu.getSurname(), "Apellidos", isDisabled).labelled().toString());
        formContent.add(new InputHiddenTemplate("group_name", _stu.getGroupName()).toString());
        formContent.add(prepareGroupsSelect("group_id", _stu, "Grupo", isDisabled, groupsServ));
        return super.toFormTemplate("student", this.getFormHeader(), formContent, "accept", req);
    }

    public FormTemplate toEmptyFormTemplate(GroupsService groupsServ) {
        ArrayList<String> formContent = new ArrayList<>();

        formContent.add(new InputHiddenTemplate("id", 0).toString());
        formContent.add(new InputTextTemplate("name", "", "Nombre", false).labelled().toString());
        formContent.add(new InputTextTemplate("surname", "", "Apellidos", false).labelled().toString());
        formContent.add(new InputHiddenTemplate("group_name", "").toString());
        formContent.add(prepareGroupsSelect("group_id", _stu, "Grupo", false, groupsServ));
        return super.toFormTemplate("student", this.getFormHeader(), formContent, "accept", "new");
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Apellidos: %s", _stu.getId(),
                _stu.getName(),
                _stu.getSurname());
    }
}
