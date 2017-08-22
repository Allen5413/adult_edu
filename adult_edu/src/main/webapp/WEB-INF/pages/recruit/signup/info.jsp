<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">层次信息</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <table class="info-table-input" style="width: 80%">
        <tr>
          <td class="tag-b">学校：</td>
          <td>${school.name}</td>
          <td class="tag-b">报考类型：</td>
          <td>${type.name}</td>
          <td rowspan="4" width="120" style="text-align: center;">
            <c:if test="${!empty signUp.photoUrl}"><img src="${pageContext.request.contextPath}${signUp.photoUrl}" style="width: 100px; height: 100px;" /></c:if>
            <c:if test="${empty signUp.photoUrl}"><img src="${pageContext.request.contextPath}/img/zs_uhead.jpg" /></c:if>
          </td>
        </tr>
        <tr>
          <td class="tag-b">层次：</td>
          <td>${level.name}</td>
          <td class="tag-b">专业：</td>
          <td>${spec.name}</td>
        </tr>
        <tr>
          <td class="tag-b">批次：</td>
          <td>${teachPlan.year}年${teachPlan.term == 0 ? "春季" : "秋季"}</td>
          <td class="tag-b" >姓名：</td>
          <td>${signUp.name}</td>
        </tr>
        <tr>
          <td class="tag-b" >性别：</td>
          <td>${signUp.sex == 0 ? "男" : "女"}</td>
          <td class="tag-b">身份证号：</td>
          <td>${signUp.idCard}</td>
        </tr>
        <tr>
          <td class="tag-b" >手机：</td>
          <td>${signUp.phone}</td>
          <td class="tag-b">QQ：</td>
          <td  colspan="2">${signUp.qq}</td>
        </tr>
        <tr>
          <td class="tag-b">学习方式：</td>
          <td >
            <c:if test="${0 == signUp.studyType}">脱产</c:if>
            <c:if test="${1 == signUp.studyType}">业余</c:if>
            <c:if test="${2 == signUp.studyType}">函授</c:if>
            <c:if test="${3 == signUp.studyType}">远程教育</c:if>
            </select>
          </td>
          <td class="tag-b" >家庭住址：</td>
          <td colspan="2">${signUp.address}</td>
        </tr>
        <tr>
          <td class="tag-b">电子邮箱：</td>
          <td >${signUp.email}</td>
          <td class="tag-b">邮政编码：</td>
          <td >${signUp.zipCode}</td>
        </tr>
        <tr>
          <td class="tag-b" >生源备注：</td>
          <td colspan="3">${signUp.sourceRemark}</td>
        </tr>
        <tr>
          <td class="tag-b" >入学前学历：</td>
          <td>${signUp.beforeEdu}</td>
          <td class="tag-b">毕（肄）业时间：</td>
          <td colspan="2">${signUp.beforeGraduationDate}</td>
        </tr>
        <tr>
          <td class="tag-b" >毕（肄）业学校：</td>
          <td>${signUp.beforeSchool}</td>
          <td class="tag-b">毕业证书编号：</td>
          <td colspan="2">${signUp.beforeCode}</td>
        </tr>
        <tr>
          <td class="tag-b" >党团员否：</td>
          <td>${signUp.party}</td>
          <td class="tag-b">入党团年月：</td>
          <td colspan="2">${signUp.partyDate}</td>
        </tr>
        <tr>
          <td class="tag-b" >个人简历</td>
          <td colspan="4">
            <table class="in-table" style="width: 100%">
              <tr>
                <th>起止时间</th>
                <th>学校或工作单位</th>
                <th>地址</th>
                <th>职务</th>
                <th>离开原因</th>
              </tr>
              <tr>
                <td>${signUp.resumeDate}</td>
                <td>${signUp.resumeCompany}</td>
                <td>${signUp.resumeAddress}</td>
                <td>${signUp.resumePost}</td>
                <td colspan="2">${signUp.resumeLeaveReason}</td>
              </tr>
              <tr>
                <td>${signUp.resumeDate2}</td>
                <td>${signUp.resumeCompany2}</td>
                <td>${signUp.resumeAddress2}</td>
                <td>${signUp.resumePost2}</td>
                <td colspan="2">${signUp.resumeLeaveReason2}</td>
              </tr>
              <tr>
                <td>${signUp.resumeDate3}</td>
                <td>${signUp.resumeCompany3}</td>
                <td>${signUp.resumeAddress3}</td>
                <td>${signUp.resumePost3}</td>
                <td colspan="2">${signUp.resumeLeaveReason3}</td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >家庭成员</td>
          <td colspan="4">
            <table class="in-table" style="width: 100%">
              <tr>
                <th>姓名</th>
                <th>称谓</th>
                <th>年龄</th>
                <th>政治面貌</th>
                <th>工作单位</th>
                <th>职务或工种</th>
              </tr>
              <tr>
                <td>${signUp.familyName}</td>
                <td>${signUp.familyRelation}</td>
                <td>${signUp.familyAge}</td>
                <td>${signUp.familyPolitical}</td>
                <td>${signUp.familyCompany}</td>
                <td>${signUp.familyPost}</td>
              </tr>
              <tr>
                <td>${signUp.familyName2}</td>
                <td>${signUp.familyRelation2}</td>
                <td>${signUp.familyAge2}</td>
                <td>${signUp.familyPolitical2}</td>
                <td>${signUp.familyCompany2}</td>
                <td>${signUp.familyPost2}</td>
              </tr>
              <tr>
                <td>${signUp.familyName3}</td>
                <td>${signUp.familyRelation3}</td>
                <td>${signUp.familyAge3}</td>
                <td>${signUp.familyPolitical3}</td>
                <td>${signUp.familyCompany3}</td>
                <td>${signUp.familyPost3}</td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >何时何地曾获过何种奖励：</td>
          <td colspan="4">${signUp.reward}</td>
        </tr>
        <tr>
          <td class="tag-b" >生源来源：</td>
          <td colspan="4">
            <c:if test="${signUp.userId == -1}">本部</c:if>
            <c:if test="${signUp.userId != -1}">${user.name}</c:if>
          </td>
        </tr>
        <tr>
          <td class="tag-b" >拒绝原因：</td>
          <td colspan="4">${reason}</td>
        </tr>
      </table>
      </form>
      <table class="info-table-input" style="width: 80%">
        <tr>
          <td style="text-align: center">
            <img id="photoImg" <c:if test="${!empty signUp.photoUrl}">src="${signUp.photoUrl}"</c:if> style="<c:if test="${empty signUp.photoUrl}">display: none;</c:if> width: 400px; height: 280px;" /><br/>
          </td>
          <td>
            <img id="photoImg2" <c:if test="${!empty signUp.idCardFrontUrl}">src="${signUp.idCardFrontUrl}"</c:if> style="<c:if test="${empty signUp.idCardFrontUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
        </tr>
        <tr>
          <td style="text-align: center">
            <img id="photoImg3" <c:if test="${!empty signUp.idCardBackUrl}">src="${signUp.idCardBackUrl}"</c:if> style="<c:if test="${empty signUp.idCardBackUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
          <td>
            <img id="photoImg4" <c:if test="${!empty signUp.diplomaUrl}">src="${signUp.diplomaUrl}"</c:if> style="<c:if test="${empty signUp.diplomaUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
        </tr>
        <tr>
          <td style="text-align: center">
            <img id="photoImg5" <c:if test="${!empty signUp.xxwUrl}">src="${signUp.xxwUrl}"</c:if> style="<c:if test="${empty signUp.xxwUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
          </td>
          <td>
            <img id="photoImg6" <c:if test="${!empty signUp.ydsUrl}">src="${signUp.ydsUrl}"</c:if> style="<c:if test="${empty signUp.ydsUrl}">display: none;</c:if> width: 400px; height: 280px; " /><br/>
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
          <c:if test="${dataChange.state == '0'}">
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