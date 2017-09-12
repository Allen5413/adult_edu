<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">教学计划基本信息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <table class="set-table-info">
        <tr>
          <td class="tag-b">高校：</td>
          <td>${school.name}</td>
        </tr>
        <tr>
          <td class="tag-b">招生类型：</td>
          <td>${type.name}</td>
        </tr>
        <tr>
          <td class="tag-b">层次：</td>
          <td>${level.name}</td>
        </tr>
        <tr>
          <td class="tag-b">专业：</td>
          <td>${spec.name}</td>
        </tr>
        <tr>
          <td class="tag-b">开课年季：</td>
          <td>${teachPlan.year}年${teachPlan.term == 0 ? "春季":"秋季"}</td>
        </tr>
        <tr>
          <td class="tag-b">报名起止时间：</td>
          <td>${fn:substring(teachPlan.beginDate, 0, 11)} 到 ${fn:substring(teachPlan.endDate, 0, 11)}</td>
        </tr>
        <tr>
          <td class="tag-b">创建人：</td>
          <td>${teachPlan.cerator}</td>
        </tr>
        <tr>
          <td class="tag-b">创建时间：</td>
          <td>${teachPlan.createTime}</td>
        </tr>
        <tr>
          <td class="tag-b">关联课程：</td>
          <td></td>
        </tr>
      </table>
      <div class="data-table-list">
      <table width="100%">
        <tr>
          <th>序号</th>
          <th>学期</th>
          <th>课程编号</th>
          <th>课程名称</th>
          <th>考核方式</th>
          <th>开课时间</th>
          <th>学分</th>
        </tr>
        <c:if test="${empty courseList}">
          <tr>
            <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
          </tr>
        </c:if>
        <c:if test="${!empty courseList}">
          <c:forEach var="course" items="${courseList}" varStatus="status">
            <tr>
              <td>${status.index+1}</td>
              <td>${course.semester}</td>
              <td>${course.code}</td>
              <td>${course.name}</td>
              <td>${course.type}</td>
              <td>${course.course_date}</td>
              <td>${course.score}</td>
            </tr>
          </c:forEach>
        </c:if>
      </table>
        </div>
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
          <c:if test="${dataChange.state == '0' && sessionScope.type eq '1'}">
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