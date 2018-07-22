<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.Model" %>

<%
    String base= request.getParameter("base");
    if(base!=null){
        try {
            PersistenceHelper.getInstance().start(new Model(), base);
            %>session started<%
        }
        catch (Exception e) {
            %>error <%=e%><%
        }
    }
    else{
        %>need base parameter<%
    }
%>
