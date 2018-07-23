<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>
<%
    String base= request.getParameter("base");
%>

<div id="success">
<h3>Соединение успешно</h3>
</div>
<div id="error">
<h3>Ошибка подключения</h3>
<p id="errorMsg"></p>
</div>

<script>
$.ajax({
    type: "get",
    url: "jspapi/startSession.jsp",
    data: {base: "<%=base%>"},
    success: function(msg){
        if(msg.trim()=="success"){
            $("#error").hide();
        }
        else {
            $("#success").hide();
            $("#errorMsg").html(msg);
        }
    }
})
</script>


<%@ include file="end.jspf" %>
