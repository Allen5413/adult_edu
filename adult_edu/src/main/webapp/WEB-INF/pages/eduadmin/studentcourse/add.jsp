<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">录入成绩</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addStudentCourse/add.json">
        <input type="hidden" name="studentId" value="${student.id}" />
        <input type="hidden" name="courseId" value="${course.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">学号：</td>
            <td>${student.code}</td>
          </tr>
          <tr>
            <td class="tag-b">姓名：</td>
            <td>${student.name}</td>
          </tr>
          <tr>
            <td class="tag-b">课程编号：</td>
            <td>${course.code}</td>
          </tr>
          <tr>
            <td class="tag-b">课程名称：</td>
            <td>${course.name}</td>
          </tr>
          <tr>
            <td class="tag-b">成绩：</td>
            <td>
              <label><input type="radio" name="score" value="1" <c:if test="${empty studentCourse || studentCourse.score eq '1'}">checked</c:if>>通过 </label>
              <label><input type="radio" name="score" value="0" <c:if test="${!empty studentCourse && studentCourse.score eq '0'}">checked</c:if>>未通过</label>
            </td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="add()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function add(){
    app.add("${pageContext.request.contextPath}/addStudentCourse/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageStudentCourse/page.html", ${reqParams});
  }
</script>