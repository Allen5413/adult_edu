<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑中心邮箱</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${center.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">学习中心邮箱：</td>
            <td><input type="text" id="email" name="email" class="input-txt-220"  value="${center.email}" /></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="saveCenter()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function saveCenter(){
    if($("#email").val() == ""){
      app.alert("请输入邮箱！", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/editCenter/editEmail.json", $("#form").serialize(), "${pageContext.request.contextPath}/findCneterById/open.html");
  }
</script>