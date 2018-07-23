<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>

<%
    out.clear();
    try {
        PersistenceHelper.getInstance().stop();
        out.println("success");
    }
    catch (Exception e) {
        out.clear();
        out.println(e);
    }
    out.close();
%>
