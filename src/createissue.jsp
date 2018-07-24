<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="begin.jspf" %>

<h3>Создать описание ошибки</h3>

<div id="success">
<form>
<p>Тема <input type="text" name="topic"/></p>
<p>Тип
  <select name="type">
    <option value="bug">ошибка</option>
    <option value="issue">проблема</option>
    <option value="question">вопрос</option>
  </select>
</p>
<p>Приоритет
  <select name="priority">
    <option value="1">очень низкий</option>
    <option value="2">низкий</option>
    <option value="3">средний</option>
    <option value="4">высокий</option>
    <option value="5">очень высокий</option>
    <option value="6">критический</option>
  </select>
</p>
<p>Описание<input type="text" name="description"/></p>
<p>Исполнитель
    <select name="assignee" id="selectAssignee">
    </select>
</p>
<p>Проект
    <select name="project" id="selectProject">
    </select>
</p>
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
    
    alert(params);
    
    $.ajax({
        type: "get",
        dataType: "json",
        url: "jspapi/createIssue.jsp?"+params,
        success: function(msg){
            if(msg.success=="yes"){
                alert("Описание ошибки создано");
                document.location.replace("issue.jsp?id="+msg.issueid);
            }
            else {
                $("#success").hide();
                $("#error").show();
                $("#errorMsg").html(msg.error);
            }
        }
    })
    
    
})

$.ajax({
    type: "get",
    dataType: "json",
    url: "jspapi/getProjects.jsp",
    success: function(msg){
        if(msg.success=="yes"){
            var opts="";
            for (var key in msg.projects){
                opts=opts+"<option value='"+msg.projects[key].id+"'>";
                opts=opts+msg.projects[key].name+"</option>";
            }
            $("#selectProject").html(opts);
        }
        else {
            $("#success").hide();
            $("#error").show();
            $("#errorMsg").html(msg.error);
        }
    }
})

$.ajax({
    type: "get",
    dataType: "json",
    url: "jspapi/getUsers.jsp",
    success: function(msg){
        if(msg.success=="yes"){
            var opts="";
            for (var key in msg.users){
                opts=opts+"<option value='"+msg.users[key].id+"'>";
                opts=opts+msg.users[key].firstname+" "+msg.users[key].lastname+"</option>";
            }
            $("#selectAssignee").html(opts);
        }
        else {
            $("#success").hide();
            $("#error").show();
            $("#errorMsg").html(msg.error);
        }
    }
})

</script>

<%@ include file="end.jspf" %>
