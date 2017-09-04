<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">导入学生成绩</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="importForm" name="importForm" action="${pageContext.request.contextPath}/importStudentCourse/importAdd.json" enctype="multipart/form-data" method="post">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>
              <select id="schoolId" name="schoolId">
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}">${school.name}</option>
                </c:forEach>
              </select>
            </td>
            <td>
                <a class="btn-com-upload" href="#"><input type="file" name="file" class="uploadfile" onchange="addImport()" />导入学生成绩表</a>
            </td>
            <td>
              <a class="btn-com-upload" href="${pageContext.request.contextPath}/template/courseScore.xlsx">学生成绩表模板下载</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function addImport(){
    if($("#schoolId").val() == ""){
      app.alert("请选择高校", 1);
      return false;
    }
    $("#importForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/importStudentCourse/importAdd.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          app.clickResources("${pageContext.request.contextPath}/pageStudentCourse/page.html", ${reqParams});
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
          app.clickResources("${pageContext.request.contextPath}/importStudentCourse/open.html", ${reqParams});
        }
      }
    });
  }
</script>