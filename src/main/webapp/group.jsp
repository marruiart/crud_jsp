<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="app.AppGroup"%>

<%@page import="groups.Group"%>
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
            String btn = request.getParameter("btn");
            String req = request.getParameter("req");
            String answer;
            int id = 0;
            String name = null;
            String tutor = null;
            int stuAmount = 0;
            if (!btn.equals("add")){
                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                tutor = request.getParameter("tutor");
                stuAmount = Integer.parseInt(request.getParameter("stuAmount"));
            }

            AppGroup app = new AppGroup();
            switch (btn) {
                case "request":
                    out.print(app.printGroupFile(new Group(id, name, tutor, stuAmount), true, "req"));
                    out.print(app.printSimpleStudentsInGroup(id));
                    break;
                case "add":
                    out.print(app.printGroupFile());
                    break;
                case "edit":
                    out.print(app.printGroupFile(new Group(id, name, tutor, stuAmount), false, "upd"));
                    break;
                case "delete":
                    out.print(app.printGroupFile(new Group(id, name, tutor, stuAmount), true, "del"));
                    break;
            }
            app.closeAll();
            %>
          </main>
          <%=Utilities.printFooter()%>
    </body>
</html>
