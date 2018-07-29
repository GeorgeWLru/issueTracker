<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.persistence.Persistable" %>
<%@ page import="ru.georgewl.epam.it.persistence.JSONHelper" %>

<%
    out.clear();
    String className= request.getParameter("class");
    String data= request.getParameter("data");
    
    try {
        Class clazz= PersistenceHelper.getInstance().getClassByAlias(className);
        Persistable p= JSONHelper.persistableFor(data, clazz);
        PersistenceHelper.getInstance().persist(p);
        out.println("{\"success\":\"yes\",");
        out.print("\"id\":");
        out.println("\"" +p.getId()+ "\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
