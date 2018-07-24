<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        Project p= PersistenceHelper.getInstance().retrieve(Project.class, id);
        PersistenceHelper.getInstance().delete(p);
        out.println("{\"success\":\"yes\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e.toString().replace("\"","").replace("\n"," ")+"\"}");
    }
    out.close();
%>
