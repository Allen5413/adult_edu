<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加新高校</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="logoForm" name="logoForm" action="${pageContext.request.contextPath}/uploadScLogo.json" enctype="multipart/form-data" method="post">
        <table class="set-table-info">
          <tr>
            <td>
              <img id="logoImg" style="display:none; width: 400px; height: 280px; " /><br/>
              <a class="btn-com-upload" href="#"><input type="file" id="logoFile" name="img" class="uploadfile" onchange="upLogo();" />选择要上传的logo</a>
            </td>
          </tr>
        </table>
      </form>
      <form id="form" name="form" action="${pageContext.request.contextPath}/addSchool/add.json">
        <table class="set-table-info">
          <input type="hidden" id="logo" name="logo" />
          <tr>
            <td class="tag-b">高校编码：</td>
            <td><input type="text" id="code" name="code" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校名称：</td>
            <td><input type="text" id="name" name="name" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系人：</td>
            <td><input type="text" id="linkMan" name="linkMan" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">联系电话：</td>
            <td><input type="text" id="phone" name="phone" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校地址：</td>
            <td><input type="text" id="address" name="address" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">高校简介：</td>
            <td><textarea class="textarea-intro" name="remark" rows="5" cols="50" ></textarea></td>
          </tr>
          <tr>
            <td class="tag-b">教务负责人：</td>
            <td>
              <select name="userId">
                <c:forEach var="user" items="${userList}">
                  <option value="${user.id}">${user.name}</option>
                </c:forEach>
              </select>
            </td>
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
  function upLogo(){
    $("#logoForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/uploadScLogo.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          $("#logoImg").show();
          $("#logoImg").attr('src', data.url);
          $("#logo").val(data.url);
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function saveCenter(){
    if($("#code").val() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#name").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    if($("#phone").val() == ""){
      app.alert("请输入联系电话！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addSchool/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageSchool/page.html", ${reqParams});
  }
</script>