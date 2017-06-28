<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加新用户</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addCenter/add.html">
        <input type="hidden" id="authorizeDate" name="authorizeDate" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">学习中心编号：</td>
            <td><input type="text" id="code" name="code" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">学习中心名称：</td>
            <td><input type="text" id="name" name="name" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">手机号码：</td>
            <td><input type="text" id="phone" name="phone" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">授权到期时间：</td>
            <td><input type="text" id="date" name="date" style="height: 24px;" onfocus="WdatePicker({firstDayOfWeek:1})" class="Wdate" /></td>
          </tr>
          <tr>
            <td class="tag-b">缴费状态：</td>
            <td>
              <label><input type="radio" name="feeState" value="2" checked> 已缴费 </label>
              <label><input type="radio" name="feeState" value="0" > 未缴费 </label>
            </td>
          </tr>
          <tr>
            <td class="tag-b">账号状态：</td>
            <td>
              <label><input type="radio" name="state" value="1" checked> 启用 </label>
              <label><input type="radio" name="state" value="0" > 停用 </label>
            </td>
          </tr>
          <tr>
            <td class="tag-b">学习中心介绍：</td>
            <td><textarea class="textarea-intro" name="remark" rows="5" cols="50" ></textarea></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="saveCenter()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function saveCenter(){
    if($("#code").val() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#name").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    if($("#date").val() == ""){
      app.alert("请选择授权到期时间！", 1);
      return false;
    }
    $("#authorizeDate").val($("#date").val()+" 23:59:59");
    app.add("${pageContext.request.contextPath}/addCenter/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageCenter/page.html", ${reqParams});
  }
</script>