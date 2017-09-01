<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">创建新消息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addNotify/add.json" method="post">
        <input type="hidden" id="objectRemark" name="objectRemark" />
        <input type="hidden" id="content" name="content" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">标题：</td>
            <td><input type="text" id="title" name="title" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">消息类型：</td>
            <td>
              <select id="type" name="type">
                <option value="">请选择</option>
                <option value="0">学习信息</option>
                <option value="1">系统消息</option>
                <option value="2">考试通知</option>
                <option value="3">缴费提醒</option>
                <option value="4">更正</option>
                <option value="5">普通</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">发送对象：</td>
            <td>
              <select id="sendObject" name="sendObject" onchange="selectobject(this)">
                <option value="">全部</option>
                <option value="0">学生</option>
                <option value="1">分销商</option>
              </select>
            </td>
          </tr>
          <tr id="schoolTr" style="display: none;">
            <td class="tag-b">高校：</td>
            <td>
              <select id="schoolId" name="schoolId" onchange="selectSchool(this)">
                <option value="">全部</option>
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}">${school.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr id="specTr" style="display: none;">
            <td class="tag-b">专业：</td>
            <td>
              <select id="specId" name="specId" onchange="selectSpec(this)">
                <option value="">全部</option>
              </select>
            </td>
          </tr>
          <tr id="tpTr" style="display: none;">
            <td class="tag-b">批次：</td>
            <td>
              <select id="teachPlanId" name="teachPlanId">
                <option value="">全部</option>
              </select>
            </td>
          </tr>
          <tr id="feeStateTr" style="display: none;">
            <td class="tag-b">缴费状态：</td>
            <td>
              <select id="feeState" name="feeState">
                <option value="">全部</option>
                <option value="0">未缴费</option>
                <option value="1">未缴完</option>
                <option value="2">已缴清</option>
              </select>
            </td>
          </tr>
          <tr id="studyStateTr" style="display: none;">
            <td class="tag-b">学籍状态：</td>
            <td>
              <select id="studyState" name="studyState">
                <option value="">全部</option>
                <option value="0">在籍</option>
                <option value="1">休学</option>
                <option value="2">退学</option>
                <option value="3">毕业</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">消息内容：</td>
            <td>
              <!-- 加载编辑器的容器 -->
              <script id="contentUE" name="contentUE" type="text/plain"></script>
              <!-- 配置文件 -->
              <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor1_4_3_3-utf8-jsp/ueditor.config.js"></script>
              <!-- 编辑器源码文件 -->
              <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor1_4_3_3-utf8-jsp/ueditor.all.js"></script>
              <!-- 实例化编辑器 -->
              <script type="text/javascript">
                var ue = UE.getEditor('contentUE');
              </script>
            </td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="add()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function selectobject(obj){
    var objectVal = $(obj).val();
    if(objectVal == "" || objectVal == 1){
      $("#schoolTr, #specTr, #tpTr, #feeStateTr, #studyStateTr").hide();
    }else{
      $("#schoolTr, #specTr, #tpTr, #feeStateTr, #studyStateTr").show();
    }
  }

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
        url:"${pageContext.request.contextPath}/findTeachPlanBySchoolIdAndSpecIdService.json",
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

  function add(){
    if($("#title").val() == ""){
      app.alert("请输入标题！", 1);
      return false;
    }
    if($("#type").val() == ""){
      app.alert("请选择消息类型！", 1);
      return false;
    }
    var sendObject = "";
    var objVal = $("#sendObject").val();
    if(objVal == ""){
      sendObject = "所有学生和分销商";
    }else {
      debugger;
      if (objVal == 0) {
        var school = $("#schoolId").find("option:selected").text();
        var spec = $("#specId").find("option:selected").text();
        var teachPlan = $("#teachPlanId").find("option:selected").text();
        var feeState =  $("#feeState").find("option:selected").text();
        var studyState =  $("#studyState").find("option:selected").text();
        if ("全部" == school && "全部" == spec && "全部" == teachPlan && "全部" == feeState && "全部" == studyState) {
          sendObject = "所有学生";
        } else {
          sendObject = school + ("全部" == spec ? "" : spec) + ("全部" == teachPlan ? "" : teachPlan) + ("全部" == feeState ? "" : feeState) + ("全部" == studyState ? "" : studyState) + "的学生";
        }
      }
      if (objVal == 1) {
        sendObject = "所有分销商";
      }
    }
    if("" == UE.getEditor('contentUE').getContent()){
      app.alert("请输入消息内容！", 1);
      return false;
    }
    $("#objectRemark").val(sendObject);
    $("#content").val(UE.getEditor('contentUE').getContent());
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addNotify/add.json",
      data:$("#form").serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.alert("提交成功！", 0);
          app.clickResources("${pageContext.request.contextPath}/addNotify/open.html");
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>
