<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑高校</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/editSchool/editor.html">
        <input type="hidden" name="id" value="${school.id}"/>
        <input type="hidden" name="centerId" value="${school.centerId}"/>
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校编码：</td>
            <td><input type="text" id="code" name="code" value="${school.code}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校名称：</td>
            <td><input type="text" id="name" name="name" value="${school.name}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系人：</td>
            <td><input type="text" id="linkMan" name="linkMan" value="${school.linkMan}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系电话：</td>
            <td><input type="text" id="phone" name="phone" value="${school.phone}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校地址：</td>
            <td><input type="text" id="address" name="address" value="${school.address}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校简介：</td>
            <td><textarea class="textarea-intro" name="remark" rows="5" cols="50" >${school.remark}</textarea></td>
          </tr>
          <tr>
            <td class="tag-b">教务负责人：</td>
            <td>
              <select name="userId">
                <c:forEach var="user" items="${userList}">
                  <option value="${user.id}" <c:if test="${school.userId == user.id}">selected="selected" </c:if> >${user.name}</option>
                </c:forEach>
              </select>
            </td>
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
    if($("#code").val() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#name").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    if($("#phone").val() == ""){
      app.alert("请输入联系电话！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/editSchool/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageSchool/page.html", ${reqParams});
  }
</script>