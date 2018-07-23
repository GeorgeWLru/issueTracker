<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.Project" %>
<%@ page import="ru.georgewl.epam.it.Model" %>

<%
    String name= request.getParameter("name");
    
    Project p= new Project();
    if(name!=null){
        p.setName(name);
    }
    try {
        //PersistenceHelper.getInstance().start(new Model(), "test");
        PersistenceHelper.getInstance().persist(p);
        //PersistenceHelper.getInstance().stop();
        %>project created<%
    }
    catch (Exception e) {
        %>error <%=e%><%
    }
%>
