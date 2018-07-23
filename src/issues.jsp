<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<div id="success">
<h3>Список ошибок</h3>
<div>
<table border="1">
<thead><tr><td>id</td><td>Тема</td><td>Описание</td><td>Приоритет</td><td>Проект</td><td>Исполнитель</td></tr></thead>
<tbody id="tblIssues"></tbody>
</table>
</div>

<div>
<a href="createissue.jsp">Создать новое описание ошибки</a>
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
    url: "jspapi/getIssues.jsp",
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            for (var key in msg.issues){
                tbl=tbl+"<tr>";
                tbl=tbl+"<td>"+msg.issues[key].id+"</td>";
                tbl=tbl+"<td><a href='issue.jsp?id="+msg.issues[key].id+"'>"+msg.issues[key].topic+"</a></td>";
                tbl=tbl+"<td>"+msg.issues[key].description+"</td>";
                tbl=tbl+"<td>"+msg.issues[key].priority+"</td>";
                tbl=tbl+"<td><a href='project.jsp?id="+msg.issues[key].projectId+"'>проект</a></td>";
                tbl=tbl+"<td><a href='user.jsp?id="+msg.issues[key].assigneeId+"'>пользователь</a></td>";
                tbl=tbl+"</tr>";
            }
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
