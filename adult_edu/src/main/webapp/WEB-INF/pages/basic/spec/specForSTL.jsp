<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">专业管理</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">专业列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addSchoolTypeLevelSpec/openFromDetail.html?reqParams=${reqParams}&schoolId=${param.schoolId}&typeId=${param.typeId}&levelId=${param.levelId}');">添加专业</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>专业编码</th>
            <th>专业名称</th>
            <th width="180">操作</th>
          </tr>
          <c:if test="${empty specList}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty specList}">
            <c:forEach var="spec" items="${specList}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${spec.code}</td>
                <td>${spec.name}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editSpec/open.html?id=${spec.sId}&reqParams=${reqParams}');">编辑</a>
                  <a class="btn-opr" href="#" onclick="del(${spec.id})">删除</a>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/findCourseBySTLSId/find.html?stlsId=${spec.id}');">查看课程</a>
                </td>
              </tr>
            </c:forEach>
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
            url:"${pageContext.request.contextPath}/delSchoolTypeLevelSpecById.json",
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
                app.clickResources('${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/find.html?schoolId=${param.sId}&typeId=${param.rtId}&levelId=${param.lId}');
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
          url:"${pageContext.request.contextPath}/delSchoolTypeLevelSpecById.json",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              if(data.msg != "" && "undefined" != typeof(data.msg)){
                app.msg(data.msg, 0);
              }
              layer.close(index);
              app.clickResources('${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/find.html?schoolId=${param.sId}&typeId=${param.rtId}&levelId=${param.lId}');
            }else {
              app.msg(data.msg, 1);
            }
          }
        });
      });
    }
  }
</script>