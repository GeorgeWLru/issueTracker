<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.User" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        User u= PersistenceHelper.getInstance().retrieve(User.class, id);
        PersistenceHelper.getInstance().delete(u);
        out.println("{\"success\":\"yes\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e.toString().replace("\"","").replace("\n"," ")+"\"}");
    }
    out.close();
%>
