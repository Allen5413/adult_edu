<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">招生类型管理</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">招生类型列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addRecruitType/open.html');">添加新类型</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>招生类型</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="recruitType" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${recruitType.name}</td>
                <td>${recruitType.cerator}</td>
                <td>${recruitType.createTime}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="del(${recruitType.id});">删除</a>
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
    var isOperateAudit = "${sessionScope.isOperateAudit}";
    if(isOperateAudit == "1"){
      app.openDialog("${pageContext.request.contextPath}/addDataChangeForEditReson/open.html", "变更原因", 480, 200, function(index2){
        if($("#editReson").val() == ""){
          app.msg("请输入变更原因！");
          return false;
        }
        app.confirm("您确定要删除该数据", function(index){
          $.ajax({
            url:"${pageContext.request.contextPath}/delRecruitType.json",
            method : 'POST',
            async:false,
            data:{"id":id, "editReson":$("#editReson").val()},
            success:function(data){
              if(data.state == 0){
                if(data.msg != "" && "undefined" != typeof(data.msg)){
                  app.msg(data.msg, 0);
                }
                layer.close(index);
                layer.close(index2);
                app.clickResources('${pageContext.request.contextPath}/pageRecruitType/page.html');
              }else {
                app.msg(data.msg, 1);
              }
            }
          });
        });
      });
    }else{
      app.confirm("您确定要删除该数据", function(index){
        $.ajax({
          url:"${pageContext.request.contextPath}/delRecruitType.json",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              if(data.msg != "" && "undefined" != typeof(data.msg)){
                app.msg(data.msg, 0);
              }
              layer.close(index);
              app.clickResources('${pageContext.request.contextPath}/pageRecruitType/page.html');
            }else {
              app.msg(data.msg, 1);
            }
          }
        });
      });
    }
  }
</script>