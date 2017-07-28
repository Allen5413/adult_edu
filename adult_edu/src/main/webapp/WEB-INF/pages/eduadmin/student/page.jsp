<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">学生管理</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageStudent/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">高校：</span>
        <span class="inline-select">
          <select id="schoolId" name="schoolId" class="select-140" onchange="selectSchool(this)">
            <option value="">请选择</option>
            <c:forEach var="school" items="${schoolList}">
              <option value="${school.id}">${school.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">报考类型：</span>
        <span class="inline-select">
          <select id="recruitTypeId" name="recruitTypeId" class="select-140" onchange="selectType(this)">
            <option value="">请选择</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">层次：</span>
        <span class="inline-select">
          <select id="levelId" name="levelId" class="select-140" onchange="selectLevel(this)">
            <option value="">请选择</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">专业：</span>
        <span class="inline-select">
          <select id="specId" name="specId" class="select-140" onchange="selectSpec(this)">
            <option value="">请选择</option>
          </select>
        </span><p />
        <span class="itg">批次：</span>
        <span class="inline-select">
          <select id="teachPlanId" name="teachPlanId" class="select-140">
            <option value="">请选择</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
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
      <a href="#">学生列表</a>
      <a class="btn-com f-r" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/addRecruitType/open.html');">导入学生</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>学号</th>
            <th>高校</th>
            <th>报考类型</th>
            <th>层次</th>
            <th>专业</th>
            <th>批次</th>
            <th>学籍状态</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="student" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${student.name}</td>
                <td>${student.sex == 0 ? "男":"女"}</td>
                <td>${student.code}</td>
                <td>${student.scName}</td>
                <td>${student.rtName}</td>
                <td>${student.lName}</td>
                <td>${student.spName}</td>
                <td>${student.year}年${student.term == 0 ? "春季":"秋季"}</td>
                <td>
                  <c:if test="${student.state == 0}">在籍</c:if>
                  <c:if test="${student.state == 1}">休学</c:if>
                  <c:if test="${student.state == 2}">退学</c:if>
                  <c:if test="${student.state == 3}">毕业</c:if>
                </td>
                <td>
                  <a class="btn-opr" href="#" onclick="edit(${student.id});">编辑</a>
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
  function selectSchool(obj){
    $("#recruitTypeId option").remove();
    $("#levelId option").remove();
    $("#specId option").remove();
    $("#teachPlanId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findRecruitTypeBySchoolIdForTP.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var typeObj = $("#recruitTypeId");
            typeObj.append($("<option value=''>请选择</option>"));
            for(var i=0; i<data.typeList.length; i++){
              var type = data.typeList[i];
              typeObj.append($("<option value='"+type.id+"'>"+type.name+"</option>"));
            }
            $("#levelId").append($("<option value=''>请选择</option>"));
            $("#specId").append($("<option value=''>请选择</option>"));
            $("#teachPlanId").append($("<option value=''>请选择</option>"));
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function selectType(obj){
    $("#levelId option").remove();
    $("#specId option").remove();
    $("#teachPlanId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findLevelBySchoolIdAndTypeIdForTP.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "typeId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var levelObj = $("#levelId");
            levelObj.append($("<option value=''>请选择</option>"));
            for(var i=0; i<data.levelList.length; i++){
              var level = data.levelList[i];
              levelObj.append($("<option value='" + level.id + "'>" + level.name + "</option>"));
            }
            $("#specId").append($("<option value=''>请选择</option>"));
            $("#teachPlanId").append($("<option value=''>请选择</option>"));
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function selectLevel(obj){
    $("#specId option").remove();
    $("#teachPlanId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelIdForTP.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "typeId":$("#recruitTypeId").val(), "levelId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var specObj = $("#specId");
            specObj.append($("<option value=''>请选择</option>"));
            for(var i=0; i<data.specList.length; i++){
              var spec = data.specList[i];
              specObj.append($("<option value='" + spec.id + "'>["+spec.code+"]" + spec.name + "</option>"));
            }
            $("#teachPlanId").append($("<option value=''>请选择</option>"));
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
        url:"${pageContext.request.contextPath}/findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecId.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "typeId":$("#recruitTypeId").val(), "levelId":$("#levelId").val(), "specId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var tpObj = $("#teachPlanId");
            tpObj.append($("<option value=''>请选择</option>"));
            for(var i=0; i<data.tpList.length; i++){
              var tp = data.tpList[i];
              tpObj.append($("<option value='" + tp.id + "'>"+tp.year+(tp.term == 0 ? "春季":"秋季") + "</option>"));
            }
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }
</script>