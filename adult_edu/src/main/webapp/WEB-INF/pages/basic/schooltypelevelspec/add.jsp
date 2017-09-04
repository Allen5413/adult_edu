<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加专业</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/addSchoolTypeLevelSpec/add.json" method="post">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>
              <select id="schoolId" name="schoolId">
                <c:forEach var="school" items="${schoolList}">
                  <option value="${school.id}">${school.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>
              <select id="typeId" name="recruitTypeId">
                <c:forEach var="type" items="${typeList}">
                  <option value="${type.id}">${type.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>
              <select id="levelId" name="levelId">
                <c:forEach var="level" items="${levelList}">
                  <option value="${level.id}">${level.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">专业编号：</td>
            <td><input type="text" id="specCode" name="specCode" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">专业名称：</td>
            <td><input type="text" id="specName" name="specName" class="input-txt-220" /></td>
          </tr>
        </table>
      </form>
      <form id="importForm" name="importForm" action="${pageContext.request.contextPath}/importSchoolTypeLevelSpec.json" enctype="multipart/form-data" method="post">
        <table class="set-table-info">
          <tr>
            <td class="tag-b"></td>
            <td>
              <table>
                <tr>
                  <td>
                    <a class="btn-com-upload" href="#" onclick="add()">保存提交</a>
                  </td>
                  <td>
                    <input type="hidden" id="schoolId2" name="schoolId"/>
                    <input type="hidden" id="typeId2" name="typeId"/>
                    <input type="hidden" id="levelId2" name="levelId"/>
                    <a class="btn-com-upload" href="#"><input type="file" name="file" class="uploadfile" onchange="addImport()" />导入专业表</a>
                  </td>
                  <td><a class="btn-com-upload" href="${pageContext.request.contextPath}/template/spec.xlsx">专业表模板下载</a></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function add(){
    if($("#specCode").val() == ""){
      app.alert("请输入编号！", 1);
      return false;
    }
    if($("#specName").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/addSchoolTypeLevelSpec/add.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageSchoolTypeLevelSpec/page.html", ${reqParams});
  }

  function addImport(){
    $("#schoolId2").val($("#schoolId").val());
    $("#typeId2").val($("#typeId").val());
    $("#levelId2").val($("#levelId").val());
    $("#importForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/importSchoolTypeLevelSpec.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          layer.confirm("上传成功，是否要进入专业列表页面上传相应的课程？", {icon: 3, title:'提示'},
            function(index){
              app.clickResources("${pageContext.request.contextPath}/findSpecBySchoolIdAndTypeIdAndLevelId/find.html?schoolId="+$("#schoolId").val()+"&typeId="+$("#typeId").val()+"&levelId="+$("#levelId").val());
              layer.close(index);
            },
            function(){
              app.clickResources("${pageContext.request.contextPath}/pageSchoolTypeLevelSpec/page.html", ${reqParams});
            }
          );
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
          app.clickResources('${pageContext.request.contextPath}/addSchoolTypeLevelSpec/open.html?reqParams=${reqParams}');
        }
      }
    });
  }
</script>