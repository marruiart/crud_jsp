<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="app.AppGroup"%>

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
            String filter = request.getParameter("filter");
            filter = filter == null ? "" : filter;
            
            out.print(new InputTextTemplate("search", filter, "search-box").labelled("Filtrar").toString());
            AppGroup app = new AppGroup();
            out.print(app.printGroupsTable(filter));
            out.print(new AnchorTemplate("index.jsp", "Atrás", "btn", "btn-back").toString());
            app.closeAll();
            %>
          </main>
          <%=Utilities.printFooter()%>
    </body>
</html>
