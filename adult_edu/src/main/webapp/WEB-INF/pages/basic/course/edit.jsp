<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑课程</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${course.id}" />
        <input type="hidden" name="schoolId" value="${course.schoolId}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">课程编号：</td>
            <td><input type="text" id="courseCode" name="code" value="${course.code}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">课程名称：</td>
            <td><input type="text" id="courseName" name="name" value="${course.name}" class="input-txt-220" /></td>
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
    if($("#courseCode").val().trim() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#courseName").val().trim() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    <c:if test="${sessionScope.isOperateAudit == 1}">
      if($("#editReson").val() == ""){
        app.alert("请输入变更原因！", 1);
        return false;
      }
    </c:if>
    app.edit("${pageContext.request.contextPath}/editCourse/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/findCourseBySTLSId/find.html", ${reqParams});
  }
</script>