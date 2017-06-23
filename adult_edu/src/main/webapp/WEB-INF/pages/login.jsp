<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/meta.jsp"%>
<%@ include file="common/taglibs.jsp"%>
</head>
<script type="text/javascript">
  $(function(){
    //回车事件
    document.onkeydown = function(e){
      var ev = document.all ? window.event : e;
      if(ev.keyCode==13) {
        sub();
      }
    }
  });

  function sub(){
    var loginName = $.trim($("#loginName").val());
    var pwd = $.trim($("#pwd").val());
    if(loginName == ""){
      $("#loginName").css("color", "red");
    }
    else if(pwd == ""){
      $("#pwd").css("color", "red");
    }else{
      var params = {
        "loginName":loginName,
        "pwd":pwd
      };
      $.ajax({
        url:"${pageContext.request.contextPath}/loginUser/login.htm",
        method : 'POST',
        async:false,
        data:params,
        success:function(data){
          if(data.msg == "success"){
            location.href = "${pageContext.request.contextPath}/index/main.htm";
          }else {
            $("#msg").html(data.msg);
          }
        }
      });
    }
  }
</script>
<body>
<div class="header-view">
  <div class="title">至善网后台管理</div>
</div>
<div id="main_page" class="section-view">
  <div class="logon-view">
    <div class="title">后台管理员登录</div>
    <ul class="input-item-list">
      <li>
        <label class="i-tg">用户名：</label>
        <input type="text" class="reg-input-unam" />
      </li>
      <li>
        <label class="i-tg">密 码：</label>
        <input type="password" class="reg-input-pwd" />
      </li>
      <li><label class="i-tg"></label><a href="#">忘记密码？</a></li>
      <li class="bar-but">
        <a class="reg-btn-1" href="#">登 录</a>
        <a class="reg-btn-2" href="#">重 置</a>
      </li>
    </ul>
  </div>
</div>
</body>
</html>