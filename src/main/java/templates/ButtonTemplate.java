package templates;

import java.util.ArrayList;

public class ButtonTemplate extends SimpleTagTemplate {
    private String _type;

    public ButtonTemplate(String type, ArrayList<?> content, String... classes) {
        super("button", content, classes);
        this._type = type == null ? "type='button'" : String.format("type='%s'", type);
    }

    public ButtonTemplate(String type, String content, String... classes) {
        super("button", content, classes);
        this._type = type == null ? "type='button'" : String.format("type='%s'", type);
    }

    @Override
    public String toString() {
        return String.format("<%s %s class='%s'>%s</%s>", _tag, _type, _classes, _content, _tag);
    }
}
