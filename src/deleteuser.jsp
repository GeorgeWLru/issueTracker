<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>
<%
    String id= request.getParameter("id");
%>

<div id="success">
<h3>Удалить пользователя?</h3>
<div>
<table border="0">
<tbody id="tblIssues"></tbody>
</table>
</div>
<div><button id="btnOk">Ок</button><button id="btnCancel">Отмена</button></div>
</div>

<div id="error">
<h3>Ошибка</h3>
<p id="errorMsg"></p>
</div>

<script>
$.ajax({
    type: "get",
    dataType: "json",
    url: "jspapi/getUser.jsp",
    data: {id: "<%=id%>"},
    success: function(msg){
        if(msg.success=="yes"){
            var tbl="";
            tbl=tbl+"<tr><td>id</td><td>"+msg.user.id+"</td></tr>";
            tbl=tbl+"<tr><td>Логин</td><td>"+msg.user.username+"</td></tr>";
            tbl=tbl+"<tr><td>Имя</td><td>"+msg.user.firstname+"</td></tr>";
            tbl=tbl+"<tr><td>Фамилия</td><td>"+msg.user.lastname+"</td></tr>";
            $("#error").hide();
            $("#tblIssues").html(tbl);
        }
        else {
            $("#success").hide();
            $("#errorMsg").html(msg.error);
        }
    }
})

$("#btnCancel").on("click", function(event){
    document.location.replace("users.jsp");
})

$("#btnOk").on("click", function(event){
    $.ajax({
        type: "get",
        dataType: "json",
        url: "jspapi/deleteUser.jsp",
        data: {id: "<%=id%>"},
        success: function(msg){
            if(msg.success=="yes"){
                alert("Пользователь удален");
                document.location.replace("users.jsp");
            }
            else {
                $("#success").hide();
                $("#error").show();
                $("#errorMsg").html(msg.error);
            }
        }
    })
})

</script>


<%@ include file="end.jspf" %>
