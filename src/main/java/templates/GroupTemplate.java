package templates;

import java.util.ArrayList;
import java.util.HashMap;

import groups.Group;
import groups.GroupsService;

public class GroupTemplate extends ItemTemplate {
    Group _gr;

    public GroupTemplate(Group group) {
        super(group);
        this._gr = (Group) super._item;
    }

    private HashMap<String, Object> getGroupInfo() {
        HashMap<String, Object> groupInfo = new HashMap<>();
        groupInfo.put("id", _gr.getId());
        groupInfo.put("name", _gr.getGroupName());
        groupInfo.put("tutor", _gr.getTutor());
        groupInfo.put("stuAmount", _gr.getStuAmount());

        return groupInfo;
    }

    public static RowTemplate getTableHeader() {
        return ItemTemplate.getTableHeader("Grupo", "Tutor", "Nº alumnos", "");
    }

    public static RowTemplate getAddOneButton() {
        return ItemTemplate.getAddOneButton("group.jsp", "group");
    }

    public String getFormHeader() {
        return super.getFormHeader("Ficha del grupo");
    }

    public RowTemplate toRowTemplate() {
        // TODO Object?
        ArrayList<String> rowContent = new ArrayList<>();

        rowContent.add(_gr.getGroupName());
        rowContent.add(_gr.getTutor());
        rowContent.add(String.valueOf(_gr.getStuAmount()));
        return super.toRowTemplate("group", getGroupInfo(), rowContent);
    }

    public FormTemplate toFormTemplate(boolean isDisabled, String req, GroupsService groupsServ) {
        ArrayList<String> formContent = new ArrayList<>();

        formContent.add(new InputHiddenTemplate("id", _gr.getId()).toString());
        formContent.add(new InputTextTemplate("name", _gr.getGroupName(), "Grupo", isDisabled).labelled().toString());
        formContent.add(new InputTextTemplate("tutor", _gr.getTutor(), "Tutor", isDisabled).labelled().toString());
        formContent.add(new InputHiddenTemplate("stuAmount", _gr.getStuAmount()).toString());
        return super.toFormTemplate("group", this.getFormHeader(), formContent, "accept", req);
    }

    public FormTemplate toEmptyFormTemplate(GroupsService groupsServ) {
        ArrayList<String> formContent = new ArrayList<>();

        formContent.add(new InputHiddenTemplate("id", 0).toString());
        formContent.add(new InputTextTemplate("name", "", "Grupo", false).labelled().toString());
        formContent.add(new InputTextTemplate("tutor", "", "Tutor", false).labelled().toString());
        formContent.add(new InputHiddenTemplate("stuAmount", 0).toString());
        return super.toFormTemplate("group", this.getFormHeader(), formContent, "accept", "new");
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Grupo: %s, Tutor: %s", _gr.getId(),
                _gr.getGroupName(), _gr.getTutor());
    }
}
