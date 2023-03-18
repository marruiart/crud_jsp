<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="app.AppGroup"%>
<%@page import="app.AppStudent"%>
<%@page import="java.util.ArrayList"%>

<%@page import="utilities.Utilities"%>
<%@page import="templates.*"%>

<!DOCTYPE html>
<html lang="es">
    <%=Utilities.printHead()%>

    <body class="container">
        <%=Utilities.printHeader("Matrículas")%>
        <%=Utilities.printNavbar()%>

          <main class="enroll-landing">
            <%
            AppGroup appGroup = new AppGroup();
            AppStudent appStudent = new AppStudent();
            String[] ids = request.getParameterValues("id");
            String btn = request.getParameter("btn");
            String req = request.getParameter("req");
            String filter = request.getParameter("filter");
            Boolean enroll = Boolean.parseBoolean(request.getParameter("enroll"));
            Integer select = request.getParameter("select") == null ? 0 : Integer.parseInt(request.getParameter("select"));
            filter = filter == null ? "" : filter;
            if (ids != null && ids.length != 0) {
              if (enroll)
                for (String id : ids)
                  appStudent.updateStudent(Integer.parseInt(id), select);
              else if (!enroll)
                  for (String id : ids)
                    appStudent.updateStudent(Integer.parseInt(id), 0);  
            }
            
            out.print(new AnchorTemplate("index.jsp", "Atrás", "btn", "btn-back").toString());
            
            ArrayList<Object> form = new ArrayList<>();
            form.add(new InputTextTemplate("filter", filter, "search-box").labelled("Filtrar", "search-label").toString());
            form.add(new InputHiddenTemplate("enroll", true));
            form.add(new InputHiddenTemplate("select", select));
            form.add(new ButtonTemplate("submit", "Matricular", "btn", "enroll").toString());
            form.add(appStudent.printCheckboxStudentsList(filter, 0, "Alumnos sin matricular"));
            String unrolled = new FormTemplate("enroll.jsp", "GET", form, "form-enroll").toString();

            form = new ArrayList<>();
            form.add(new InputHiddenTemplate("filter", filter));
            form.add(new InputHiddenTemplate("enroll", false));
            form.add(ItemTemplate.prepareGroupsSelect("select", "Grupo", false, appGroup.get_serv(), "search-group"));
            form.add(new ButtonTemplate("submit", "Desmatricular", "btn", "unenroll").toString());
            form.add(appStudent.printCheckboxStudentsList("", select, "Alumnos del grupo"));
            String groups = new FormTemplate("enroll.jsp", "GET", form, "form-enroll").toString();

            out.print(new DivTemplate(unrolled + groups, "div-enroll").toString());
            appGroup.closeAll();
            appStudent.closeAll();
            %>
          </main>
          <%=Utilities.printFooter()%>
    </body>
</html>
