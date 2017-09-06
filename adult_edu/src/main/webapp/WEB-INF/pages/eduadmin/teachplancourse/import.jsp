<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">导入教学计划课程</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="importForm" name="importForm" action="${pageContext.request.contextPath}/importTeachPlanCourse/importCourse.json" enctype="multipart/form-data" method="post">
        <input type="hidden" id="id" name="id" value="${teachPlan.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">高校：</td>
            <td>${school.name}</td>
          </tr>
          <tr>
            <td class="tag-b">招生类型：</td>
            <td>${recruitType.name}</td>
          </tr>
          <tr>
            <td class="tag-b">层次：</td>
            <td>${level.name}</td>
          </tr>
          <tr>
            <td class="tag-b">专业：</td>
            <td>${spec.name}</td>
          </tr>
          <tr>
            <td class="tag-b">开课年季：</td>
            <td>${teachPlan.year}年${teachPlan.term == 0 ? "春":"秋"}季</td>
          </tr>
          <tr>
            <td class="tag-b">报名时间：</td>
            <td>${teachPlan.beginDate}  到  ${teachPlan.endDate}</td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <table>
                  <td>
                    <a class="btn-com-upload" href="#"><input type="file" id="file" name="file" class="uploadfile" onchange="importCourse()" />导入课程表</a>
                  </td>
                  <td>
                    <a class="btn-com-upload" href="${pageContext.request.contextPath}/template/teachPlan.xlsx">课程表模板下载</a>
                  </td>
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
  function importCourse(){
    $("#importForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/importTeachPlanCourse/importCourse.json",
      dataType : 'json',
      success : function(data){
        if(0 == data.state) {
          app.msg("导入成功", 0);
          app.clickResources("${pageContext.request.contextPath}/pageTeachPlan/page.html", ${reqParams});
        }
        if(1 == data.state) {
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>
