<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">数据变更管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageDataChange/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">学生姓名：</span>
        <span class="inline-select">
          <input type="text" class="input-txt-200" id="studentName" name="studentName" value="${param.studentName}" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">更改时间：</span>
        <span class="inline-select">
          <input type="text" id="beginDate" name="beginDate" value="${param.beginDate}" style="height: 24px;"
                 onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" /> -
          <input type="text" id="endDate" name="endDate" value="${param.endDate}" style="height: 24px;"
                 onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" />
        </span><p />
        <span class="itg">审核状态：</span>
        <span class="inline-select">
          <select name="state">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.feeState eq '0'}">selected="selected" </c:if> >待审核</option>
            <option value="1" <c:if test="${param.feeState eq '1'}">selected="selected" </c:if>>未通过</option>
            <option value="2" <c:if test="${param.feeState eq '2'}">selected="selected" </c:if>>已通过</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${'0' eq isChaildUser}">
          <span class="itg">更改人：</span>
          <span class="inline-select">
            <select name="createId">
              <option value="">全部</option>
              <c:forEach var="user" items="${userList}">
                <option value="${user.id}" <c:if test="${param.createId == user.id}">selected="selected" </c:if> >${user.name}</option>
              </c:forEach>
            </select>
          </span>&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <span class="inline-input"><a id="searchBtn" class="btn-1" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">数据变更列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>更改人</th>
            <th>联系方式</th>
            <th>更改时间</th>
            <th>学生姓名</th>
            <th>学号</th>
            <th>更改内容</th>
            <th>审核状态</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="dataChange" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${dataChange.name}</td>
                <td>${dataChange.phone}</td>
                <td>${dataChange.createTime}</td>
                <td>${dataChange.studentName}</td>
                <td>${dataChange.code}</td>
                <td>${dataChange.changeContent}</td>
                <td>
                  <c:if test="${dataChange.state == 0}">待审核</c:if>
                  <c:if test="${dataChange.state == 1}">未通过</c:if>
                  <c:if test="${dataChange.state == 2}">已通过</c:if>
                </td>
                <td>
                  <c:if test="${dataChange.state < 2 || dataChange.type < 2}">
                    <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/findDataChangeById/open.html?id=${dataChange.id}&table=${dataChange.changeTable}&tableId=${dataChange.changeTableId}&reqParams=${reqParams}');">查看</a>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
            <%@ include file="../common/page.jsp"%>
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