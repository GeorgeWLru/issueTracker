<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        User u= PersistenceHelper.getInstance().retrieve(User.class, id);
        out.println("{\"success\":\"yes\",");
        out.println("\"user\":{");
        out.println("   \"id\":\""+u.getId()+"\",");
        out.println("   \"username\":\""+u.getUsername()+"\",");
        out.println("   \"firstname\":\""+u.getFirstName()+"\",");
        out.println("   \"lastname\":\""+u.getLastName()+"\"");
        out.println("}}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
