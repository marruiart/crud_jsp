<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="app.AppStudent"%>
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
            String btn = request.getParameter("btn");
            String req = request.getParameter("req");
            String filter = request.getParameter("filter");
            filter = filter == null ? "" : filter;
            AppStudent app = new AppStudent();
            
            out.print(new InputTextTemplate("search", filter, "search-box").labelled("Filtrar").toString());
            out.print(app.printStudentsTable(filter));
            out.print(new AnchorTemplate("index.jsp", "Atrás", "btn", "btn-back").toString());
            app.closeAll();
            %>
        </main>
        <%=Utilities.printFooter()%>
    </body>
</html>
