<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加层次</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">层次名称：</td>
            <td><input type="text" id="name" name="name" class="input-txt-220" /></td>
          </tr>
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
    app.add("${pageContext.request.contextPath}/addLevel/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageLevel/page.html", ${reqParams});
  }
</script>