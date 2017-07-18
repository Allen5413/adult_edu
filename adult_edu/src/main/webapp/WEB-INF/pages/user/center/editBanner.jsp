<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑中心banner</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/editCenter/editBanner.json" enctype="multipart/form-data" method="post">
        <input type="hidden" name="id" value="${center.id}" />
        <table class="set-table-info">
          <tr>
            <td>
              <c:if test="${!empty center.banner}">
                <img src="${pageContext.request.contextPath}${center.banner}?random=${random}" />
              </c:if>
            </td>
          </tr>
          <tr>
            <td>
              <a class="btn-com-upload" href="#"><input type="file" name="img" class="uploadfile" onchange="edit()" />选择要上传的banner</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function edit(){
    $("#form").ajaxSubmit({
      url : "/editCenter/editBanner.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          app.clickResources("${pageContext.request.contextPath}/findCneterById/open.html?random=${random}");
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
          app.clickResources('${pageContext.request.contextPath}/editCenter/openEditLogo.html?id=${center.id}');
        }
      }
    });
  }
</script>