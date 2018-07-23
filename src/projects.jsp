<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<div id="success">
<h3>Список пользователей</h3>
<div>
<table border="1">
<thead><tr><td>id</td><td>Название</td></tr></thead>
<tbody id="tblIssues"></tbody>
</table>
</div>
<div>
<a href="createproject.jsp">Создать новый проект</a>
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
    url: "jspapi/getProjects.jsp",
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            for (var key in msg.projects){
                tbl=tbl+"<tr>";
                tbl=tbl+"<td>"+msg.projects[key].id+"</td>";
                tbl=tbl+"<td><a href='project.jsp?id="+msg.projects[key].id+"'>"+msg.projects[key].name+"</td>";
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
