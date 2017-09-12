<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">层次信息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
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
          <td>${teachPlan.year}年${teachPlan.term == 0 ? "春季":"秋季"}</td>
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
            <c:if test="${student.state eq '0'}">在籍</c:if>
            <c:if test="${student.state eq '1'}">休学</c:if>
            <c:if test="${student.state eq '2'}">退学</c:if>
            <c:if test="${student.state eq '3'}">毕业</c:if>
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
          <td>${student.zipCode}</td>
          <td class="tag-b">联系电话：</td>
          <td>${student.tel}</td>
          <td class="tag-b">手机号码：</td>
          <td>${student.phone}</td>
          <td class="tag-b">电子邮箱：</td>
          <td>${student.email}</td>
        </tr>
        <tr>
          <td class="tag-b">通讯地址：</td>
          <td colspan="7">${student.address}</td>
        </tr>
      </table>
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
            <c:if test="${!empty student.xxwUrl}">
              <img src="${student.xxwUrl}" style="width: 400px; height: 280px; " /><br/>
            </c:if>
            <%--<c:if test="${empty student.xxwUrl}">--%>
              <%--<img src="${pageContext.request.contextPath}/upload/student/temp/${student.idCard}_xxw.png" style="width: 400px; height: 280px; " /><br/>--%>
            <%--</c:if>--%>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >准考证正面照：</td>
          <td>
            <c:if test="${!empty student.zkzFrontUrl}">
              <img src="${student.zkzFrontUrl}" style="width: 400px; height: 280px; " /><br/>
            </c:if>
            <%--<c:if test="${empty student.zkzFrontUrl}">--%>
              <%--<img src="${pageContext.request.contextPath}/upload/student/temp/${student.idCard}_zkzFront.png" style="width: 400px; height: 280px; " /><br/>--%>
            <%--</c:if>--%>
          </td>
          <td class="tag-b" >准考证背面照：</td>
          <td style="text-align: center">
            <c:if test="${!empty student.zkzBackUrl}">
              <img src="${student.zkzBackUrl}" style="width: 400px; height: 280px; " /><br/>
            </c:if>
            <%--<c:if test="${empty student.zkzBackUrl}">--%>
              <%--<img src="${pageContext.request.contextPath}/upload/student/temp/${student.idCard}_zkzBack.png" style="width: 400px; height: 280px; " /><br/>--%>
            <%--</c:if>--%>
          </td>
        </tr>
      </table>
    </div>
    <c:if test="${!empty param.dataChangeId}">
      <div class="view-opr-info">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">变更内容：</td>
            <td>${dataChange.changeContent}</td>
          </tr>
          <tr>
            <td class="tag-b">变更原因：</td>
            <td>${dataChange.editReson}</td>
          </tr>
          <tr>
            <td class="tag-b">状态：</td>
            <td>
              <c:if test="${dataChange.state == '0'}">待审核</c:if>
              <c:if test="${dataChange.state == '1'}">未通过</c:if>
              <c:if test="${dataChange.state == '2'}">已通过</c:if>
            </td>
          </tr>
          <tr>
            <td class="tag-b">拒绝原因：</td>
            <td><textarea class="textarea-intro" id="refuseContent" rows="5" cols="50" >${dataChange.refuseContent}</textarea></td>
          </tr>
          <c:if test="${dataChange.state == '0' && sessionScope.type eq '1'}">
            <tr>
              <td class="tag-b"></td>
              <td>
                <a class="btn-com" href="#" onclick="saveAudit(2)">通过</a>
                <a class="btn-com" href="#" onclick="saveAudit(1)">不通过</a>
              </td>
            </tr>
          </c:if>
        </table>
      </div>
    </c:if>
  </div>
</div>
<script>
  function saveAudit(state){
    var refuseContent = $("#refuseContent").val();
    if(1 == state && refuseContent == ""){
      app.alert("请输入拒绝原因！", 1);
      return false;
    }
    if(2 == state && refuseContent != ""){
      app.alert("审核通过不需要填写拒绝原因", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/auditDataChange.json", {"id":${dataChange.id}, "refuseContent":refuseContent, "state":state}, "${pageContext.request.contextPath}/pageDataChange/page.html", ${reqParams});
  }
</script>