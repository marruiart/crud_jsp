package templates;

public class InputHiddenTemplate extends InputTemplate {

    public InputHiddenTemplate(String name, Object value, String... classes) {
        super("hidden", name, String.valueOf(value), classes);
    }

}
