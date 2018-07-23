<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<h3>Создать проект</h3>

<div id="success">
<form>
<p>Название <input type="text" name="name"/></p>
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
        url: "jspapi/createProject.jsp?"+params,
        success: function(msg){
            if(msg.success=="yes"){
                alert("Проект создан");
                document.location.replace("project.jsp?id="+msg.projectid);
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
