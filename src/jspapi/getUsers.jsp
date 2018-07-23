<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>

<%
    out.clear();
    
    try {
        java.util.List<User> list= PersistenceHelper.getInstance().selectAll(User.class);
        out.println("{\"success\":\"yes\",");
        out.println("\"users\":[");
        boolean first= true;
        for(User u : list){
            if(!first){
                out.println(",");
            }
            else {
                first= false;
            }
            out.print("   {");
            out.print("\"id\":");
            out.print("\"" +u.getId()+ "\",");
            out.print("\"username\":");
            out.print("\"" +u.getUsername()+ "\",");
            out.print("\"firstname\":");
            out.print("\"" +u.getFirstName()+ "\",");
            out.print("\"lastname\":");
            out.print("\"" +u.getLastName()+ "\"");
            out.print("}");
        }
        out.println("");
        out.print("]}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
