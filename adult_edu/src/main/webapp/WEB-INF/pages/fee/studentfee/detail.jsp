<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">${student.name}的缴费明细</div>
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
      <c:forEach var="detailList" items="${feeDetail}">
        <table class="info-table-input" style="width: 96%;">
          <tr>
            <td class="tag-b" colspan="8">${detailList.key}</td>
          </tr>
          <tr>
            <td>
              <div class="data-table-list" style="padding-top: 2px;">
                <table width="100%">
                  <tr>
                    <th>缴费类型</th>
                    <th>缴费方式</th>
                    <th>缴费金额</th>
                    <th>收费分销商</th>
                    <th>分销商实缴金额</th>
                    <th>缴费时间</th>
                    <th>状态</th>
                  </tr>
                  <c:forEach var="detail" items="${detailList.value}">
                    <tr>
                      <td>${detail.name}</td>
                      <td>
                        <c:if test="${detail.fee_style == 0}">平台缴费</c:if>
                        <c:if test="${detail.fee_style == 1}">分销商缴费</c:if>
                        <c:if test="${detail.fee_style == 2}">中心缴费</c:if>
                      </td>
                      <td>${detail.fee}</td>
                      <td><c:if test="${detail.fee_style == 1}">${user.name}</c:if></td>
                      <td>${detail.fxsFee}</td>
                      <td>${detail.operate_time}</td>
                      <td>${detail.sumFee >= detail.typeFee ? "已缴清" : "未缴完"}</td>
                    </tr>
                  </c:forEach>
                </table>
              </div>
            </td>
          </tr>
        </table>
      </c:forEach>
    </div>
  </div>
</div>
