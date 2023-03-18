<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="app.AppGroup"%>
<%@page import="app.AppStudent"%>

<%@page import="groups.Group"%>
<%@page import="students.Student"%>
<%@page import="utilities.Utilities"%>
<%@page import="templates.*"%>

<!DOCTYPE html>
<html lang="es">
    <%=Utilities.printHead()%>

    <body class="container">
        <%=Utilities.printHeader("Grupos")%>
        <%=Utilities.printNavbar()%>

          <main class="groups-landing">
            <%
            String serv = request.getParameter("serv");
            String req = request.getParameter("req");
            String answer;
            int id = 0;
            String name = null;
            String surname = null;
            String tutor = null;
            int group_id = 0;
            String group_name = null;
            int stuAmount = 0;

            switch (serv) {
                case "student":
                    AppStudent appStudent = new AppStudent();
                    id = Integer.parseInt(request.getParameter("id"));
                    name = request.getParameter("name");
                    surname = request.getParameter("surname");
                    if (request.getParameter("group_id") != null)
                        group_id = Integer.parseInt(request.getParameter("group_id"));
                    group_name = request.getParameter("group_name");
                    switch (req) {
                        case "new":
                            id = appStudent.createStudent(name, surname, group_id);
                            out.print(appStudent.printStudentFile(new Student(id, name, surname, group_id, group_name), true, "req"));
                            break;
                        case "upd":
                            answer = appStudent.updateStudent(id, name, surname, group_id);
                            out.print(answer);
                            out.print(appStudent.printStudentFile(new Student(id, name, surname, group_id, group_name), true, "start"));
                            break;
                        case "del":
                            answer = appStudent.deleteStudent(id);
                            out.print(answer);
                            out.print(new AnchorTemplate("students.jsp", "Atrás", "btn", "btn-back").toString());
                            break;
                        }
                    appStudent.closeAll();
                    break;
                case "group":
                    AppGroup appGroup = new AppGroup();
                    id = Integer.parseInt(request.getParameter("id"));
                    name = request.getParameter("name");
                    tutor = request.getParameter("tutor");
                    stuAmount = Integer.parseInt(request.getParameter("stuAmount"));
                    switch (req) {
                        case "new":
                            id = appGroup.createGroup(name, tutor);
                            out.print(appGroup.printGroupFile(new Group(id, name, tutor, stuAmount), true, "req"));
                            break;
                        case "upd":
                            answer = appGroup.updateGroup(id, name, tutor);
                            out.print(answer);
                            out.print(appGroup.printGroupFile(new Group(id, name, tutor, stuAmount), true, "start"));
                            break;
                        case "del":
                            answer = appGroup.deleteGroup(id);
                            out.print(answer);
                            out.print(new AnchorTemplate("groups.jsp", "Atrás", "btn", "btn-back").toString());
                            break;
                    }
                    appGroup.closeAll();
            }
            %>
          </main>
          <%=Utilities.printFooter()%>
    </body>
</html>
