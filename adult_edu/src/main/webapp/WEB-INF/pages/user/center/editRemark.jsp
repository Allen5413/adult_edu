<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑中心简介</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form">
        <input type="hidden" name="id" value="${center.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">学习中心简介：</td>
            <td><textarea class="textarea-intro" id="remark" name="remark" rows="5" cols="50" >${center.remark}</textarea></td>
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
    app.edit("${pageContext.request.contextPath}/editCenter/editRemark.json", $("#form").serialize(), "${pageContext.request.contextPath}/findCneterById/open.html");
  }
</script>