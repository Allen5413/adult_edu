<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">教学计划管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageTeachPlan/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">招生类型：</span>
        <span class="inline-select">
          <select name="typeId">
            <option value="">全部</option>
            <c:forEach var="type" items="${typeList}">
              <option value="${type.id}" <c:if test="${param.typeId == type.id}">selected="selected" </c:if> >${type.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">高校：</span>
        <span class="inline-select">
          <select name="schoolId" onchange="findLevel(this);findSpec(this);">
            <option value="">全部</option>
            <c:forEach var="school" items="${schoolList}">
              <option value="${school.id}" <c:if test="${param.schoolId == school.id}">selected="selected" </c:if> >${school.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">层次：</span>
        <span class="inline-select">
          <select id="levelId" name="levelId">
            <option value="">全部</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">专业：</span>
        <span class="inline-select">
          <select id="specId" name="specId">
            <option value="">全部</option>
          </select>
        </span><p />
        <span class="itg">开课年：</span>
        <span class="inline-select">
          <select name="year">
            <option value="">全部</option>
            <option value="2017" <c:if test="${param.year == 2017}">selected="selected" </c:if>>2017</option>
            <option value="2018" <c:if test="${param.year == 2018}">selected="selected" </c:if>>2018</option>
            <option value="2019" <c:if test="${param.year == 2019}">selected="selected" </c:if>>2019</option>
            <option value="2020" <c:if test="${param.year == 2020}">selected="selected" </c:if>>2020</option>
            <option value="2021" <c:if test="${param.year == 2021}">selected="selected" </c:if>>2021</option>
            <option value="2022" <c:if test="${param.year == 2022}">selected="selected" </c:if>>2022</option>
            <option value="2023" <c:if test="${param.year == 2023}">selected="selected" </c:if>>2023</option>
            <option value="2024" <c:if test="${param.year == 2024}">selected="selected" </c:if>>2024</option>
            <option value="2025" <c:if test="${param.year == 2025}">selected="selected" </c:if>>2025</option>
            <option value="2026" <c:if test="${param.year == 2026}">selected="selected" </c:if>>2026</option>
            <option value="2027" <c:if test="${param.year == 2027}">selected="selected" </c:if>>2027</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">开课季：</span>
        <span class="inline-select">
          <select name="term">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.term eq '0'}">selected="selected" </c:if>>春季</option>
            <option value="1" <c:if test="${param.term eq '1'}">selected="selected" </c:if>>秋季</option>
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
      <a href="#">教学计划列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addTeachPlan/open.html?reqParams=${reqParams}');">添加教学计划</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>高校</th>
            <th>招生类型</th>
            <th>层次</th>
            <th>专业</th>
            <th>批次</th>
            <th>报名起止时间</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="teachPlan" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${teachPlan.sName}</td>
                <td>${teachPlan.rtName}</td>
                <td>${teachPlan.lName}</td>
                <td>${teachPlan.spName}</td>
                <td>${teachPlan.year}年<c:if test="${teachPlan.term == 0}">春季</c:if><c:if test="${teachPlan.term == 1}">秋季</c:if></td>
                <td>${fn:substring(teachPlan.begin_date, 0, 11)} 到 ${fn:substring(teachPlan.end_date, 0, 11)}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="searchCourse(${teachPlan.id});">查看课程</a>
                  <a class="btn-opr" href="#" onclick="del(${teachPlan.id});">删除</a>
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
  function findLevel(obj){
    $("#levelId option").remove();
    if("" != $(obj).val()){
      $.ajax({
        url:"${pageContext.request.contextPath}/findLevelBySchoolIdFromStls.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var levelObj = $("#levelId");
            levelObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.levelList.length; i++){
              var level = data.levelList[i];
              levelObj.append($("<option value='"+level.id+"'>"+level.name+"</option>"));
            }
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function findSpec(obj){
    $("#specId option").remove();
    if("" != $(obj).val()){
      $.ajax({
        url:"${pageContext.request.contextPath}/findSpecBySchoolIdFromStls.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var levelObj = $("#specId");
            levelObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.specList.length; i++){
              var spec = data.specList[i];
              levelObj.append($("<option value='"+spec.id+"'>["+spec.code+"]"+spec.name+"</option>"));
            }
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function searchCourse(id){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachPlanCourseByTeachPlanId/find.html?teachPlanId='+id, '查看课程', 1200, 0.8);
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
            url:"${pageContext.request.contextPath}/delTeachPlan.json",
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
                app.clickResources('${pageContext.request.contextPath}/pageTeachPlan/page.html');
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
          url:"${pageContext.request.contextPath}/delTeachPlan.json",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              if(data.msg != "" && "undefined" != typeof(data.msg)){
                app.msg(data.msg, 0);
              }
              layer.close(index);
              app.clickResources('${pageContext.request.contextPath}/pageTeachPlan/page.html');
            }else {
              app.msg(data.msg, 1);
            }
          }
        });
      });
    }
  }
</script>