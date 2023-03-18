package templates;

public class InputCheckboxTemplate extends InputTemplate {

    public InputCheckboxTemplate(String name, String value, String... classes) {
        super("checkbox", name, value, classes);
    }

    public InputCheckboxTemplate(String name, int value, String... classes) {
        super("checkbox", name, value, classes);
    }

    public String labelled() {
        return super.labelled(_placeholder, this);
    }

    public String labelled(String label, String... classes) {
        return super.labelled(label, this, classes);
    }

    public String revLabelled(String label, String... classes) {
        return super.revLabelled(label, this, classes);
    }
}
