<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加${student.name}的缴费信息</div>
  <ul class="search-view">
    <li>
      <span class="itg">所在高校：${school.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <span class="itg">学号：${student.code}</span>
    </li>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addStudentFee/add.json" method="post">
        <input type="hidden" name="studentId" value="${param.studentId}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">缴费类型：</td>
            <td>
              <select id="feeTypeId" name="feeTypeId">
                <c:forEach var="type" items="${typeList}">
                  <option value="${type.id}">${type.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">缴费方式：</td>
            <td>
              <select id="feeStyle" name="feeStyle">
                <option value="0">平台缴费</option>
                <option value="1">分销商缴费</option>
                <option value="2">中心缴费</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">缴费金额：</td>
            <td><input type="text" id="money" name="money" class="input-txt-220" placeholder="单位：元"  /></td>
          </tr>
          <tr>
            <td class="tag-b">分销商实缴：</td>
            <td><input type="text" id="fxsMoney" name="fxsMoney" class="input-txt-220" placeholder="单位：元"  /></td>
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
  function add(){
    if($("#feeTypeId").val() == ""){
      app.alert("请选择缴费类型！", 1);
      return false;
    }
    if($("#feeStyle").val() == ""){
      app.alert("请选择缴费方式！", 1);
      return false;
    }
    if(!vaildApp.vaildMoney($("#money").val())){
      app.alert("请输入正确的费用金额！", 1);
      return false;
    }
    if($("#feeStyle").val() == 1 && !vaildApp.vaildMoney($("#fxsMoney").val())){
      app.alert("请输入分销商实缴金额！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addStudentFee/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageStudentFee/page.html", ${reqParams});
  }
</script>
