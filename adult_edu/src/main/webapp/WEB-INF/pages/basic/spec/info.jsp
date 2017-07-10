<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">专业信息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <table class="set-table-info">
        <tr>
          <td class="tag-b">专业编号：</td>
          <td>${spec.code}</td>
        </tr>
        <tr>
          <td class="tag-b">专业名称：</td>
          <td>${spec.name}</td>
        </tr>
        <tr>
          <td class="tag-b">创建人：</td>
          <td>${spec.creator}</td>
        </tr>
        <tr>
          <td class="tag-b">创建时间：</td>
          <td>${spec.createTime}</td>
        </tr>
      </table>
    </div>
    <c:if test="${!empty param.dataChangeId}">
      <div class="view-opr-info">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">变更内容：</td>
            <td>${dataChange.changeContent}</td>
          </tr>
          <tr>
            <td class="tag-b">变更原因：</td>
            <td>${dataChange.editReson}</td>
          </tr>
          <tr>
            <td class="tag-b">状态：</td>
            <td>
              <c:if test="${dataChange.state == '0'}">待审核</c:if>
              <c:if test="${dataChange.state == '1'}">未通过</c:if>
              <c:if test="${dataChange.state == '2'}">已通过</c:if>
            </td>
          </tr>
          <tr>
            <td class="tag-b">拒绝原因：</td>
            <td><textarea class="textarea-intro" id="refuseContent" rows="5" cols="50" >${dataChange.refuseContent}</textarea></td>
          </tr>
          <c:if test="${dataChange.state == '0'}">
            <tr>
              <td class="tag-b"></td>
              <td>
                <a class="btn-com" href="#" onclick="saveAudit(2)">通过</a>
                <a class="btn-com" href="#" onclick="saveAudit(1)">不通过</a>
              </td>
            </tr>
          </c:if>
        </table>
      </div>
    </c:if>
  </div>
</div>
<script>
  function saveAudit(state){
    var refuseContent = $("#refuseContent").val();
    if(1 == state && refuseContent == ""){
      app.alert("请输入拒绝原因！", 1);
      return false;
    }
    if(2 == state && refuseContent != ""){
      app.alert("审核通过不需要填写拒绝原因", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/auditDataChange.json", {"id":${dataChange.id}, "refuseContent":refuseContent, "state":state}, "${pageContext.request.contextPath}/pageDataChange/page.html", ${reqParams});
  }
</script>