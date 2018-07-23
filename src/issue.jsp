<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>
<%
    String id= request.getParameter("id");
%>

<div id="success">
<h3>Описание ошибки</h3>
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
    url: "jspapi/getIssue.jsp",
    data: {id: "<%=id%>"},
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            tbl=tbl+"<tr><td>id</td><td>"+msg.issue.id+"</td></tr>";
            tbl=tbl+"<tr><td>Тема</td><td>"+msg.issue.topic+"</td></tr>";
            tbl=tbl+"<tr><td>Тип</td><td>"+msg.issue.type+"</td></tr>";
            tbl=tbl+"<tr><td>Приоритет</td><td>"+msg.issue.priority+"</td></tr>";
            tbl=tbl+"<tr><td>Описание</td><td>"+msg.issue.description+"</td></tr>";
            tbl=tbl+"<tr><td>Исполнитель</td><td><a href='user.jsp?id="+msg.issue.assigneeId+"'>пользователь</a></td></tr>";
            tbl=tbl+"<tr><td>Проект</td><td><a href='project.jsp?id="+msg.issue.projectId+"'>проект</a></td></tr>";
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
