<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑分销商</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${user.id}" />
        <input type="hidden" name="type" value="${user.type}" />
        <input type="hidden" name="centerId" value="${user.centerId}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">分销商名称：</td>
            <td><input type="text" id="name" name="name" value="${user.name}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">分销商编码：</td>
            <td><input type="text" id="code" name="code" value="${user.code}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系人：</td>
            <td><input type="text" id="linkMan" name="linkMan" value="${user.linkMan}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">手机号码：</td>
            <td><input type="text" id="phone" name="phone" value="${user.phone}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">详细地址：</td>
            <td><input type="text" id="address" name="address" value="${user.address}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="saveUser()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function saveUser(){
    if($("#name").val() == ""){
      app.alert("请输入分销商名称！", 1);
      return false;
    }
    if($("#code").val() == ""){
      app.alert("请输入分销商编码！", 1);
      return false;
    }
    if($("#linkMan").val() == ""){
      app.alert("请输入联系人！", 1);
      return false;
    }
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    if($("#address").val() == ""){
      app.alert("请输入详细地址！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/editUser/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageUser/page.html", ${reqParams});
  }
</script>