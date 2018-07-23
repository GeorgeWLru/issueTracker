<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.Issue" %>

<%
    out.clear();
    String stringId= request.getParameter("id");
    Long id= Long.parseLong(stringId);
    
    try {
        Issue i= PersistenceHelper.getInstance().retrieve(Issue.class, id);
        out.println("{\"success\":\"yes\",");
        out.println("\"issue\":{");
        out.print("   \"id\":");
        out.println("\"" +i.getId()+ "\",");
        out.print("   \"topic\":");
        out.println("\"" +i.getTopic()+ "\",");
        out.print("   \"type\":");
        out.println("\"" +i.getType()+ "\",");
        out.print("   \"priority\":");
        out.println("\"" +i.getPriority()+ "\",");
        out.print("   \"description\":");
        out.println("\"" +i.getDescription()+ "\",");
        out.print("   \"assigneeId\":");
        out.println("\"" +i.getAssigneeRef().getId()+ "\",");
        out.print("   \"projectId\":");
        out.println("\"" +i.getProjectRef().getId()+ "\"");
        out.println("}}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
    }
    out.close();
%>
