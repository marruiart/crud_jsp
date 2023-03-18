package utilities;

import java.util.ArrayList;

import templates.AnchorTemplate;
import templates.DivTemplate;
import templates.SimpleTagTemplate;

public class Utilities {

    public static String printHead() {
        ArrayList<String> content = new ArrayList<>();
        content.add("<meta charset='utf-8'/>");
        content.add("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'/>");
        content.add("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
        content.add("<link rel='stylesheet' href='./assets/css/style.css'>");
        content.add("<link rel='stylesheet' "
                + "href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        content.add("<title>MySQL</title>");
        content.add("<script  type='text/javascript' src='./javascript/main.js'></script>");
        return new SimpleTagTemplate("head", content).toString();
    }

    public static String printHeader(String content) {
        String title = new SimpleTagTemplate("h1", content).toString();
        return new SimpleTagTemplate("header", title).toString();
    }

    public static String printNavbar() {
        ArrayList<Object> content = new ArrayList<>();
        content.add(new AnchorTemplate("index.jsp", "Inicio"));
        content.add(new AnchorTemplate("students.jsp", "Alumnos"));
        content.add(new AnchorTemplate("groups.jsp", "Grupos"));
        content.add(new AnchorTemplate("enroll.jsp", "Matriculas"));
        return new SimpleTagTemplate("nav", new DivTemplate(content).toString()).toString();
    }

    public static String printFooter() {
        ArrayList<Object> content = new ArrayList<>();
        ArrayList<String> anchors = new ArrayList<>();
        anchors.add("Imágenes: " + new AnchorTemplate("https://www.freepik.es/", "Freepik &emsp;&emsp;").toString());
        anchors.add("Iconos: " + new AnchorTemplate("https://fontawesome.com/v4/icons/", "Font Awesome").toString());
        content.add(new DivTemplate("Diseñado y desarrollado por Marina Ruiz"));
        content.add(new SimpleTagTemplate("small", anchors).toString());
        return new SimpleTagTemplate("footer", content).toString();
    }
}
