<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">学生数据分析</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">统计结果</a>
    </div>
    <div class="mod-content">
      <div class="data-cols-list">
        <div class="col">
          <div class="tit">
            <p>学生总人数</p><p class="num">${numMap.totalNum}</p>
          </div>
        </div>
        <div class="col">
          <div class="tit">
            <p>在籍学生人数</p><p class="num">${numMap.zdNum}</p>
          </div>
        </div>
        <div class="col">
          <div class="tit">
            <p>毕业学生人数</p><p class="num">${numMap.byNum}</p>
          </div>
        </div>
        <div class="col">
          <div class="tit">
            <p>休学人数</p><p class="num">${numMap.xxNum}</p>
          </div>
        </div>
        <div class="col">
          <div class="tit">
            <p>费用未结清人数</p><p class="num">${numMap.notPayNum+numMap.notCleanNum}</p>
          </div>
        </div>
        <div class="col">
          <div class="tit">
            <p>教务老师人数</p><p class="num">${jwNum}</p>
          </div>
        </div>
        <div class="clear"></div>
      </div>

        <div class="title-select">
          <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/studentStatis/find.html">
            <span class="inline-select">
                <select id="schoolId" name="schoolId" class="select-140" onchange="selectSchool(this)">
                  <option value="">选择高校</option>
                  <c:forEach var="school" items="${schoolList}">
                    <option value="${school.id}">${school.name}</option>
                  </c:forEach>
                </select>
            </span>
            <span class="inline-select">
                <select id="recruitTypeId" name="recruitTypeId" class="select-140" onchange="selectType(this)">
                  <option value="">招生类型</option>
                </select>
            </span>
            <span class="inline-select">
                <select id="levelId" name="levelId" class="select-140" onchange="selectLevel(this)">
                  <option value="">选择层次</option>
                </select>
            </span>
            <span class="inline-select">
                <select id="specId" name="specId" class="select-140" onchange="selectSpec(this)">
                  <option value="">选择专业</option>
                </select>
            </span>
            <span class="inline-select">
                <select id="teachPlanId" name="teachPlanId" class="select-140">
                  <option value="">选择批次</option>
                </select>
            </span>
            <span class="inline-select">
                <select id="userId" name="userId" class="select-140">
                  <option value="">选择分销商</option>
                  <option value="-1">本部</option>
                  <c:forEach var="user" items="${userList}">
                    <option value="${user.id}">${user.name}</option>
                  </c:forEach>
                </select>
            </span>
            <span class="inline-select">
                <select id="state" name="state" class="select-140">
                  <option value="">学籍状态</option>
                  <option value="0">在籍</option>
                  <option value="1">休学</option>
                  <option value="2">退学</option>
                  <option value="3">毕业</option>
                </select>
            </span>
            <span class="inline-select">
                <select id="userId2" name="userId2" class="select-140">
                  <option value="">教务老师</option>
                  <c:forEach var="user" items="${jwUserList}">
                    <option value="${user.id}">${user.name}</option>
                  </c:forEach>
                </select>
            </span>
            <span class="inline-select">
                <select id="feeState" name="feeState" class="select-140">
                  <option value="">缴费状态</option>
                  <option value="0">未缴费</option>
                  <option value="1">未缴完</option>
                  <option value="2">已结清</option>
                </select>
            </span>
            <a class="btn-com-bl f-r" href="#" onclick="downXls()">下载报表</a><a id="searchBtn" class="btn-com f-r" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查询报表</a>
          </form>
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
              <c:if test="${empty pageInfo.pageResults}">
                <tr>
                  <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
                </tr>
              </c:if>
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
                <%@ include file="../../common/page.jsp"%>
              </c:if>
            </table>
          </div>
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
            typeObj.append($("<option value=''>招生类型</option>"));
            for(var i=0; i<data.typeList.length; i++){
              var type = data.typeList[i];
              typeObj.append($("<option value='"+type.id+"'>"+type.name+"</option>"));
            }
            $("#levelId").append($("<option value=''>选择层次</option>"));
            $("#specId").append($("<option value=''>选择专业</option>"));
            $("#teachPlanId").append($("<option value=''>选择批次</option>"));
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
            levelObj.append($("<option value=''>选择层次</option>"));
            for(var i=0; i<data.levelList.length; i++){
              var level = data.levelList[i];
              levelObj.append($("<option value='" + level.id + "'>" + level.name + "</option>"));
            }
            $("#specId").append($("<option value=''>选择专业</option>"));
            $("#teachPlanId").append($("<option value=''>选择批次</option>"));
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
            specObj.append($("<option value=''>选择专业</option>"));
            for(var i=0; i<data.specList.length; i++){
              var spec = data.specList[i];
              specObj.append($("<option value='" + spec.id + "'>["+spec.code+"]" + spec.name + "</option>"));
            }
            $("#teachPlanId").append($("<option value=''>选择批次</option>"));
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
            tpObj.append($("<option value=''>选择批次</option>"));
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

  function downXls(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStudent/down.html",
      data:$("#pageForm").serialize(),
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>