package templates;

import java.util.ArrayList;

public class FormTemplate extends SimpleTagTemplate {
    private String _action;
    private String _method;

    public FormTemplate(String action, ArrayList<?> content, String... classes) {
        this(action, "GET", content, classes);
    }

    public FormTemplate(String action, String method, ArrayList<?> content, String... classes) {
        super("form", content, classes);
        this._action = action;
        this._method = method;
    }

    @Override
    public String toString() {
        return String.format("<%s action='%s' method='%s' class='%s'>%s</%s>",
                _tag, _action, _method, _classes, _content, _tag);
    }
}
