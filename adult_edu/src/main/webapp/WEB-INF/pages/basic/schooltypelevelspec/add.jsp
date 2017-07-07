<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加专业</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" id="authorizeDate" name="authorizeDate" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>
              <select name="schoolId">
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}">${school.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>
              <select name="recruitTypeId">
                <c:forEach var="type" items="${typeList}">
                  <option value="${type.id}">${type.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>
              <select name="levelId">
                <c:forEach var="level" items="${levelList}">
                  <option value="${level.id}">${level.name}</option>
                </c:forEach>
              </select>
            </td>
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
    app.add("${pageContext.request.contextPath}/addSchoolTypeLevelSpec/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageSchoolTypeLevelSpec/page.html", ${reqParams});
  }
</script>