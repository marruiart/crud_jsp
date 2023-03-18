package templates;

public abstract class InputTemplate extends Template {
    protected String _type;
    protected String _name;
    protected String _value;
    protected String _placeholder;
    protected String _disabled;

    protected InputTemplate(String type, String name, int value, String... classes) {
        this(type, name, String.valueOf(value), null, false, classes);
    }

    protected InputTemplate(String type, String name, String value, String... classes) {
        this(type, name, value, null, false, classes);
    }

    protected InputTemplate(String type, String name, int value, boolean isDisabled, String... classes) {
        this(type, name, String.valueOf(value), null, isDisabled, classes);
    }

    protected InputTemplate(String type, String name, String value, boolean isDisabled, String... classes) {
        this(type, name, value, null, isDisabled, classes);
    }

    protected InputTemplate(String type, String name, int value, String placeholder, boolean isDisabled,
            String... classes) {
        this(type, name, String.valueOf(value), placeholder, isDisabled, classes);
    }

    protected InputTemplate(String type, String name, String value, String placeholder, boolean isDisabled,
            String... classes) {
        super(classes);
        this._type = type;
        this._name = name;
        this._value = value;
        this._placeholder = placeholder == null ? "" : placeholder;
        this._disabled = isDisabled ? " disabled" : "";
    }

    protected String labelled(Object item) {
        return super.labelled(_placeholder, item);
    }

    protected String labelled(String label, Object item) {
        return super.labelled(label, item);
    }

    public String revLabelled(String label, Object item, String... classes) {
        return super.revLabelled(label, item, classes);
    }

    @Override
    public String toString() {
        return String.format("<input type='%s' name='%s' value='%s'%s class='%s'%s>", _type, _name, _value,
                _placeholder.equals("") ? "" : String.format(" placeholder='%s' ", _placeholder), _classes, _disabled);
    }
}
