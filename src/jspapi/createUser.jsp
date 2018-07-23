<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>

<%
    out.clear();
    String username= request.getParameter("username");
    String firstname= request.getParameter("firstname");
    String lastname= request.getParameter("lastname");
    
    User u= new User();
    if(username!=null){
        u.setUsername(username);
    }
    if(firstname!=null){
        u.setFirstName(firstname);
    }
    if(lastname!=null){
        u.setLastName(lastname);
    }
    try {
        PersistenceHelper.getInstance().persist(u);
        out.println("{\"success\":\"yes\",");
        out.print("\"userid\":");
        out.println("\"" +u.getId()+ "\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
