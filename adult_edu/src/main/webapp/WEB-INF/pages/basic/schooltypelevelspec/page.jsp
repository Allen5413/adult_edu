<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">专业管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageSchoolTypeLevelSpec/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">高校：</span>
        <span class="inline-select">
          <select name="schoolId">
            <option value="">全部</option>
            <c:forEach var="school" items="${schoolList}">
              <option value="${school.id}" <c:if test="${school.id == param.schoolId}">selected="selected" </c:if> >${school.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">招生类型：</span>
        <span class="inline-select">
           <select name="typeId">
             <option value="">全部</option>
             <c:forEach var="type" items="${typeList}">
               <option value="${type.id}" <c:if test="${type.id == param.typeId}">selected="selected" </c:if> >${type.name}</option>
             </c:forEach>
           </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="inline-input"><a id="searchBtn" class="btn-1" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">专业列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addSchoolTypeLevelSpec/open.html?reqParams=${reqParams}');">添加专业</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>高校</th>
            <th>招生类型</th>
            <th>层次</th>
            <th>专业数</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="obj" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${obj.sName}</td>
                <td>${obj.rtName}</td>
                <td>${obj.lName}</td>
                <td>${obj.count}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/find.html?schoolId=${obj.sId}&typeId=${obj.rtId}&levelId=${obj.lId}');">查看</a>
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