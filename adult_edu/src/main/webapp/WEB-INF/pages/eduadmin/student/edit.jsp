<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑学生信息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/editStudent/editor.json">
        <input type="hidden" name="id" value="${student.id}" />
        <table class="info-table-input" style="width: 90%">
          <tr>
            <td class="tag-b" colspan="8">个人信息</td>
          </tr>
          <tr>
            <td class="tag-b">姓名：</td>
            <td>${student.name}</td>
            <td class="tag-b">性别：</td>
            <td>${0 == student.sex ? "男" : "女"}</td>
            <td class="tag-b">出生日期：</td>
            <td>${fn:substring(student.birthday, 0, 10)}</td>
            <td rowspan="2" width="120" style="text-align: center;">
              <c:if test="${!empty student.photoUrl}"><img src="${pageContext.request.contextPath}${student.photoUrl}" style="width: 100px; height: 100px;" /></c:if>
              <c:if test="${empty student.photoUrl}"><img src="${pageContext.request.contextPath}/img/zs_uhead.jpg" /></c:if>
            </td>
          </tr>
          <tr>
            <td class="tag-b">身份证号码：</td>
            <td>${student.idCard}</td>
            <td class="tag-b">学号：</td>
            <td>${student.code}</td>
            <td class="tag-b"></td>
            <td></td>
            <td></td>
          </tr>
        </table>
        <table class="info-table-input" style="width: 90%">
          <tr>
            <td class="tag-b" colspan="6">报读信息</td>
          </tr>
          <tr>
            <td class="tag-b">报读学校：</td>
            <td>${school.name}</td>
            <td class="tag-b">报读层次：</td>
            <td>${level.name}</td>
            <td class="tag-b">批次：</td>
            <td>${tp.year}年${tp.term == 0 ? "春季":"秋季"}</td>
          </tr>
          <tr>
            <td class="tag-b">报考类型：</td>
            <td>${type.name}</td>
            <td class="tag-b">报考专业：</td>
            <td>${spec.name}</td>
            <td class="tag-b">报名时间：</td>
            <td>${fn:substring(student.signUpDate,0,10)}</td>
          </tr>
          <tr>
            <td class="tag-b">生源来源：</td>
            <td>
              <c:if test="${student.userId == -1}">本部</c:if>
              <c:if test="${student.userId != -1}">${user.name}</c:if>
            </td>
            <td class="tag-b">学籍状态：</td>
            <td>
              <input type="radio" name="state" value="0" <c:if test="${student.state eq '0'}">checked</c:if>> 在籍
              <input type="radio" name="state" value="1" <c:if test="${student.state eq '1'}">checked</c:if>> 休学
              <input type="radio" name="state" value="2" <c:if test="${student.state eq '2'}">checked</c:if>> 退学
              <input type="radio" name="state" value="3" <c:if test="${student.state eq '3'}">checked</c:if>> 毕业
            </td>
            <td class="tag-b"></td>
            <td></td>
          </tr>
        </table>
        <table class="info-table-input" style="width: 90%">
          <tr>
            <td class="tag-b" colspan="8">通讯信息</td>
          </tr>
          <tr>
            <td class="tag-b" >邮编：</td>
            <td><input type="text" id="zipCode" name="zipCode" value="${student.zipCode}" class="input-txt-200" /></td>
            <td class="tag-b">联系电话：</td>
            <td><input type="text" id="tel" name="tel" value="${student.tel}" class="input-txt-200" /></td>
            <td class="tag-b">手机号码：</td>
            <td><input type="text" id="phone" name="phone" value="${student.phone}" class="input-txt-200" /><span style="color: #ff0000;">*</span> </td>
            <td class="tag-b">电子邮箱：</td>
            <td><input type="text" id="email" name="email" value="${student.email}" class="input-txt-200" /></td>
          </tr>
          <tr>
            <td class="tag-b">通讯地址：</td>
            <td colspan="7"><input type="text" id="address" name="address" value="${student.address}" class="input-txt-200" style="width: 600px;" /></td>
          </tr>
          <c:if test="${sessionScope.isOperateAudit == 1}">
            <tr>
              <td class="tag-b">变更原因：</td>
              <td colspan="7"><textarea id="editReson" name="editReson" class="textarea-intro"></textarea></td>
            </tr>
          </c:if>
        </table>
        <input type="hidden" id="zkzFrontUrl" name="zkzFrontUrl" value="${student.zkzFrontUrl}" />
        <input type="hidden" id="zkzBackUrl" name="zkzBackUrl" value="${student.zkzBackUrl}" />
        <input type="hidden" id="xxwUrl" name="xxwUrl" value="${signUp.xxwUrl}" />
      </form>
      <table class="info-table-input" style="width: 80%">
        <tr>
          <td class="tag-b" >照片：</td>
          <td style="text-align: center">
            <img <c:if test="${!empty student.photoUrl}">src="${student.photoUrl}"</c:if> style="<c:if test="${empty student.photoUrl}">display: none;</c:if> width: 400px; height: 280px;" /><br/>
          </td>
          <td class="tag-b" >身份证正面照：</td>
          <td>
            <img <c:if test="${!empty student.idCardFrontUrl}">src="${student.idCardFrontUrl}"</c:if> style="<c:if test="${empty student.idCardFrontUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >身份证背面照：</td>
          <td style="text-align: center">
            <img <c:if test="${!empty student.idCardBackUrl}">src="${student.idCardBackUrl}"</c:if> style="<c:if test="${empty student.idCardBackUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
          <td class="tag-b" >学历证书：</td>
          <td>
            <img <c:if test="${!empty student.diplomaUrl}">src="${student.diplomaUrl}"</c:if> style="<c:if test="${empty student.diplomaUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >异地生证明：</td>
          <td>
            <img <c:if test="${!empty student.ydsUrl}">src="${student.ydsUrl}"</c:if> style="<c:if test="${empty student.ydsUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
          <td class="tag-b" >学信网认证：</td>
          <td style="text-align: center">
            <form id="photoForm" name="photoForm" action="${pageContext.request.contextPath}/student/uploadXxw.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo" name="idCard">
              <img id="photoImg" <c:if test="${!empty student.xxwUrl}">src="${student.xxwUrl}"</c:if> style="<c:if test="${empty student.xxwUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile" name="img" class="uploadfile" onchange="upImg(1);" />上传学信网认证</a>
            </form>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >准考证正面照：</td>
          <td>
            <form id="photoForm2" name="photoForm2" action="${pageContext.request.contextPath}/student/uploadZkzFront.json" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo2" name="idCard">
              <img id="photoImg2" <c:if test="${!empty student.zkzFrontUrl}">src="${student.zkzFrontUrl}"</c:if> style="<c:if test="${empty student.zkzFrontUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile2" name="img" class="uploadfile" onchange="upImg(2);" />上传准考证正面照</a>
            </form>
          </td>
          <td class="tag-b" >准考证背面照：</td>
          <td style="text-align: center">
            <form id="photoForm3" name="photoForm3" action="${pageContext.request.contextPath}/student/uploadZkzBack" enctype="multipart/form-data" method="post">
              <input type="hidden" id="idCard_photo3" name="idCard">
              <img id="photoImg3" <c:if test="${!empty student.zkzBackUrl}">src="${student.zkzBackUrl}"</c:if> style="<c:if test="${empty student.zkzBackUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="photoFile3" name="img" class="uploadfile" onchange="upImg(3);" />上传准考证背面照</a>
            </form>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <a class="btn-com" href="#" onclick="edit(2)">保存提交</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</div>
<script>
  function upImg(flag){
    var formId = "";
    var url = "";
    var imgId = "";
    var setValueId = "";
    var idCard = "${student.idCard}";
    if(idCard == ""){
      app.msg("请先输入身份证号！")
      return false;
    }
    if(1 == flag){
      formId = "photoForm";
      url = "${pageContext.request.contextPath}/student/uploadXxw.json";
      imgId = "photoImg";
      setValueId = "xxwUrl";
      $("#idCard_photo").val(idCard);
    }
    if(2 == flag){
      formId = "photoForm2";
      url = "${pageContext.request.contextPath}/student/uploadZkzFront.json";
      imgId = "photoImg2";
      setValueId = "zkzFrontUrl";
      $("#idCard_photo2").val(idCard);
    }
    if(3 == flag){
      formId = "photoForm3";
      url = "${pageContext.request.contextPath}/student/uploadZkzBack.json";
      imgId = "photoImg3";
      setValueId = "zkzBackUrl";
      $("#idCard_photo3").val(idCard);
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

  function edit(){
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    if($("#editReson").val().trim() == ""){
      app.alert("请输入变更原因！", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/editStudent/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageStudent/page.html", ${reqParams});
  }
</script>