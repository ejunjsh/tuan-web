<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Tuan ++</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
  <body>
 <%@ include file="/WEB-INF/jsp/common/top.jsp"%>
    <div class="container">
    

<%@ include file="/WEB-INF/jsp/common/breadCrumb.jsp"%>

    
    <div class="row">
      <c:forEach items="${deals}" var="deal">
        <div class="col-sm-6 col-md-3">
          <div class="thumbnail">
            <a class="thumbnail" title="${deal.title}" href="${deal.siteUrl}" target="_blank"><img src="<%=request.getContextPath()%>/staticFile/img/a.gif" class="lazy" data-original="${deal.imageUrl}"></a>
            <div class="caption">
              <h4><a title="${deal.title}"  href="${deal.siteUrl}" target="_blank">${deal.shortTitle}</a></h4>
              <p class="do"><a target="_blank" role="button" class="btn btn-primary" href="${deal.siteUrl}">去购买</a>
              <span class="yen">¥</span>
				<span>${deal.currentPrice}</span>
				<em>¥${deal.originalPrice }</em>
              </p>
            </div>
          </div>
        </div>
        </c:forEach>
      </div>
      <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
    </div> <!-- /container -->
    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
  </body>
</html>