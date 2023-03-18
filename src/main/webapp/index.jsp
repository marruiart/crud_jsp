<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="utilities.Utilities"%>


<!DOCTYPE html>
<html lang="es">
    <%=Utilities.printHead()%>

    <body class="container">
        <%=Utilities.printHeader("CPIFP Campanillas")%>
        <main class="main-landing">
            <form method="GET" action="students.jsp">
                <input type="hidden" name="btn" value="start">
                <button type="submit" class="btn-gest-student">
                    <h2>Gestionar estudiantes</h2>
                    <img src="./assets/img/student.svg" alt="Imagen prediseñada de una persona">
                </button>
            </form>
            <form method="GET" action="groups.jsp">
                <input type="hidden" name="btn" value="start">
                <button type="submit" class="btn-gest-group">
                    <h2>Gestionar grupos</h2>
                    <img src="./assets/img/group.svg" alt="Imagen prediseñada de un group de personas">
                </button>
            </form>
            <form method="GET" action="enroll.jsp">
                <input type="hidden" name="btn" value="start">
                <button type="submit" class="btn-gest-group">
                    <h2>Gestionar matrículas</h2>
                    <img src="./assets/img/enroll.svg" alt="Imagen prediseñada de una persona y una flecha hacia un grupo">
                </button>
            </form>
        </main>
        <%=Utilities.printFooter()%>
    </body>
</html>
