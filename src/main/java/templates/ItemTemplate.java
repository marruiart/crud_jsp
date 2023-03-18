package templates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import groups.Group;
import groups.GroupsService;
import students.Student;

public abstract class ItemTemplate {
    Object _item;

    protected ItemTemplate(Object item) {
        this._item = item;
    }

    public static RowTemplate getAddOneButton(String action, String... classes) {
        ArrayList<String> formContent = new ArrayList<>();
        ArrayList<String> rowContent = new ArrayList<>();

        formContent.add(new InputHiddenTemplate("btn", "add").toString());
        formContent.add(
                new ButtonTemplate("submit", "<i class='fa fa-plus-square' aria-hidden='true'></i>", "icon btn-add")
                        .toString());
        rowContent.add(new FormTemplate(action, "GET", formContent).toString());
        return new RowTemplate(rowContent, classes);
    }

    public static String prepareGroupsSelect(String name, String info, boolean isDisabled, GroupsService gServ,
            String... classes) {
        return prepareGroupsSelect(name, null, info, isDisabled, gServ, classes);
    }

    public static String prepareGroupsSelect(String name, Student stu, String info, boolean isDisabled,
            GroupsService gServ, String... classes) {
        ArrayList<Group> groups = null;
        HashMap<Integer, String> groupsStr = new HashMap<>();
        groupsStr.put(0, "");
        try {
            groups = gServ.requestAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Group g : groups)
            groupsStr.put(g.getId(), g.getGroupName());

        return new SelectTemplate(name, groupsStr, stu == null ? null : stu.getGroupId(), isDisabled, classes)
                .labelled("Grupo").toString();
    }

    private String getTableButton(String itemName, HashMap<String, Object> info, String icon, String type) {
        ArrayList<String> formContent = new ArrayList<>();
        String action = itemName + ".jsp";

        formContent.add(new InputHiddenTemplate("btn", type).toString());
        for (Map.Entry<String, ?> set : info.entrySet())
            formContent.add(new InputHiddenTemplate(set.getKey(), set.getValue()).toString());
        formContent.add(new ButtonTemplate("submit", icon, "icon", "btn-" + type).toString());
        return new FormTemplate(action, "GET", formContent).toString();
    }

    protected static RowTemplate getTableHeader(String... _headers) {
        ArrayList<String> headers = new ArrayList<>();
        for (String h : _headers)
            headers.add(h);
        return (new RowTemplate(headers, "th"));
    }

    protected String getFormHeader(String itemHeader) {
        return new DivTemplate(itemHeader, "form-th").toString();
    }

    protected static String getFormButtons(String action, String btn, String req) {
        String btnBack = "";
        String btnAccept = "";
        String div = "";
        if (req.equals("del")) {
            div = new DivTemplate(
                    String.format("¿Desea eliminar %s?", action.contains("group") ? "el grupo" : "al alumno"))
                    .toString();
            btnAccept = new ButtonTemplate("submit", "Sí", "btn", "btn-" + btn).toString();
            btnBack = new AnchorTemplate(action.replace(".jsp", "s.jsp"), "No", "btn", "btn-back").toString();
            div += new DivTemplate(btnAccept + btnBack).toString();
        } else if (req.equals("upd") || req.equals("new")) {
            div = new DivTemplate("¿Desea aplicar los cambios?").toString();
            btnAccept = new ButtonTemplate("submit", "Aceptar", "btn", "btn-" + btn).toString();
            btnBack = new AnchorTemplate(action.replace(".jsp", "s.jsp"), "Cancelar", "btn", "btn-back").toString();
            div += new DivTemplate(btnAccept + btnBack).toString();
        } else {
            div = new AnchorTemplate(action.replace(".jsp", "s.jsp"), "Atrás", "btn", "btn-back").toString();
        }
        return new DivTemplate(div, "form-buttons").toString();
    }

    protected RowTemplate toRowTemplate(String itemName, HashMap<String, Object> info, ArrayList<String> rowContent) {
        // TODO SimpleTagTemplate
        String reqOne = getTableButton(itemName, info, "<i class='fa fa-id-card' aria-hidden='true'></i>", "request");
        String edit = getTableButton(itemName, info, "<i class='fa fa-pencil' aria-hidden='true'></i>", "edit");
        String cancel = getTableButton(itemName, info, "<i class='fa fa-trash' aria-hidden='true'></i>", "delete");

        rowContent.add(reqOne + edit + cancel);
        return (new RowTemplate(rowContent, itemName + info));
    }

    protected FormTemplate toFormTemplate(String itemStr, String formHeader, ArrayList<String> _formContent, String btn,
            String req) {
        ArrayList<String> formContent = new ArrayList<>();
        String action = itemStr + ".jsp";

        formContent.add(formHeader);
        formContent.add(new InputHiddenTemplate("btn", btn).toString());
        formContent.add(new InputHiddenTemplate("req", req).toString());
        formContent.add(new InputHiddenTemplate("serv", itemStr).toString());
        for (String str : _formContent) {
            formContent.add(str);
        }
        formContent.add(getFormButtons(action, btn, req));
        return (new FormTemplate("service.jsp", formContent, "file"));
    }

}
