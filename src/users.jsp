<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<div id="success">
<h3>Список пользователей</h3>
<div>
<table border="1">
<thead><tr><td>id</td><td>Логин</td><td>Имя</td><td>Фамилия</td></tr></thead>
<tbody id="tblIssues"></tbody>
</table>
<div>
<a href="createuser.jsp">Создать нового пользователя</a>
</div>
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
    url: "jspapi/getUsers.jsp",
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            for (var key in msg.users){
                tbl=tbl+"<tr>";
                tbl=tbl+"<td>"+msg.users[key].id+"</td>";
                tbl=tbl+"<td><a href='user.jsp?id="+msg.users[key].id+"'>"+msg.users[key].username+"</td>";
                tbl=tbl+"<td>"+msg.users[key].firstname+"</td>";
                tbl=tbl+"<td>"+msg.users[key].lastname+"</td>";
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
