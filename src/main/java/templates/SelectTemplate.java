package templates;

import java.util.HashMap;
import java.util.Map;

public class SelectTemplate extends Template {
    String _name;
    String _disabled;
    String _options;
    Integer _selectedOption;

    public SelectTemplate(String name, HashMap<Integer, String> options, String... classes) {
        this(name, options, null, false, classes);
    }

    public SelectTemplate(String name, HashMap<Integer, String> options, Integer selectedOption, boolean isDisabled,
            String... classes) {
        super(classes);
        this._name = String.format("name='%s'", name);
        this._disabled = isDisabled ? " disabled" : "";
        this._options = getOptionsStr(options, selectedOption);
    }

    private static String getOptionsStr(HashMap<Integer, String> options, Integer selectedOption) {
        String optStr = "";
        if (options.size() != 0) {
            for (Map.Entry<Integer, String> set : options.entrySet()) {
                if (selectedOption != null && set.getKey() == selectedOption)
                    optStr += String.format("<option value='%s' selected>%s</option>", set.getKey(), set.getValue());
                else
                    optStr += String.format("<option value='%s'>%s</option>", set.getKey(), set.getValue());
            }
        }
        return optStr;
    }

    public String labelled() {
        return super.labelled(_name, this);
    }

    public String labelled(String label) {
        return super.labelled(label, this);
    }

    @Override
    public String toString() {
        return String.format("<select %s class='%s'%s>%s</select>", _name, _classes, _disabled, _options);
    }
}
