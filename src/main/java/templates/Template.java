package templates;

import java.util.ArrayList;

public abstract class Template {
    protected String _classes;

    public Template(String... classes) {
        this._classes = getClassesStr(classes);
    }

    protected void addClasses(String[] oldClasses, String... newClasses) {
        int oldLen = oldClasses.length;
        int newLen = newClasses.length;
        String[] classes = new String[oldLen + newLen];
        int i = 0;
        while (i < oldLen)
            classes[i] = oldClasses[i++];
        while (i - oldLen < newLen) {
            classes[i] = newClasses[i - oldLen];
            i++;
        }
        this._classes = getClassesStr(classes);
    }

    protected String getClassesStr(String... classes) {
        String classStr = "";
        for (int i = 0; i < classes.length; i++)
            classStr += classes[i] + " ";
        return classStr;
    }

    protected String getContentStr(ArrayList<?> content) {
        String classStr = "";
        if (content != null)
            for (Object str : content)
                classStr += str.toString();
        return classStr;
    }

    public static String firstToCapital(String str) {
        return (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase());
    }

    protected String labelled(String label, Object item, String... classes) {
        label = label == null || label.equals("") ? "" : new SimpleTagTemplate("span", label + ":").toString();
        return new SimpleTagTemplate("label", label + item, getClassesStr(classes)).toString();
    }

    public String revLabelled(String label, Object item, String... classes) {
        label = label == null || label.equals("") ? "" : new SimpleTagTemplate("span", label).toString();
        return new SimpleTagTemplate("label", item + label, getClassesStr(classes)).toString();

    }

}
