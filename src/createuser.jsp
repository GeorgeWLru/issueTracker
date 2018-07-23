<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<h3>Создать пользователя</h3>

<div id="success">
<form>
<p>Логин <input type="text" name="username"/></p>
<p>Имя<input type="text" name="firstname"/></p>
<p>Фамилия<input type="text" name="lastname"/></p>
</form>
<button id="btnCreate" type="submit">Создать</button>
</div>

<div id="error" style="display:none">
<h3>Ошибка</h3>
<p id="errorMsg"></p>
</div>

<script>
$("#btnCreate").on("click", function(event){
    var fields = $( ":input" ).serializeArray();
    var params= $.param(fields);
    
    $.ajax({
        type: "get",
        dataType: "json",
        url: "jspapi/createUser.jsp?"+params,
        success: function(msg){
            if(msg.success=="yes"){
                alert("Пользователь создан");
                document.location.replace("user.jsp?id="+msg.userid);
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
