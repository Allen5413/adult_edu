<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加专业</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="schoolTypeLevelSpecId" value="${param.stlsId}"/>
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>${school.name}</td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>${type.name}</td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>${level.name}</td>
          </tr>
          <tr>
            <td class="tag-b">专业：</td>
            <td>[${spec.code}]${spec.name}</td>
          </tr>
          <tr>
            <td class="tag-b">课程编号：</td>
            <td><input type="text" id="courseCode" name="courseCode" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">课程名称：</td>
            <td><input type="text" id="courseName" name="courseName" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <table>
                  <td>
                    <a class="btn-com-upload" href="#" onclick="add()">保存提交</a>
                  </td>
                  <td>
                    <form></form>
                    <form id="importForm" name="importForm" action="${pageContext.request.contextPath}/importStlsc.json" enctype="multipart/form-data" method="post">
                      <input type="hidden" name="stlsId" value="${param.stlsId}"/>
                      <a class="btn-com-upload" href="#"><input type="file" name="file" class="uploadfile" onchange="addImport()" />导入课程表</a>
                    </form>
                  </td>
                  <td><a class="btn-com-upload" href="${pageContext.request.contextPath}/template/course.xlsx">课程表模板下载</a></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function add(){
    if($("#courseCode").val().trim() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#courseName").val().trim() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addSchoolTypeLevelSpecCourse/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/findCourseBySTLSId/find.html", ${reqParams});
  }

  function addImport(){
    $("#importForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/importStlsc.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          app.msg("导入成功", 0);
          app.clickResources("${pageContext.request.contextPath}/findCourseBySTLSId/find.html", ${reqParams});
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
          app.clickResources('${pageContext.request.contextPath}/addSchoolTypeLevelSpecCourse/open.html?reqParams=${reqParams}&stlsId=${param.stlsId}');
        }
      }
    });
  }
</script>