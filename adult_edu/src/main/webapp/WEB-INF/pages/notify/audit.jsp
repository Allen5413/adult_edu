<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">待审核通知详情</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" method="post">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">通知标题：</td>
            <td>${notify.title}</td>
          </tr>
          <tr>
            <td class="tag-b">接收对象：</td>
            <td>${notify.objectRemark}</td>
          </tr>
          <tr>
            <td class="tag-b">编辑时间：</td>
            <td>${notify.operateTime}</td>
          </tr>
          <tr>
            <td class="tag-b">通知内容：</td>
            <td>${notify.content}</td>
          </tr>
          <tr>
            <td class="tag-b">拒绝原因：</td>
            <td>
              <c:if test="${'0' eq notify.state && userType == 1}">
                <input type="text" id="refuseRemark" style="width: 400px;" />
              </c:if>
              <c:if test="${'2' eq notify.state || userType == 2}">${notify.refuseRemark}</c:if>
            </td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <c:if test="${'0' eq notify.state && userType == 1}">
                <a class="btn-com" href="#" onclick="pass(${notify.id})">发送</a>
                <a class="btn-com" href="#" onclick="notPass(${notify.id})">拒绝</a>
              </c:if>
              <c:if test="${'2' eq notify.state}">
                <a class="btn-com" href="#" onclick="del(${notify.id})">删除</a>
              </c:if>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function pass(id){
    app.edit("${pageContext.request.contextPath}/auditNotify/pass.json", {"id":id}, "${pageContext.request.contextPath}/pageNotify/page.html", ${reqParams});
  }

  function notPass(id){
    var refuseRemark = $("#refuseRemark").val();
    if("" == refuseRemark){
      app.msg("请输入拒绝原因!", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/auditNotify/not.json", {"id":id,"refuseRemark":refuseRemark}, "${pageContext.request.contextPath}/pageNotify/page.html", ${reqParams});
  }

  function del(id){
    app.del("您确认要删除该条通知？", "${pageContext.request.contextPath}/delNotify/del.json", {"id":id}, "${pageContext.request.contextPath}/pageNotify/page.html", ${reqParams})
  }
</script>
