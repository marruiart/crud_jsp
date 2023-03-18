package templates;

import java.util.ArrayList;

public class AnchorTemplate extends SimpleTagTemplate {
    private String _href;

    public AnchorTemplate(String href, ArrayList<?> content, String... classes) {
        super("a", content, classes);
        this._href = href == null ? "" : String.format("href='%s'", href);
    }

    public AnchorTemplate(String href, String content, String... classes) {
        super("a", content, classes);
        this._href = href == null ? "" : String.format("href='%s'", href);
    }

    @Override
    public String toString() {
        return String.format("<%s %s class='%s'>%s</%s>", _tag, _href, _classes, _content, _tag);
    }
}
