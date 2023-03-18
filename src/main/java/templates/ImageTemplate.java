package templates;

public class ImageTemplate extends Template {
    public String _src;
    public String _alt;
    public String _inlineStyle;

    public ImageTemplate(String src, String alt, String... classes) {
        this(src, alt, null, false, classes);
    }

    public ImageTemplate(String src, String alt, String inlineStyle, boolean inline, String... classes) {
        super(classes);
        this._src = String.format("src='%s'", src);
        this._alt = (alt == null || alt.equals("")) ? "" : String.format("alt='%s'", alt);
        this._inlineStyle = inline ? String.format("style='%s'", inlineStyle) : "";
    }

    @Override
    public String toString() {
        return String.format("<img %s %s %s class='%s'>", _classes, _src, _alt, _inlineStyle);
    }
}
