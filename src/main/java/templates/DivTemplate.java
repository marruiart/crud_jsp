package templates;

import java.util.ArrayList;

public class DivTemplate extends SimpleTagTemplate {

    public DivTemplate(String content, String... classes) {
        super("div", content, classes);
    }

    public DivTemplate(ArrayList<?> content, String... classes) {
        super("div", content, classes);
    }

    @Override
    public String toString() {
        return String.format("<%s class='%s'>%s</%s>", _tag, _classes, _content, _tag);
    }
}
