<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>
<%@ page import="ru.georgewl.epam.it.Model" %>

<%
    try {
        PersistenceHelper.getInstance().stop();
        %>session stopped<%
    }
    catch (Exception e) {
        %>error <%=e%><%
    }
%>
