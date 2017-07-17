<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加费用类型</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/editFeeType/editor.json" method="post">
        <input type="hidden" name="id" value="${feeType.id}"/>
        <input type="hidden" name="centerId" value="${feeType.centerId}"/>
        <table class="set-table-info">
          <tr>
            <td class="tag-b">费用类型：</td>
            <td><input type="text" id="name" name="name" value="${feeType.name}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">费用金额：</td>
            <td><input type="text" id="money" name="money" value="${feeType.feeStr}" class="input-txt-220" placeholder="单位：元"  /></td>
          </tr>
          <tr>
            <td class="tag-b">高校：</td>
            <td>
              <select id="schoolId" name="schoolId" onchange="selectSchool(this)">
                <option value="">全部</option>
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}" <c:if test="${school.id == feeType.schoolId}">selected="selected"</c:if> >${school.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>
              <select id="typeId" name="typeId" onchange="selectType(this)">
                <option value="">全部</option>
                <c:forEach var="type" items="${typeList}">
                  <option value="${type.id}" <c:if test="${type.id == feeType.typeId}">selected="selected"</c:if> >${type.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">批次：</td>
            <td>
              <select id="batch" name="batch">
                <option value="2017_1" <c:if test="${feeType.year == 2017 && feeType.term == 1}">selected="selected"</c:if> >2017年秋季</option>
                <option value="2018_0" <c:if test="${feeType.year == 2018 && feeType.term == 0}">selected="selected"</c:if> >2018年春季</option>
                <option value="2018_1" <c:if test="${feeType.year == 2018 && feeType.term == 1}">selected="selected"</c:if> >2018年秋季</option>
                <option value="2019_0" <c:if test="${feeType.year == 2019 && feeType.term == 0}">selected="selected"</c:if> >2019年春季</option>
                <option value="2019_1" <c:if test="${feeType.year == 2019 && feeType.term == 1}">selected="selected"</c:if> >2019年秋季</option>
                <option value="2020_0" <c:if test="${feeType.year == 2020 && feeType.term == 0}">selected="selected"</c:if> >2020年春季</option>
                <option value="2020_1" <c:if test="${feeType.year == 2020 && feeType.term == 1}">selected="selected"</c:if> >2020年秋季</option>
                <option value="2021_0" <c:if test="${feeType.year == 2021 && feeType.term == 0}">selected="selected"</c:if> >2021年春季</option>
                <option value="2021_1" <c:if test="${feeType.year == 2021 && feeType.term == 1}">selected="selected"</c:if> >2021年秋季</option>
                <option value="2022_0" <c:if test="${feeType.year == 2022 && feeType.term == 0}">selected="selected"</c:if> >2022年春季</option>
                <option value="2022_1" <c:if test="${feeType.year == 2022 && feeType.term == 1}">selected="selected"</c:if> >2022年秋季</option>
                <option value="2023_0" <c:if test="${feeType.year == 2023 && feeType.term == 0}">selected="selected"</c:if> >2023年春季</option>
                <option value="2023_1" <c:if test="${feeType.year == 2023 && feeType.term == 1}">selected="selected"</c:if> >2023年秋季</option>
                <option value="2024_0" <c:if test="${feeType.year == 2024 && feeType.term == 0}">selected="selected"</c:if> >2024年春季</option>
                <option value="2024_1" <c:if test="${feeType.year == 2024 && feeType.term == 1}">selected="selected"</c:if> >2024年秋季</option>
                <option value="2025_0" <c:if test="${feeType.year == 2025 && feeType.term == 0}">selected="selected"</c:if> >2025年春季</option>
                <option value="2025_1" <c:if test="${feeType.year == 2025 && feeType.term == 1}">selected="selected"</c:if> >2025年秋季</option>
                <option value="2026_0" <c:if test="${feeType.year == 2026 && feeType.term == 0}">selected="selected"</c:if> >2026年春季</option>
                <option value="2026_1" <c:if test="${feeType.year == 2026 && feeType.term == 1}">selected="selected"</c:if> >2026年秋季</option>
                <option value="2027_0" <c:if test="${feeType.year == 2027 && feeType.term == 0}">selected="selected"</c:if> >2027年春季</option>
                <option value="2027_1" <c:if test="${feeType.year == 2027 && feeType.term == 1}">selected="selected"</c:if> >2027年秋季</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>
              <select id="levelId" name="levelId">
                <option value="">全部</option>
                <c:forEach var="level" items="${levelList}">
                  <option value="${level.id}" <c:if test="${level.id == feeType.levelId}">selected="selected"</c:if> >${level.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <c:if test="${sessionScope.isOperateAudit == 1}">
            <tr>
              <td class="tag-b">变更原因：</td>
              <td><input type="text" id="editReson" name="editReson" class="input-txt-220" /></td>
            </tr>
          </c:if>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="edit()">保存提交</a>
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
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function selectType(obj){
    $("#levelId option").remove();
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
          }else {
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function edit(){
    if($("#name").val() == ""){
      app.alert("请输入费用类型！", 1);
      return false;
    }
    if(!vaildApp.vaildMoney($("#money").val())){
      app.alert("请输入正确的费用金额！", 1);
      return false;
    }
    if($("#schoolId").val() == ""){
      app.alert("请选择高校！", 1);
      return false;
    }
    if($("#typeId").val() == ""){
      app.alert("请选择招生类型！", 1);
      return false;
    }
    if($("#levelId").val() == ""){
      app.alert("请选择层次！", 1);
      return false;
    }
    if($("#editReson").val() == ""){
      app.alert("请输入变更原因！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/editFeeType/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageFeeType/page.html", ${reqParams});
  }
</script>
