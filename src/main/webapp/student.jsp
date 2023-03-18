<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="app.AppStudent"%>
<%@page import="students.Student"%>
<%@page import="utilities.Utilities"%>
<%@page import="templates.*"%>


<!DOCTYPE html>
<html lang="es">
    <%=Utilities.printHead()%>

    <body class="container">
        <%=Utilities.printHeader("Alumnos")%>
        <%=Utilities.printNavbar()%>
        <main class="students-landing">
            <%
            AppStudent app = null;
            String btn = request.getParameter("btn");
            String req = request.getParameter("req");
            String answer;
            int id = 0;
            String name = null;
            String surname = null;
            int group_id = 0;
            String group_name = null;
            if (!btn.equals("add")){
                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                surname = request.getParameter("surname");
                if (request.getParameter("group_id") != null)
                    group_id = Integer.parseInt(request.getParameter("group_id"));
                group_name = request.getParameter("group_name");
            }

            app = new AppStudent();
            switch (btn) {
                case "request":
                    out.print(app.printStudentFile(new Student(id, name, surname, group_id, group_name), true, "req"));
                    break;
                case "add":
                    out.print(app.printStudentFile());
                    break;
                case "edit":
                    out.print(app.printStudentFile(new Student(id, name, surname, group_id, group_name), false, "upd"));
                    break;
                case "delete":
                    out.print(app.printStudentFile(new Student(id, name, surname, group_id, group_name), true, "del"));
                    break;
            }
            app.closeAll();
            %>
        </main>
        <%=Utilities.printFooter()%>
    </body>
</html>
