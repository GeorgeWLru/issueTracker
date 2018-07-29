<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.Project" %>

<%
    out.clear();
    String name= request.getParameter("name");
    
    Project p= new Project();
    if(name!=null){
        p.setName(name);
    }
    try {
        PersistenceHelper.getInstance().persist(p);
        out.println("{\"success\":\"yes\",");
        out.print("\"projectid\":");
        out.println("\"" +p.getId()+ "\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
