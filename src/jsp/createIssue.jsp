<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>
<%@ page import="ru.georgewl.epam.it.model.Issue" %>
<%@ page import="java.lang.Integer" %>

<%
    String topic= request.getParameter("topic");
    String assignee= request.getParameter("assignee");
    String project= request.getParameter("project");
    
    try {
        Issue i= new Issue();
        if(topic!=null) {
            i.setTopic(topic);
        }
        if(assignee!=null) {
            long id= Long.getLong(assignee);
            User u= PersistenceHelper.getInstance().retrieve(User.class, id);
            i.setAssigneeRef(PersistenceHelper.getInstance().makeReference(u));
        }
        if(project!=null) {
            long id= Long.getLong(project);
            Project p= PersistenceHelper.getInstance().retrieve(Project.class, id);
            i.setProjectRef(PersistenceHelper.getInstance().makeReference(p));
        }
        PersistenceHelper.getInstance().persist(i);
        %>issue created<%
    }
    catch (Exception e) {
        %>error <%=e%><%
    }
%>
