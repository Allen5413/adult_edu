<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">报名表管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageSignUp/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">学生姓名：</span>
        <span class="inline-select">
          <input type="text" class="input-txt-200" name="name" value="${param.name}" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">审核状态：</span>
        <span class="inline-select">
          <select name="state">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待审核</option>
            <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >中心未通过</option>
            <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >高校审核中</option>
            <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >高校未通过</option>
            <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if> >高校已通过</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">上传时间：</span>
        <span class="inline-select">
          <select name="date">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.date eq '0'}">selected="selected" </c:if> >最近一周</option>
            <option value="1" <c:if test="${param.date eq '1'}">selected="selected" </c:if> >最近半个月</option>
            <option value="2" <c:if test="${param.date eq '2'}">selected="selected" </c:if> >最近一个月</option>
            <option value="3" <c:if test="${param.date eq '3'}">selected="selected" </c:if> >最近三个月</option>
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
      <a href="#">报名表列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>手机</th>
            <th>身份证号</th>
            <th>上传时间</th>
            <th>状态</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="signUp" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${signUp.name}</td>
                <td>${signUp.phone}</td>
                <td>${signUp.idCard}</td>
                <td>${signUp.createTime}</td>
                <td>${signUp.stateStr}</td>
                <td>
                  <c:if test="${signUp.state != 2 && signUp.state != 4}">
                    <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editSignUp/open.html?id=${signUp.id}&reqParams=${reqParams}');">编辑</a>
                  </c:if>
                  <c:if test="${signUp.state == 2}">
                    <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editSignUp/open.html?id=${signUp.id}&reqParams=${reqParams}');">查看</a>
                  </c:if>
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