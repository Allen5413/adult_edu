<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">中心子账户管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageUser/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="type" name="type" value="2" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">账户列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addUser/open.html?reqParams=${reqParams}');">添加子账户</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>账户类型</th>
            <th>联系电话</th>
            <th>账号</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="user" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${user.name}</td>
                <td>${user.ugName}</td>
                <td>${user.phone}</td>
                <td>${user.login_name}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editUser/open.html?id=${user.id}&reqParams=${reqParams}');">编辑</a>
                  <a class="btn-opr" href="#" onclick="del(${user.id});">删除</a>
                  <a class="btn-opr" href="#" onclick="resetPwd('${user.login_name}')">重置密码</a>
                </td>
              </tr>
            </c:forEach>
            <%@ include file="../../common/page.jsp"%>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function del(id){
    app.del("您确定要删除该用户？", "${pageContext.request.contextPath}/delUser.json", {"id":id}, "${pageContext.request.contextPath}/pageUser/page.html", $("#pageForm").serialize());
  }

  function resetPwd(loginName){
    app.operator("您确定要重置密码？", "${pageContext.request.contextPath}/resetPwd.json", {"loginName":loginName});
  }
</script>