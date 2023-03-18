package templates;

import java.util.ArrayList;

public class SimpleTagTemplate extends Template {
    public String _tag;
    public String _content;

    public SimpleTagTemplate(String tag, ArrayList<?> content, String... classes) {
        super(classes);
        this._tag = tag;
        this._content = getContentStr(content);
    }

    public SimpleTagTemplate(String tag, String content, String... classes) {
        super(classes);
        this._tag = tag;
        this._content = content == null ? "" : content;
    }

    @Override
    public String toString() {
        return String.format("<%s class='%s'> %s </%s>", _tag, _classes, _content, _tag);
    }
}
