<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑用户</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${user.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">账户类型：</td>
            <td>
              <select name="userGroupId">
                <option value="3" <c:if test="${userGroupId == 3}">selected="selected" </c:if> >招生</option>
                <option value="4" <c:if test="${userGroupId == 4}">selected="selected" </c:if>>教务</option>
                <option value="5" <c:if test="${userGroupId == 5}">selected="selected" </c:if>>财务</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">姓名：</td>
            <td><input type="text" id="name" name="name" value="${user.name}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">手机号码：</td>
            <td><input type="text" id="phone" name="phone" value="${user.phone}" class="input-txt-220" /></td>
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
      app.alert("请输入姓名！", 1);
      return false;
    }
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/editUser/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageUser/page.html", ${reqParams});
  }
</script>