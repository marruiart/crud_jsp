package app;

import java.sql.SQLException;
import java.util.ArrayList;

import groups.Group;
import groups.GroupsService;
import templates.RowTemplate;
import templates.DivTemplate;
import templates.GroupTemplate;

public class AppGroup extends App {
    private GroupsService _serv;

    public AppGroup() {
        super();
        this._serv = (GroupsService) super._groupsServ;
    }

    private ArrayList<Group> getAllGroups(String filter) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            groups = this._serv.requestAll(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public GroupsService get_serv() {
        return _serv;
    }

    public String printGroupsTable() {
        return printGroupsTable("");
    }

    public String printGroupsTable(String filter) {
        ArrayList<Group> groups = getAllGroups(filter);
        ArrayList<RowTemplate> rows = new ArrayList<>();

        rows.add(GroupTemplate.getTableHeader());
        for (Group s : groups) {
            RowTemplate row = (new GroupTemplate(s)).toRowTemplate();
            rows.add(row);
        }
        return super.printTable(rows, "group.jsp");
    }

    public String printGroupFile(Group group, boolean isDisabled, String req) {
        return new GroupTemplate(group).toFormTemplate(isDisabled, req, _groupsServ).toString();
    }

    public String printGroupFile() {
        Group group = new Group();
        return new GroupTemplate(group).toEmptyFormTemplate(_groupsServ).toString();
    }

    public int createGroup(String name, String tutor) {
        int id = 0;
        try {
            id = this._serv.create(name, tutor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String updateGroup(int id, String name, String tutor) {
        int affectedRows = 0;
        try {
            affectedRows = this._serv.update(id, name, tutor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DivTemplate("Filas afectadas: " + affectedRows, affectedRows == 0 ? "warn" : "okay").toString();
    }

    public String deleteGroup(int id) {
        boolean deleted = false;
        GroupTemplate g = null;
        try {
            g = new GroupTemplate(this._serv.requestById(id));
            deleted = this._serv.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DivTemplate(deleted ? g.toString() + " ha sido eliminado" : "Error al eliminar: " + g.toString(), deleted ? "okay" : "warn").toString();
    }
}
