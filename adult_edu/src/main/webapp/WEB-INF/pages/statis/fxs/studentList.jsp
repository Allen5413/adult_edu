<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">学生列表</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">所招学生列表</a>
      <a class="btn-com f-r" href="#" onclick="downXls();">下载报表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>报名时间</th>
            <th>高校</th>
            <th>招生分销商</th>
            <th>姓名</th>
            <th>学号</th>
            <th>电话</th>
            <th>招生类型</th>
            <th>层次</th>
            <th>专业</th>
            <th>批次</th>
            <th>学籍状态</th>
            <th>缴费状态</th>
            <th>教务老师</th>
          </tr>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="student" items="${pageInfo.pageResults}" varStatus="status">
              <tr onclick="app.clickResources('${pageContext.request.contextPath}/findStudentById/open.html?id=${student.id}');">
                <td>${status.index+1}</td>
                <td><fmt:formatDate value="${student.signUpDate}" pattern="yyyy-MM-dd" /></td>
                <td>${student.scName}</td>
                <td>${student.uName}</td>
                <td>${student.name}</td>
                <td>${student.code}</td>
                <td>${student.phone}</td>
                <td>${student.rtName}</td>
                <td>${student.lName}</td>
                <td>${student.spName}</td>
                <td>${student.year}年${student.term == 0 ? "春季":"秋季"}</td>
                <td>
                  <c:if test="${'0' eq student.state}">在籍</c:if>
                  <c:if test="${'1' eq student.state}">休学</c:if>
                  <c:if test="${'2' eq student.state}">退学</c:if>
                  <c:if test="${'3' eq student.state}">毕业</c:if>
                </td>
                <td>
                  <c:if test="${'0' eq student.feeState}">未缴费</c:if>
                  <c:if test="${'1' eq student.feeState}">未缴完</c:if>
                  <c:if test="${'2' eq student.feeState}">已结清</c:if>
                </td>
                <td>${student.scuName}</td>
              </tr>
            </c:forEach>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<form id="downForm" name="downForm" action="${pageContext.request.contextPath}/downStudent/down.json">
  <input type="hidden" name="schoolId" value="${param.schoolId}" />
  <input type="hidden" name="recruitTypeId" value="${param.recruitTypeId}" />
  <input type="hidden" name="levelId" value="${param.levelId}" />
  <input type="hidden" name="specId" value="${param.specId}" />
  <input type="hidden" name="teachPlanId" value="${param.teachPlanId}" />
  <input type="hidden" name="userId" value="${param.userId}" />
</form>
<script>
  function downXls(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStudent/down.html",
      data:$("#downForm").serialize(),
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>