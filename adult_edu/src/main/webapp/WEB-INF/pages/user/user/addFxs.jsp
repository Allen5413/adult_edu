<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加分销商</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addUser/add.html">
        <input type="hidden" id="type" name="type" value="3" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">分销商名称：</td>
            <td><input type="text" id="name" name="name" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">分销商编码：</td>
            <td><input type="text" id="code" name="code" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系人：</td>
            <td><input type="text" id="linkMan" name="linkMan" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">手机号码：</td>
            <td><input type="text" id="phone" name="phone" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">详细地址：</td>
            <td><input type="text" id="address" name="address" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="saveUser()">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function saveUser(){
    if($("#name").val() == ""){
      app.alert("请输入分销商名称！", 1);
      return false;
    }
    if($("#code").val() == ""){
      app.alert("请输入分销商编码！", 1);
      return false;
    }
    if($("#linkMan").val() == ""){
      app.alert("请输入联系人！", 1);
      return false;
    }
    if(isNaN($("#phone").val()) || 11 != $("#phone").val().length){
      app.alert("请输入正确的手机号码！", 1);
      return false;
    }
    if($("#address").val() == ""){
      app.alert("请输入详细地址！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addUser/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageUser/page.html", ${reqParams});
  }
</script>