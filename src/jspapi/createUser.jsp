<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.georgewl.epam.it.persistence.PersistenceHelper" %>
<%@ page import="ru.georgewl.epam.it.model.User" %>
<%@ page import="ru.georgewl.epam.it.Model" %>

<%
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
        PersistenceHelper.getInstance().start(new Model(), "test");
        PersistenceHelper.getInstance().persist(u);
        PersistenceHelper.getInstance().stop();
        %>user created<%
    }
    catch (Exception e) {
        %>error <%=e%><%
    }
%>
