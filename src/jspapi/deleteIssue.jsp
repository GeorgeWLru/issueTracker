<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.Issue" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        Issue i= PersistenceHelper.getInstance().retrieve(Issue.class, id);
        PersistenceHelper.getInstance().delete(i);
        out.println("{\"success\":\"yes\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
