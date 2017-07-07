<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">专业管理</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">专业列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addSchoolTypeLevelSpec/open.html?reqParams=${reqParams}');">添加课程</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>专业编码</th>
            <th>专业名称</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty specList}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty specList}">
            <c:forEach var="spec" items="${specList}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${spec.code}</td>
                <td>${spec.name}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/open.html?id=${center.id}&reqParams=${reqParams}');">查看</a>
                </td>
              </tr>
            </c:forEach>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function resetPwd(loginName){
    app.operator("您确定要重置密码？", "${pageContext.request.contextPath}/resetPwd.json", {"loginName":loginName});
  }
</script>