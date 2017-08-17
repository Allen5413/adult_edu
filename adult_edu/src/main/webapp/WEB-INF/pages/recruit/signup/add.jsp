<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">报名信息录入</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addSignUp/add.json">
        <table class="info-table-input">
          <tr>
            <td class="tag-b">学校：</td>
            <td>
              <select id="schoolId" name="schoolId" class="select-140" onchange="selectSchool(this)">
                <option value="">请选择</option>
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}">${school.name}</option>
                </c:forEach>
              </select><span style="color: #ff0000">*</span>
            </td>
            <td class="tag-b">报考类型：</td>
            <td>
              <select id="recruitTypeId" name="recruitTypeId" class="select-140" onchange="selectType(this)">
                <option value="">请选择</option>
              </select><span style="color: #ff0000">*</span>
            </td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>
              <select id="levelId" name="levelId" class="select-140" onchange="selectLevel(this)">
                <option value="">请选择</option>
              </select><span style="color: #ff0000">*</span>
            </td>
            <td class="tag-b">专业：</td>
            <td>
              <select id="specId" name="specId" class="select-140" onchange="selectSpec(this)">
                <option value="">请选择</option>
              </select><span style="color: #ff0000">*</span>
            </td>
          </tr>
          <tr>
            <td class="tag-b">批次：</td>
            <td>
              <select id="teachPlanId" name="teachPlanId" class="select-140">
                <option value="">请选择</option>
              </select><span style="color: #ff0000">*</span>
            </td>
            <td class="tag-b" >姓名：</td>
            <td><input type="text" id="name" name="name" class="input-txt-200" /><span style="color: #ff0000">*</span></td>
          </tr>
          <tr>
            <td class="tag-b" >性别：</td>
            <td>
              <input type="radio" name="sex" value="0" checked>&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="radio" name="sex" value="1">&nbsp;&nbsp;女
            </td>
            <td class="tag-b">身份证号：</td>
            <td><input type="text" id="idCard" name="idCard" class="input-txt-200" /><span style="color: #ff0000">*</span></td>
          </tr>
          <tr>
            <td class="tag-b" >手机：</td>
            <td><input type="text" id="phone" name="phone" class="input-txt-200" /><span style="color: #ff0000">*</span></td>
            <td class="tag-b">QQ：</td>
            <td><input type="text" id="qq" name="qq" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b">学习方式：</td>
            <td >
              <select id="studyType" name="studyType" class="select-140">
                <option value="">请选择</option>
                <option value="0">脱产</option>
                <option value="1">业余</option>
                <option value="2">函授</option>
                <option value="3">远程教育</option>
              </select>
            </td>
            <td class="tag-b" >家庭住址：</td>
            <td><input type="text" id="address" name="address" class="input-txt-200" /><span style="color: #ff0000">*</span></td>

          </tr>
          <tr>
            <td class="tag-b">电子邮箱：</td>
            <td ><input type="text" name="email" class="input-txt-200" /></td>
            <td class="tag-b">邮政编码：</td>
            <td ><input type="text" name="zipCode" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b" >生源备注：</td>
            <td colspan="3"><textarea id="sourceRemark" name="sourceRemark" class="textarea-intro"></textarea><span style="color: #ff0000">*</span></td>
          </tr>
          <tr>
            <td class="tag-b" >入学前学历：</td>
            <td><input type="text" name="beforeEdu" class="input-txt-200" /></td>
            <td class="tag-b">毕（肄）业时间：</td>
            <td ><input type="text" name="beforeGraduationDate" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b" >毕（肄）业学校：</td>
            <td><input type="text" name="beforeSchool" class="input-txt-200" /></td>
            <td class="tag-b">毕业证书编号：</td>
            <td ><input type="text" name="beforeCode" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b" >党团员否：</td>
            <td><input type="text" name="party" class="input-txt-200" /></td>
            <td class="tag-b">入党团年月：</td>
            <td ><input type="text" name="partyDate" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b" >个人简历</td>
            <td colspan="4">
              <table class="in-table">
                <tr>
                  <th>起止时间</th>
                  <th>学校或工作单位</th>
                  <th>地址</th>
                  <th>职务</th>
                  <th>离开原因</th>
                </tr>
                <tr>
                  <td><input type="text" name="resumeDate" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeCompany" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeAddress" class="input-txt-120" /></td>
                  <td><input type="text" name="resumePost" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeLeaveReason" class="input-txt-120" /></td>
                </tr>
                <tr>
                  <td><input type="text" name="resumeDate2" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeCompany2" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeAddress2" class="input-txt-120" /></td>
                  <td><input type="text" name="resumePost2" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeLeaveReason2" class="input-txt-120" /></td>
                </tr>
                <tr>
                  <td><input type="text" name="resumeDate3" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeCompany3" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeAddress3" class="input-txt-120" /></td>
                  <td><input type="text" name="resumePost3" class="input-txt-120" /></td>
                  <td><input type="text" name="resumeLeaveReason3" class="input-txt-120" /></td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="tag-b" >家庭成员</td>
            <td colspan="4">
              <table class="in-table">
                <tr>
                  <th>姓名</th>
                  <th>称谓</th>
                  <th>年龄</th>
                  <th>政治面貌</th>
                  <th>工作单位</th>
                  <th>职务或工种</th>
                </tr>
                <tr>
                  <td><input type="text" name="familyName" class="input-txt-120" /></td>
                  <td><input type="text" name="familyRelation" class="input-txt-120" /></td>
                  <td><input type="text" name="familyAge" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPolitical" class="input-txt-120" /></td>
                  <td><input type="text" name="familyCompany" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPost" class="input-txt-120" /></td>
                </tr>
                <tr>
                  <td><input type="text" name="familyName2" class="input-txt-120" /></td>
                  <td><input type="text" name="familyRelation2" class="input-txt-120" /></td>
                  <td><input type="text" name="familyAge2" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPolitical2" class="input-txt-120" /></td>
                  <td><input type="text" name="familyCompany2" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPost2" class="input-txt-120" /></td>
                </tr>
                <tr>
                  <td><input type="text" name="familyName3" class="input-txt-120" /></td>
                  <td><input type="text" name="familyRelation3" class="input-txt-120" /></td>
                  <td><input type="text" name="familyAge3" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPolitical3" class="input-txt-120" /></td>
                  <td><input type="text" name="familyCompany3" class="input-txt-120" /></td>
                  <td><input type="text" name="familyPost3" class="input-txt-120" /></td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="tag-b" >何时何地曾获过何种奖励：</td>
            <td colspan="4"><textarea name="reward" class="textarea-intro"></textarea></td>
          </tr>
        </table>
        <input type="hidden" id="photoUrl" name="photoUrl" />
        <input type="hidden" id="idCardFrontUrl" name="idCardFrontUrl" />
        <input type="hidden" id="idCardBackUrl" name="idCardBackUrl" />
        <input type="hidden" id="diplomaUrl" name="diplomaUrl" />
        <input type="hidden" id="xxwUrl" name="xxwUrl" />
        <input type="hidden" id="ydsUrl" name="ydsUrl" />
      </form>
      <table class="info-table-input" style="width: 80%">
        <tr>
          <td style="text-align: center">
            <form id="photoForm" name="photoForm" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo" name="idCard">
              <img id="photoImg" style="display: none; width: 400px; height: 280px;" /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile" name="img" class="uploadfile" onchange="upImg(1);" />上传照片</a><span style="color: #ff0000">*</span>
            </form>
          </td>
          <td>
            <form id="photoForm2" name="photoForm2" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo2" name="idCard">
              <img id="photoImg2" style="display: none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile2" name="img" class="uploadfile" onchange="upImg(2);" />上传身份证正面</a><span style="color: #ff0000">*</span>
            </form>
          </td>
        </tr>
        <tr>
          <td style="text-align: center">
            <form id="photoForm3" name="photoForm3" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo3" name="idCard">
              <img id="photoImg3" style="display: none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile3" name="img" class="uploadfile" onchange="upImg(3);" />上传身份证背面</a><span style="color: #ff0000">*</span>
            </form>
          </td>
          <td>
            <form id="photoForm4" name="photoForm4" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo4" name="idCard">
              <img id="photoImg4" style="display: none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile4" name="img" class="uploadfile" onchange="upImg(4);" />上传学历证书</a><span style="color: #ff0000">*</span>
            </form>
          </td>
        </tr>
        <tr>
          <td style="text-align: center">
            <form id="photoForm5" name="photoForm5" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo5" name="idCard">
              <img id="photoImg5" style="display: none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile5" name="img" class="uploadfile" onchange="upImg(5);" />上传学信网认证</a>
            </form>
          </td>
          <td>
            <form id="photoForm6" name="photoFor6m" action="${pageContext.request.contextPath}/uploadPhoto.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo6" name="idCard">
              <img id="photoImg6" style="display: none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile6" name="img" class="uploadfile" onchange="upImg(6);" />上传异地生证明</a>
            </form>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <a class="btn-com" href="#" onclick="add()">保存提交</a>
          </td>
        </tr>
      </table>
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

  function upImg(flag){
    var formId = "";
    var url = "";
    var imgId = "";
    var setValueId = "";
    var idCard = $("#idCard").val().trim();
    if(idCard == ""){
      app.msg("请先输入身份证号！")
      return false;
    }

    if(1 == flag){
      formId = "photoForm";
      url = "/uploadPhoto.json"
      imgId = "photoImg";
      setValueId = "photoUrl";
      $("#idCard_photo").val(idCard);
    }
    if(2 == flag){
      formId = "photoForm2";
      url = "/uploadIdCardFront.json"
      imgId = "photoImg2";
      setValueId = "idCardFrontUrl";
      $("#idCard_photo2").val(idCard);
    }
    if(3 == flag){
      formId = "photoForm3";
      url = "/uploadIdCardBack.json"
      imgId = "photoImg3";
      setValueId = "idCardBackUrl";
      $("#idCard_photo3").val(idCard);
    }
    if(4 == flag){
      formId = "photoForm4";
      url = "/uploadDiploma.json"
      imgId = "photoImg4";
      setValueId = "diplomaUrl";
      $("#idCard_photo4").val(idCard);
    }
    if(5 == flag){
      formId = "photoForm5";
      url = "/uploadXxw.json"
      imgId = "photoImg5";
      setValueId = "xxwUrl";
      $("#idCard_photo5").val(idCard);
    }
    if(6 == flag){
      formId = "photoForm6";
      url = "/uploadYds.json"
      imgId = "photoImg6";
      setValueId = "ydsUrl";
      $("#idCard_photo6").val(idCard);
    }
    $("#"+formId).ajaxSubmit({
      url : "${pageContext.request.contextPath}"+url,
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          $("#" + imgId).attr('src', data.url);
          $("#" + imgId).show();
          $("#"+setValueId).val(data.url);
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function add(){
    if($("#schoolId").val() == ""){
      app.alert("请选择学校！", 1);
      return false;
    }
    if($("#recruitTypeId").val() == ""){
      app.alert("请选择报考类型！", 1);
      return false;
    }
    if($("#levelId").val() == ""){
      app.alert("请选择层次！", 1);
      return false;
    }
    if($("#specId").val() == ""){
      app.alert("请选择专业！", 1);
      return false;
    }
    if($("#teachPlanId").val() == ""){
      app.alert("请选择批次！", 1);
      return false;
    }
    if($("#name").val().trim() == ""){
      app.alert("请输入姓名！", 1);
      return false;
    }
    if($("#idCard").val().trim() == ""){
      app.alert("请输入身份证号！", 1);
      return false;
    }
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    if($("#address").val().trim() == ""){
      app.alert("请输入家庭地址！", 1);
      return false;
    }
    if($("#sourceRemark").val().trim() == ""){
      app.alert("请输入生源备注！", 1);
      return false;
    }
    if($("#photoUrl").val().trim() == ""){
      app.alert("请上传照片！", 1);
      return false;
    }
    if($("#idCardFrontUrl").val().trim() == ""){
      app.alert("请上传身份证正面！", 1);
      return false;
    }
    if($("#idCardBackUrl").val().trim() == ""){
      app.alert("请上传身份证背面！", 1);
      return false;
    }
    if($("#diplomaUrl").val().trim() == ""){
      app.alert("请上传学历证书！", 1);
      return false;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addSignUp/add.json",
      data:$("#form").serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.msg("保存成功！", 0);
          app.clickResources("${pageContext.request.contextPath}/addSignUp/open.html");
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>