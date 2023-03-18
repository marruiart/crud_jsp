package groups;

public class Group {
    int _id;
    String _name;
    String _tutor;
    int _stuAmount;

    public Group() {
        this(0, "", "", 0);
    }

    public Group(int id, String name, String tutor, int stuAmount) {
        this._id = id;
        this._name = name;
        this._tutor = tutor;
        this._stuAmount = stuAmount;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getGroupName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getTutor() {
        return this._tutor;
    }

    public void setTutor(String tutor) {
        this._tutor = tutor;
    }

    public int getStuAmount() {
        return this._stuAmount;
    }
}
