<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<form action="connect.jsp" method="get">
<p>Для начала работы введите имя базы данных</p>
<input type="text" name="base" value="test"/>
<input type="submit" value="Подключиться"/>
</form>


<%@ include file="end.jspf" %>
