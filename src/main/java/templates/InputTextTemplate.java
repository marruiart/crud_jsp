package templates;

public class InputTextTemplate extends InputTemplate {

    public InputTextTemplate(String name, String value, String... classes) {
        super("text", name, value, classes);
    }

    public InputTextTemplate(String name, int value, String... classes) {
        super("text", name, value, classes);
    }

    public InputTextTemplate(String name, int value, boolean isDisabled, String... classes) {
        super("text", name, value, null, isDisabled, classes);
    }

    public InputTextTemplate(String name, String value, boolean isDisabled, String... classes) {
        super("text", name, value, null, isDisabled, classes);
    }

    public InputTextTemplate(String name, int value, String placeholder, boolean isDisabled,
            String... classes) {
        super("text", name, value, placeholder, isDisabled, classes);
    }

    public InputTextTemplate(String name, String value, String placeholder, boolean isDisabled,
            String... classes) {
        super("text", name, value, placeholder, isDisabled, classes);
    }

    public String labelled() {
        return super.labelled(_placeholder, this);
    }

    public String labelled(String label, String... classes) {
        return super.labelled(label, this, classes);
    }

    @Override
    public String toString() {
        return String.format("<input autocomplete='off' type='%s' name='%s' value='%s'%s class='%s'%s>", _type, _name, _value,
                _placeholder.equals("") ? "" : String.format(" placeholder='%s' ", _placeholder), _classes, _disabled);
    }
}
