<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">用户管理</div>
  <ul class="search-view">
    <form id="form" name="form" action="${pageContext.request.contextPath}/pageCenter/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">缴费状态：</span>
        <span class="inline-select">
          <select name="feeState">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.feeState eq '0'}">selected="selected" </c:if> >未缴费</option>
            <option value="2" <c:if test="${param.feeState eq '2'}">selected="selected" </c:if>>已缴费</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">授权到期时间：</span>
        <span class="inline-select">
          <input type="text" id="date" name="date" value="${param.date}" style="height: 24px;"
                 onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">中心名称：</span>
        <span class="inline-select">
          <input type="text" class="input-txt-200" id="name" name="name" value="${param.name}" />
        </span>
        <span class="inline-input"><a class="btn-1" href="#" onclick="app.searchFormPage($('#form'), $('#form').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">用户列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addCenter/open.html?reqParams=${reqParams}');">添加新用户</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>账号</th>
            <th>生成时间</th>
            <th>学习中心名称</th>
            <th>学习中心编号</th>
            <th>授权到期时间</th>
            <th>缴费状态</th>
            <th>账号状态</th>
            <th width="110">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="center" items="${pageInfo.pageResults}">
              <tr>
                <td>${center.phone}</td>
                <td>${center.createTime}</td>
                <td>${center.name}</td>
                <td>${center.code}</td>
                <td>${center.authorizeDate}</td>
                <td>${center.feeStateStr}</td>
                <td>${center.stateStr}</td>
                <td>
                  <a class="btn-opr" href="#">手机去绑定</a>
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