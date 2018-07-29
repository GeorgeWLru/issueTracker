<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.persistence.Persistable" %>
<%@ page import="ru.georgewl.epam.it.persistence.JSONHelper" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    String className= request.getParameter("class");
    
    try {
        Class clazz= PersistenceHelper.getInstance().getClassByAlias(className);
        Persistable p= PersistenceHelper.getInstance().retrieve(clazz, id);
        String json= JSONHelper.jsonFor(p);
        out.println("{\"success\":\"yes\",");
        out.println("\""+className+"\":");
        out.println(json);
        out.println("}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
