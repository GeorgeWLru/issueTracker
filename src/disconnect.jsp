<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>
<div id="success">
<h3>Отключение успешно</h3>
</div>
<div id="error">
<h3>Ошибка отключения</h3>
<p id="errorMsg"></p>
</div>

<script>
$.ajax({
    type: "get",
    url: "jspapi/stopSession.jsp",
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
