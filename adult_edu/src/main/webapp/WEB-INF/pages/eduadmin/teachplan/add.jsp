<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加教学计划</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="importForm" name="importForm" action="${pageContext.request.contextPath}/addTeachPlan/add.json" enctype="multipart/form-data" method="post">
        <input type="hidden" id="beginDate" name="beginDate" />
        <input type="hidden" id="endDate" name="endDate" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>
              <select id="schoolId" name="schoolId" onchange="selectSchool(this)">
                <option value="">全部</option>
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}" <c:if test="${param.schoolId == school.id}">selected="selected"</c:if>>${school.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>
              <select id="typeId" name="typeId" onchange="selectType(this)">
                <option value="">全部</option>
                <c:if test="${!empty typeList}">
                  <c:forEach var="type" items="${typeList}">
                    <option value="${type.id}" <c:if test="${param.typeId == type.id}">selected="selected"</c:if>>${type.name}</option>
                  </c:forEach>
                </c:if>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>
              <select id="levelId" name="levelId" onchange="selectLevel(this)">
                <option value="">全部</option>
                <c:if test="${!empty levelList}">
                  <c:forEach var="level" items="${levelList}">
                    <option value="${level.id}" <c:if test="${param.levelId == level.id}">selected="selected"</c:if>>${level.name}</option>
                  </c:forEach>
                </c:if>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">专业：</td>
            <td>
              <select id="specId" name="specId">
                <option value="">全部</option>
                <c:if test="${!empty specList}">
                  <c:forEach var="spec" items="${specList}">
                    <option value="${spec.sId}" <c:if test="${param.specId == spec.sId}">selected="selected"</c:if>>[${spec.code}]${spec.name}</option>
                  </c:forEach>
                </c:if>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">开课年：</td>
            <td>
              <select id="year" name="year">
                <option value="2017" <c:if test="${param.year eq '2017'}">selected="selected"</c:if>>2017</option>
                <option value="2017" <c:if test="${param.year eq '2018'}">selected="selected"</c:if>>2018</option>
                <option value="2019" <c:if test="${param.year eq '2019'}">selected="selected"</c:if>>2019</option>
                <option value="2020" <c:if test="${param.year eq '2020'}">selected="selected"</c:if>>2020</option>
                <option value="2021" <c:if test="${param.year eq '2021'}">selected="selected"</c:if>>2021</option>
                <option value="2022" <c:if test="${param.year eq '2022'}">selected="selected"</c:if>>2022</option>
                <option value="2023" <c:if test="${param.year eq '2023'}">selected="selected"</c:if>>2023</option>
                <option value="2024" <c:if test="${param.year eq '2024'}">selected="selected"</c:if>>2024</option>
                <option value="2025" <c:if test="${param.year eq '2025'}">selected="selected"</c:if>>2025</option>
                <option value="2026" <c:if test="${param.year eq '2026'}">selected="selected"</c:if>>2026</option>
                <option value="2027" <c:if test="${param.year eq '2027'}">selected="selected"</c:if>>2027</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">开课季：</td>
            <td>
              <select id="term" name="term">
                <option value="0" <c:if test="${param.term eq '0'}">selected="selected"</c:if> >春季</option>
                <option value="1" <c:if test="${param.term eq '1'}">selected="selected"</c:if> >秋季</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">报名时间：</td>
            <td>
              <input type="text" id="date" name="date" style="height: 24px;" onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" value="${param.date}" /> 到
              <input type="text" id="date2" name="date2" style="height: 24px;" onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" value="${param.date2}" />
            </td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <table>
                  <td>
                    <a class="btn-com-upload" href="#"><input type="file" id="file" name="file" class="uploadfile" onchange="addImport()" />导入课程表</a>
                  </td>
                  <td>
                    <a class="btn-com-upload" href="${pageContext.request.contextPath}/template/teachPlan.xlsx">课程表模板下载</a>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function selectSchool(obj){
    $("#typeId option").remove();
    $("#levelId option").remove();
    $("#specId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findRecruitTypeBySchoolId.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var typeObj = $("#typeId");
            typeObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.typeList.length; i++){
              var type = data.typeList[i];
              typeObj.append($("<option value='"+type.id+"'>"+type.name+"</option>"));
            }
            $("#levelId").append($("<option value=''>全部</option>"));
            $("#specId").append($("<option value=''>全部</option>"));
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
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findLevelBySchoolIdAndTypeId.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "typeId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var levelObj = $("#levelId");
            levelObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.levelList.length; i++){
              var level = data.levelList[i];
              levelObj.append($("<option value='" + level.id + "'>" + level.name + "</option>"));
            }
            $("#specId").append($("<option value=''>全部</option>"));
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function selectLevel(obj){
    $("#specId option").remove();
    if($(obj).val() != ""){
      $.ajax({
        url:"${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/findForJSON.json",
        method : 'POST',
        async:false,
        data:{"schoolId":$("#schoolId").val(), "typeId":$("#typeId").val(), "levelId":$(obj).val()},
        success:function(data){
          if(data.state == 0){
            var specObj = $("#specId");
            specObj.append($("<option value=''>全部</option>"));
            for(var i=0; i<data.specList.length; i++){
              var spec = data.specList[i];
              specObj.append($("<option value='" + spec.sId + "'>["+spec.code+"]" + spec.name + "</option>"));
            }
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function addImport(){
    var schoolId = $("#schoolId").val();
    var typeId = $("#typeId").val();
    var levelId = $("#levelId").val();
    var specId = $("#specId").val();
    var year = $("#year").val();
    var term = $("#term").val();
    var date = $("#date").val();
    var date2 = $("#date2").val();
    if(schoolId == ""){
      app.msg("请选择高校", 1);
      $("#file").val("");
      return false;
    }
    if(typeId == ""){
      app.msg("请选择招生类型", 1);
      $("#file").val("");
      return false;
    }
    if(levelId == ""){
      app.msg("请选择层次", 1);
      $("#file").val("");
      return false;
    }
    if(specId == ""){
      app.msg("请选择专业", 1);
      $("#file").val("");
      return false;
    }
    if(date == ""){
      app.msg("请选择报名开始时间", 1);
      $("#file").val("");
      return false;
    }
    if(date2 == ""){
      app.msg("请选择报名截止时间", 1);
      $("#file").val("");
      return false;
    }
    $("#beginDate").val($("#date").val()+" 00:00:00");
    $("#endDate").val($("#date2").val()+" 23:59:59");
    $("#importForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/addTeachPlan/add.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          app.msg("导入成功", 0);
          app.clickResources("${pageContext.request.contextPath}/pageTeachPlan/page.html", ${reqParams});
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
          var param = {"reqParams": "${reqParams}", "schoolId": schoolId, "typeId": typeId, "levelId": levelId, "specId": specId, "year": year, "term": term, "date":date, "date2": date2};
          app.clickResources('${pageContext.request.contextPath}/addTeachPlan/open.html', param);
        }
      }
    });
  }
</script>
