<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        Project p= PersistenceHelper.getInstance().retrieve(Project.class, id);
        out.println("{\"success\":\"yes\",");
        out.println("\"project\":{");
        out.println("   \"id\":\""+p.getId()+"\",");
        out.println("   \"name\":\""+p.getName()+"\"");
        out.println("}}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
