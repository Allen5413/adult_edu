<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">待审核通知信息</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageNotify/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <input type="hidden" name="stateFlag" value="${param.stateFlag}" />
      <li>
        <span class="itg">编辑时间：</span>
        <span class="inline-select">
          <input type="text" name="operateDate" style="height: 24px;" onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" value="${param.operateDate}" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">通知类型：</span>
        <span class="inline-select">
          <select name="type">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.type eq '0'}">selected="selected" </c:if>>学习信息</option>
            <option value="1" <c:if test="${param.type eq '1'}">selected="selected" </c:if>>系统消息</option>
            <option value="2" <c:if test="${param.type eq '2'}">selected="selected" </c:if>>考试通知</option>
            <option value="3" <c:if test="${param.type eq '3'}">selected="selected" </c:if>>缴费提醒</option>
            <option value="4" <c:if test="${param.type eq '4'}">selected="selected" </c:if>>更正</option>
            <option value="5" <c:if test="${param.type eq '5'}">selected="selected" </c:if>>普通</option>
          </select>
        </span>
        <br />
        <span class="itg">高校：</span>
        <span class="inline-select">
          <select id="schoolId" name="schoolId" class="select-140" onchange="selectSchool(this)">
            <option value="">请选择</option>
            <c:forEach var="school" items="${schoolList}">
              <option value="${school.id}">${school.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">专业：</span>
        <span class="inline-select">
          <select id="specId" name="specId" class="select-140" onchange="selectSpec(this)">
            <option value="">请选择</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">批次：</span>
        <span class="inline-select">
          <select id="teachPlanId" name="teachPlanId" class="select-140">
            <option value="">请选择</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">缴费状态：</span>
        <span class="inline-select">
          <select name="feeState">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.feeState eq '0'}">selected="selected" </c:if>>未缴费</option>
            <option value="1" <c:if test="${param.feeState eq '1'}">selected="selected" </c:if>>未缴完</option>
            <option value="2" <c:if test="${param.feeState eq '2'}">selected="selected" </c:if>>已结清</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">学籍状态：</span>
        <span class="inline-select">
          <select name="studyState">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.studyState eq '0'}">selected="selected" </c:if>>在籍</option>
            <option value="1" <c:if test="${param.studyState eq '1'}">selected="selected" </c:if>>休学</option>
            <option value="2" <c:if test="${param.studyState eq '2'}">selected="selected" </c:if>>退学</option>
            <option value="3" <c:if test="${param.studyState eq '3'}">selected="selected" </c:if>>毕业</option>
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
      <a href="#">通知公告列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>标题</th>
            <th>类型</th>
            <th>接收对象</th>
            <th>编辑人</th>
            <th>编辑时间</th>
            <th>状态</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="notify" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${notify.title}</td>
                <td>
                  <c:if test="${'0' eq notify.type}">学习信息</c:if>
                  <c:if test="${'1' eq notify.type}">系统消息</c:if>
                  <c:if test="${'2' eq notify.type}">考试通知</c:if>
                  <c:if test="${'3' eq notify.type}">缴费提醒</c:if>
                  <c:if test="${'4' eq notify.type}">更正</c:if>
                  <c:if test="${'5' eq notify.type}">普通</c:if>
                </td>
                <td>${notify.objectRemark}</td>
                <td>${notify.operator}</td>
                <td>${notify.operateTime}</td>
                <td>
                  <c:if test="${'0' eq notify.state}">待审核</c:if>
                  <c:if test="${'2' eq notify.state}">未通过</c:if>
                </td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/auditNotify/open.html?id=${notify.id}&reqParams=${reqParams}')">查看</a>
                  <c:if test="${'2' == notify.state}">
                    <a class="btn-opr" href="#" onclick="del(${notify.id});">删除</a>
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
  function selectSchool(obj){
    $("#specId option").remove();
    $("#teachPlanId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findSpecBySchoolId.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var specObj = $("#specId");
            specObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.specList.length; i++){
              var spec = data.specList[i];
              specObj.append($("<option value='"+spec.id+"'>"+spec.name+"</option>"));
            }
            $("#teachPlanId").append($("<option value=''>全部</option>"));
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function selectSpec(obj){
    $("#teachPlanId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findTeachPlanBySchoolIdAndSpecId.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "specId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var tpObj = $("#teachPlanId");
            tpObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.tpList.length; i++){
              var tp = data.tpList[i];
              tpObj.append($("<option value='" + tp.id + "'>" + tp.year + "年" + (tp.term == 0 ? "春季" : "秋季") + "</option>"));
            }
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function del(id){
    app.del("您确认要删除该条通知？", "${pageContext.request.contextPath}/delNotify/del.json", {"id":id}, "${pageContext.request.contextPath}/pageNotify/page.html", ${reqParams})
  }
</script>