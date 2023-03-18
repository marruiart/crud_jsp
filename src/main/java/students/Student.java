package students;

public class Student {
    int _id;
    String _name;
    String _surname;
    int _groupId;
    String _groupName;

    public Student() {
        this(0, "", "", 0, "");
    }

    public Student(int id, String name, String surname, Integer groupId, String groupName) {
        this._id = id;
        this._name = name;
        this._surname = surname;
        this._groupId = groupId;
        this._groupName = groupName;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getSurname() {
        return this._surname;
    }

    public void setSurname(String surname) {
        this._surname = surname;
    }

    public String getGroupName() {
        return this._groupName;
    }

    public int getGroupId() {
        return this._groupId;
    }

    public void setGroupId(int group_id) {
        this._groupId = group_id;
    }
}
