<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.Project" %>

<%
    out.clear();
    
    try {
        java.util.List<Project> list= PersistenceHelper.getInstance().selectAll(Project.class);
        out.println("{\"success\":\"yes\",");
        out.println("\"projects\":[");
        boolean first= true;
        for(Project p : list){
            if(!first){
                out.println(",");
            }
            else {
                first= false;
            }
            out.print("   {");
            out.print("\"id\":");
            out.print("\"" +p.getId()+ "\",");
            out.print("\"name\":");
            out.print("\"" +p.getName()+ "\"");
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
