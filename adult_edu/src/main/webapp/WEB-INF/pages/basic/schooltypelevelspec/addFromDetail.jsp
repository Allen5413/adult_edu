<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加专业</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="schoolId" value="${param.schoolId}"/>
        <input type="hidden" name="recruitTypeId" value="${param.typeId}"/>
        <input type="hidden" name="levelId" value="${param.levelId}"/>
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
            <td class="tag-b">专业编号：</td>
            <td><input type="text" id="specCode" name="specCode" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">专业名称：</td>
            <td><input type="text" id="specName" name="specName" class="input-txt-220" /></td>
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
    if($("#specCode").val() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#specName").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addSchoolTypeLevelSpec/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/find.html", ${reqParams});
  }
</script>