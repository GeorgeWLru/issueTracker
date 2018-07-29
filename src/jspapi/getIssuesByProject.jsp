<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.Project" %>
<%@ page import="ru.georgewl.epam.it.Issue" %>

<%
    out.clear();
    String project= request.getParameter("project");
    long id= Long.parseLong(project);
    
    try {
        Project p= PersistenceHelper.getInstance().retrieve(Project.class, id);
        ru.georgewl.epam.it.persistence.Reference pr= PersistenceHelper.getInstance().makeReference(p);
        
        java.util.List<Issue> list= PersistenceHelper.getInstance().selectFilteredByReference(Issue.class, Issue.PROJECT_ID, pr);
        out.println("{\"success\":\"yes\",");
        out.println("\"issues\":[");
        boolean first= true;
        for(Issue i : list){
            if(!first){
                out.println(",");
            }
            else {
                first= false;
            }
            out.print("   {");
            out.print("\"id\":");
            out.print("\"" +i.getId()+ "\",");
            out.print("\"topic\":");
            out.print("\"" +i.getTopic()+ "\",");
            out.print("\"type\":");
            out.print("\"" +i.getType()+ "\",");
            out.print("\"priority\":");
            out.print("\"" +i.getPriority()+ "\",");
            out.print("\"description\":");
            out.print("\"" +i.getDescription()+ "\",");
            out.print("\"assigneeId\":");
            out.print("\"" +i.getAssigneeRef().getId()+ "\",");
            out.print("\"projectId\":");
            out.print("\"" +i.getProjectRef().getId()+ "\"");
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
