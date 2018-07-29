<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.User" %>
<%@ page import="ru.georgewl.epam.it.Project" %>
<%@ page import="ru.georgewl.epam.it.Issue" %>

<%
    out.clear();
    String topic= request.getParameter("topic");
    String type= request.getParameter("type");
    String stringPriority= request.getParameter("priority");
    String description= request.getParameter("description");
    String assignee= request.getParameter("assignee");
    String project= request.getParameter("project");
    
    try {
        Issue i= new Issue();
        if(topic!=null) {
            i.setTopic(topic);
        }
        if(type!=null) {
            i.setType(type);
        }
        if(stringPriority!=null) {
            int priority= java.lang.Integer.parseInt(stringPriority);
            i.setPriority(priority);
        }
        if(description!=null) {
            i.setDescription(description);
        }
        if(assignee!=null) {
            long id= Long.parseLong(assignee);
            User u= PersistenceHelper.getInstance().retrieve(User.class, id);
            i.setAssigneeRef(PersistenceHelper.getInstance().makeReference(u));
        }
        if(project!=null) {
            long id= Long.parseLong(project);
            Project p= PersistenceHelper.getInstance().retrieve(Project.class, id);
            i.setProjectRef(PersistenceHelper.getInstance().makeReference(p));
        }
        PersistenceHelper.getInstance().persist(i);
        out.println("{\"success\":\"yes\",");
        out.print("\"issueid\":");
        out.println("\"" +i.getId()+ "\"}");
    }
    catch (Exception e) {
        out.clear();
        out.println("{\"success\":\"no\",");
        out.println("\"error\":\""+e+"\"}");
        throw new Exception(e);
    }
    out.close();
%>
