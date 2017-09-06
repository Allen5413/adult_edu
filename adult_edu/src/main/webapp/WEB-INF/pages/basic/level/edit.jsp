<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑层次</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${level.id}"/>
        <input type="hidden" name="centerId" value="${level.centerId}"/>
        <table class="set-table-info">
          <tr>
            <td class="tag-b">层次名称：</td>
            <td><input type="text" id="name" name="name" value="${level.name}" class="input-txt-220" /></td>
          </tr>
          <c:if test="${sessionScope.isOperateAudit == 1}">
            <tr>
              <td class="tag-b">变更原因：</td>
              <td><input type="text" id="editReson" name="editReson" class="input-txt-220" /></td>
            </tr>
          </c:if>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="saveLevel()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function saveLevel(){
    if($("#name").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    <c:if test="${sessionScope.isOperateAudit == 1}">
      if($("#editReson").val() == "" || $("#editReson").val().length > 30){
        app.alert("请输入变更原因，不能超过30个字！", 1);
        return false;
      }
    </c:if>
    app.edit("${pageContext.request.contextPath}/editLevel/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageLevel/page.html", ${reqParams});
  }
</script>