package templates;

import java.util.ArrayList;

public class RowTemplate extends Template {
    private String _content;

    public RowTemplate(ArrayList<?> content, String... classes) {
        addClasses(classes, "tr");
        this._content = this.getRowContent(content);
    }

    private String getRowContent(ArrayList<?> _content) {
        String rowContent = "";
        for (Object str : _content)
            if (str != null)
                rowContent += new DivTemplate(str.toString(), "td").toString();
        return rowContent;
    }

    @Override
    public String toString() {
        return new DivTemplate(this._content, this._classes).toString();
    }
}
