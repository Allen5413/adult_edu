<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>学期</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>考核方式</th>
            <th>开课时间</th>
            <th>学分</th>
          </tr>
          <c:if test="${empty list}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty list}">
            <c:forEach var="course" items="${list}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${course.semester}</td>
                <td>${course.code}</td>
                <td>${course.name}</td>
                <td>${course.type}</td>
                <td>${course.course_date}</td>
                <td>${course.score}</td>
              </tr>
            </c:forEach>
          </c:if>
        </table>
      </div>
