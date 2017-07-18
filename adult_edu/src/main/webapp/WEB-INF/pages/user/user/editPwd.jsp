<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">修改密码</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">旧密码：</td>
            <td><input type="password" id="oldPwd" name="oldPwd" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">新密码：</td>
            <td><input type="password" id="newPwd" name="newPwd" class="input-txt-220"/></td>
          </tr>
          <tr>
            <td class="tag-b">重复新密码：</td>
            <td><input type="password" id="againPwd" name="againPwd" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="edit()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function edit(){
    if($("#oldPwd").val() == ""){
      app.alert("请输入旧密码！", 1);
      return false;
    }
    if($("#newPwd").val() == ""){
      app.alert("请输入新密码！", 1);
      return false;
    }
    if($("#newPwd").val() != $("#againPwd").val()){
      app.alert("新密码2次输入不一致！", 1);
      return false;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/editPwd/editor.json",
      data:$("#form").serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          location.href = "${pageContext.request.contextPath}";
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>