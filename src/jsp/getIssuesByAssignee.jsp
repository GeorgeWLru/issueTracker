<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>
<%@ page import="ru.georgewl.epam.it.model.Issue" %>

<%
    String assignee= request.getParameter("assignee");
    
    try {
        long id= Long.getLong(assignee);
        User u= PersistenceHelper.getInstance().retrieve(User.class, id);
        ru.​georgewl.​epam.​it.​persistence.Reference ur= PersistenceHelper.getInstance().makeReference(u);
        
        java.​util.List<Issue> list= PersistenceHelper.getInstance().selectFilteredByReference(Issue.class, "ASSIGNEE_USER_ID", ur);
        for(Issue i : list){
            //
        }

        %>project created<%
    }
    catch (Exception e) {
        %>error <%=e%><%
    }
%>
