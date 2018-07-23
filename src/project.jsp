<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>
<%
    String id= request.getParameter("id");
%>

<div id="success">
<h3>Проект</h3>
<div>
<table border="0">
<tbody id="tblIssues"></tbody>
</table>
</div>
</div>

<div id="error">
<h3>Ошибка</h3>
<p id="errorMsg"></p>
</div>

<script>
$.ajax({
    type: "get",
    dataType: "json",
    url: "jspapi/getProject.jsp",
    data: {id: "<%=id%>"},
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            tbl=tbl+"<tr><td>id</td><td>"+msg.project.id+"</td></tr>";
            tbl=tbl+"<tr><td>Название</td><td>"+msg.project.name+"</td></tr>";
            $("#error").hide();
            $("#tblIssues").html(tbl);
        }
        else {
            $("#success").hide();
            $("#errorMsg").html(msg.error);
        }
    }
})
</script>


<%@ include file="end.jspf" %>
