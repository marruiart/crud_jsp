package templates;

import java.util.ArrayList;

public class TableTemplate extends Template {
    private String _rows;

    public TableTemplate(ArrayList<?> rows, String... classes) {
        addClasses(classes, "table");
        this._rows = "";
        for (Object str : rows) {
            this._rows += str.toString();
        }
    }

    @Override
    public String toString() {
        return new DivTemplate(_rows, _classes).toString();
    }
}
