<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">个人中心</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">中心基本信息</a>
    </div>
    <div class="mod-content">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">头像：</td>
            <td>
              <c:if test="${empty center.logo}"><span style="color: #ff0000">还没有上传图片</span></c:if>
              <c:if test="${!empty center.logo}"><img src="${pageContext.request.contextPath}${center.logo}?random=${random}" style="width: 50px; height: 50px; border: 0px;" /></c:if>
            </td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditLogo.html?id=${center.id}');">编辑</a></td>
          </tr>
          <tr>
            <td class="tag-b">banner：</td>
            <td>
              <c:if test="${empty center.banner}"><span style="color: #ff0000">还没有上传图片</span></c:if>
              <c:if test="${!empty center.banner}"><img src="${pageContext.request.contextPath}${center.banner}?random=${random}" style="width: 600px; height: 300px;" /></c:if>
            </td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditBanner.html?id=${center.id}');">编辑</a></td>
          </tr>
          <tr>
            <td class="tag-b">名称：</td>
            <td>${center.name}</td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditName.html?id=${center.id}');">编辑</a></td>
          </tr>
          <tr>
            <td class="tag-b">编码：</td>
            <td>${center.code}</td>
          </tr>
          <tr>
            <td class="tag-b">邮箱：</td>
            <td>${center.email}</td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditEmail.html?id=${center.id}');">编辑</a></td>
          </tr>
          <tr>
            <td class="tag-b">地址：</td>
            <td>${center.address}</td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditAddress.html?id=${center.id}');">编辑</a></td>
          </tr>
          <tr>
            <td class="tag-b">简介：</td>
            <td>${center.remark}</td>
            <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editCenter/openEditRemark.html?id=${center.id}');">编辑</a></td>
          </tr>
        </table>
    </div>
  </div>

  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">账号信息</a>
    </div>
    <div class="mod-content">
      <table class="set-table-info">
        <tr>
          <td class="tag-b">账号：</td>
          <td>${user.loginName}</td>
        </tr>
        <tr>
          <td class="tag-b">密码：</td>
          <td>**************</td>
          <td><a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editPwd/open.html');">修改密码</a></td>
        </tr>
      </table>
    </div>
  </div>

  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">通讯录</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <c:if test="${!empty userList}">
            <c:forEach var="user" items="${userList}" varStatus="status">
              <tr>
                <td>${user.name}</td>
                <td>${user.ugName}</td>
                <td>${user.phone}</td>
                <%--<td>--%>
                  <%--<a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editFeeType/open.html?id=${feeType.id}&reqParams=${reqParams}');">编辑</a>--%>
                  <%--<c:if test="${user.id != sessionScope.userId}">--%>
                    <%--<a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editFeeType/open.html?id=${feeType.id}&reqParams=${reqParams}');">删除</a>--%>
                  <%--</c:if>--%>
                <%--</td>--%>
              </tr>
            </c:forEach>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>